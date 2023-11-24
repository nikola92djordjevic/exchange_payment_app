package rs.payment.exchange.service;

import PlatniPrometDB.tables.pojos.BalanceEntry;
import PlatniPrometDB.tables.pojos.CashRegister;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.payment.exchange.dto.record.BalanceRecordResult;
import rs.payment.exchange.dto.requests.BalanceRequest;
import rs.payment.exchange.dto.requests.TransferRequest;
import rs.payment.exchange.dto.respones.BalanceResponse;
import rs.payment.exchange.dto.respones.CommissionUserResponse;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.dto.respones.Test;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.exception.error.Error;
import rs.payment.exchange.exception.error.ErrorCode;

import java.math.BigDecimal;
import java.util.List;

import static PlatniPrometDB.Tables.*;
import static org.jooq.impl.DSL.*;

@Service
public class BalanceService{

    @Autowired
    private DSLContext db;

    @Autowired
    JWTF<AuthInfo> authJwtf;

    private final Integer BALANCE_SIDE_IN = 1;
    private final Integer BALANCE_SIDE_OUT = 2;

    /**
     * Za računanje balansa po danu ima na videu
     * jOOQ: Get Back In Control of Your SQL
     * na 24:04
     *
     *
     *
     *
     select
     k.ddd,
     k.sss,
     sum(k.sss) OVER ( PARTITION by k.hhh order by k.ddd) as suma
     from
     (
     select
     sum(t.amount) as sss,
     t.date_of_creation as ddd,
     1 as hhh

     from balance_entry t group by t.date_of_creation order by t.date_of_creation
     ) k




     */

    public void test(){


        List<CommissionUserResponse> response = db.select(EXCHANGE_JOB.USER_ID,
                EXCHANGE_JOB.DATE_OF_CREATION.as("dateOfCommission"),
                sum(when(EXCHANGE_JOB.JOB_TYPE_ID.eq(1L), EXCHANGE_JOB.MIDDLE_RATE_AMOUNT.sub(EXCHANGE_JOB.EXCHANGE_AMOUNT))).as("buyCommission"),
                sum(when(EXCHANGE_JOB.JOB_TYPE_ID.eq(2L), EXCHANGE_JOB.EXCHANGE_AMOUNT.sub(EXCHANGE_JOB.MIDDLE_RATE_AMOUNT))).as("sellCommission")
                ).from(EXCHANGE_JOB).groupBy(EXCHANGE_JOB.USER_ID, EXCHANGE_JOB.DATE_OF_CREATION).fetch().into(CommissionUserResponse.class);


        Table<?> testTable = db.select(BALANCE_ENTRY.DATE_OF_CREATION.as("balanceDate"),
                BALANCE_ENTRY.CURRENCY_CODE.as("currency"),
                BALANCE_ENTRY.CASH_REGISTER_ID.as("cashRegister"),
                sum(when(BALANCE_ENTRY.ENTRY_SIDE.eq(1),  BALANCE_ENTRY.AMOUNT).otherwise(BALANCE_ENTRY.AMOUNT.neg())).as("suma1"),
                inline(1).as("rbr"))
                .from(BALANCE_ENTRY)
                .where(BALANCE_ENTRY.CURRENCY_CODE.eq("941"))
                .and(BALANCE_ENTRY.CASH_REGISTER_ID.eq(1L))
                .groupBy(BALANCE_ENTRY.DATE_OF_CREATION,
                        BALANCE_ENTRY.CURRENCY_CODE,
                        BALANCE_ENTRY.CASH_REGISTER_ID)
                .orderBy(BALANCE_ENTRY.DATE_OF_CREATION).asTable();

        List<Test> res = db.select(
                testTable.field("balanceDate"),
                testTable.field("currency"),
                testTable.field("cashRegister"),
                testTable.field("suma1"),
                sum(testTable.field("suma1").cast(Integer.class)).over(partitionBy(testTable.field("rbr")).orderBy(testTable.field("balanceDate"))).as("suma2")

        ).from(testTable).fetch().into(Test.class);

        if(res == null){

        }

    }




    /**
     *
     * @param balanceEntry
     * @return
     */

    public RpcResponse createBalanceEntry(BalanceEntry balanceEntry) {
        RpcResponse rpcResponse = new RpcResponse();

        try {
            db.newRecord(BALANCE_ENTRY, balanceEntry).store();
            rpcResponse.setOk();
        } catch (Exception e) {
            Error error = new Error(this.getClass().getSimpleName(),
                    ErrorCode.CONFLICT_CODE,
                    new Object() {
                    }.getClass().getEnclosingMethod().getName(),
                    e.getMessage());
            rpcResponse.addError(error);
        }


        return rpcResponse;
    }


    public RpcResponse createBalanceEntries(List<BalanceEntry> balanceEntryList) {
        RpcResponse rpcResponse = new RpcResponse();

        try {
            for (BalanceEntry entry : balanceEntryList) {
                db.newRecord(BALANCE_ENTRY, entry).store();
            }
            rpcResponse.setOk();
        } catch (Exception e) {
            Error error = new Error(this.getClass().getSimpleName(),
                    ErrorCode.CONFLICT_CODE,
                    new Object() {
                    }.getClass().getEnclosingMethod().getName(),
                    e.getMessage());
            rpcResponse.addError(error);
        }
        return rpcResponse;
    }

    private void updateBalance(String currencyCode, BigDecimal balance, List<BalanceResponse.BalanceRecord> recordList){
        boolean exist = false;
        for(BalanceResponse.BalanceRecord record: recordList){
            if(record.currencyCode.equals(currencyCode)){
                record.balance = record.balance.add(balance);
                exist = true;
                break;
            }
        }
        if(!exist){
            recordList.add(new BalanceResponse.BalanceRecord(currencyCode, balance));
        }
    }



    public BalanceResponse calculateBalance(BalanceRequest balanceRequest) {
        if (balanceRequest.cashRegisterId == null) {
            return null;
        }

        BalanceResponse response = new BalanceResponse();
        CashRegister cashRegister = db.select().from(CASH_REGISTER).where(CASH_REGISTER.CASH_REGISTER_ID.eq(balanceRequest.cashRegisterId)).fetchOne().into(CashRegister.class);

        if(cashRegister!=null){

        List<BalanceRecordResult> recordResultLists = db.select(
                isnull(BALANCE_ENTRY.CASH_REGISTER_ID, cashRegister.getCashRegisterId()).as("cash_register_id"),
                CURRENCY.CURRENCY_CODE,
                isnull(sum(when(BALANCE_ENTRY.ENTRY_SIDE.eq(1), BALANCE_ENTRY.AMOUNT).otherwise(BALANCE_ENTRY.AMOUNT.neg())), 0).as("balance"))
                .from(CURRENCY).leftJoin(BALANCE_ENTRY).on(CURRENCY.CURRENCY_CODE.eq(BALANCE_ENTRY.CURRENCY_CODE).and(BALANCE_ENTRY.CASH_REGISTER_ID.eq(cashRegister.getCashRegisterId())))
                .groupBy(BALANCE_ENTRY.CASH_REGISTER_ID, CURRENCY.CURRENCY_CODE).fetch().into(BalanceRecordResult.class);

        response.cashRegisterId = cashRegister.getCashRegisterId();
        response.cashRegisterName = cashRegister.getName();

        for (BalanceRecordResult record : recordResultLists) {
            response.balanceList.add(new BalanceResponse.BalanceRecord(record.currencyCode, record.balance));
        }
        return response;
        }
        else{
            return null;
        }
    }


    public RpcResponse transfer(TransferRequest transferRequest) {
        RpcResponse rpcResponse = new RpcResponse();

        try {
            for (BalanceEntry entry : transferRequest.balanceEntryList) {
                db.newRecord(BALANCE_ENTRY, entry).store();
            }
            rpcResponse.setOk();

        } catch (Exception e) {
            Error error = new Error(this.getClass().getSimpleName(),
                    ErrorCode.CONFLICT_CODE,
                    new Object() {
                    }.getClass().getEnclosingMethod().getName(),
                    e.getMessage());
            rpcResponse.addError(error);
        }

        return rpcResponse;
    }
}

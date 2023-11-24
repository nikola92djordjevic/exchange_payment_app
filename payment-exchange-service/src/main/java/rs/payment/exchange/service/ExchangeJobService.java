package rs.payment.exchange.service;

import PlatniPrometDB.tables.pojos.BalanceEntry;
import PlatniPrometDB.tables.pojos.ExchangeJob;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.payment.exchange.dto.requests.ExchangeMoneyRequest;
import rs.payment.exchange.dto.respones.CommissionUserResponse;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.exception.error.Error;
import rs.payment.exchange.exception.error.ErrorCode;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static PlatniPrometDB.Sequences.EXCHANGE_CASH_DENOMINATION_SEQ;
import static PlatniPrometDB.Sequences.EXCHANGE_JOB_SEQ;
import static PlatniPrometDB.Tables.*;
import static org.jooq.impl.DSL.sum;
import static org.jooq.impl.DSL.when;

@Service
public class ExchangeJobService {

    @Autowired
    private DSLContext db;

    @Autowired
    JWTF<AuthInfo> authJwtf;

    public RpcResponse exchangeMoney(ExchangeMoneyRequest exchangeMoneyRequest) {

        RpcResponse rpcResponse = new RpcResponse();

        if (exchangeMoneyRequest.exchangeJob.getExchangeJobId() == null) {
            Long exchangeJobId = db.nextval(EXCHANGE_JOB_SEQ);
            Long denominationId = db.nextval(EXCHANGE_CASH_DENOMINATION_SEQ);
            exchangeMoneyRequest.exchangeJob.setExchangeJobId(exchangeJobId);
            exchangeMoneyRequest.exchangeJob.setExchangeCashDenominationId(denominationId);
            exchangeMoneyRequest.exchangeCashDenomination.setExchangeCashDenominationId(denominationId);
            exchangeMoneyRequest.exchangeCashDenomination.setExchangeJobId(exchangeJobId);
            for (BalanceEntry entry : exchangeMoneyRequest.balanceEntryList) {
                entry.setJobId(exchangeJobId);
            }
        }


        try {
            db.newRecord(EXCHANGE_JOB, exchangeMoneyRequest.exchangeJob).store();
            db.newRecord(EXCHANGE_CASH_DENOMINATION, exchangeMoneyRequest.exchangeCashDenomination).store();
            for (BalanceEntry entry : exchangeMoneyRequest.balanceEntryList) {
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

    public List<ExchangeJob> getExchangeJobRecords() {

        Long userId = authJwtf.getPayload().getId();

        List<ExchangeJob> exchangeJobList = db.select().from(EXCHANGE_JOB)
                .where(EXCHANGE_JOB.USER_ID.eq(userId))
                .and(EXCHANGE_JOB.DATE_OF_CREATION.eq(LocalDate.now()))
                .orderBy(EXCHANGE_JOB.TIMESTAMP_OF_CREATION.desc())
                .fetch().into(ExchangeJob.class);

        return exchangeJobList;
    }

    public CommissionUserResponse getCurrentCommission() {
        Long userId = authJwtf.getPayload().getId();
        LocalDate date = LocalDate.now();

        return db.select(EXCHANGE_JOB.USER_ID,
                EXCHANGE_JOB.DATE_OF_CREATION.as("dateOfCommission"),
                sum(when(EXCHANGE_JOB.JOB_TYPE_ID.eq(1L), EXCHANGE_JOB.MIDDLE_RATE_AMOUNT.sub(EXCHANGE_JOB.EXCHANGE_AMOUNT)).otherwise(BigDecimal.ZERO)).as("buyCommission"),
                sum(when(EXCHANGE_JOB.JOB_TYPE_ID.eq(2L), EXCHANGE_JOB.EXCHANGE_AMOUNT.sub(EXCHANGE_JOB.MIDDLE_RATE_AMOUNT)).otherwise(BigDecimal.ZERO)).as("sellCommission")
        ).from(EXCHANGE_JOB).where(EXCHANGE_JOB.USER_ID.eq(userId)).and(EXCHANGE_JOB.DATE_OF_CREATION.eq(date)).groupBy(EXCHANGE_JOB.USER_ID, EXCHANGE_JOB.DATE_OF_CREATION).fetchOne().into(CommissionUserResponse.class);

    }

    public List<CommissionUserResponse> getWeekCommission() {
        Long userId = authJwtf.getPayload().getId();
        LocalDate date = LocalDate.now();

        return db.select(EXCHANGE_JOB.USER_ID,
                EXCHANGE_JOB.DATE_OF_CREATION.as("dateOfCommission"),
                sum(when(EXCHANGE_JOB.JOB_TYPE_ID.eq(1L), EXCHANGE_JOB.MIDDLE_RATE_AMOUNT.sub(EXCHANGE_JOB.EXCHANGE_AMOUNT)).otherwise(BigDecimal.ZERO)).as("buyCommission"),
                sum(when(EXCHANGE_JOB.JOB_TYPE_ID.eq(2L), EXCHANGE_JOB.EXCHANGE_AMOUNT.sub(EXCHANGE_JOB.MIDDLE_RATE_AMOUNT)).otherwise(BigDecimal.ZERO)).as("sellCommission")
        ).from(EXCHANGE_JOB).where(EXCHANGE_JOB.USER_ID.eq(userId)).and(EXCHANGE_JOB.DATE_OF_CREATION.between(date.minusDays(7), date)).groupBy(EXCHANGE_JOB.USER_ID, EXCHANGE_JOB.DATE_OF_CREATION).orderBy(EXCHANGE_JOB.DATE_OF_CREATION.desc()).fetch().into(CommissionUserResponse.class);

    }

    public List<CommissionUserResponse> calculateCommissionPerDay(Long dayNumber) {
        Long userId = authJwtf.getPayload().getId();
        LocalDate date = LocalDate.now();

        return db.select(EXCHANGE_JOB.USER_ID,
                EXCHANGE_JOB.DATE_OF_CREATION.as("dateOfCommission"),
                sum(when(EXCHANGE_JOB.JOB_TYPE_ID.eq(1L), EXCHANGE_JOB.MIDDLE_RATE_AMOUNT.sub(EXCHANGE_JOB.EXCHANGE_AMOUNT)).otherwise(BigDecimal.ZERO)).as("buyCommission"),
                sum(when(EXCHANGE_JOB.JOB_TYPE_ID.eq(2L), EXCHANGE_JOB.EXCHANGE_AMOUNT.sub(EXCHANGE_JOB.MIDDLE_RATE_AMOUNT)).otherwise(BigDecimal.ZERO)).as("sellCommission")
        ).from(EXCHANGE_JOB).where(EXCHANGE_JOB.USER_ID.eq(userId)).and(EXCHANGE_JOB.DATE_OF_CREATION.between(date.minusDays(dayNumber), date))
                .groupBy(EXCHANGE_JOB.USER_ID, EXCHANGE_JOB.DATE_OF_CREATION)
                .orderBy(EXCHANGE_JOB.DATE_OF_CREATION.asc()).fetch().into(CommissionUserResponse.class);

    }
}

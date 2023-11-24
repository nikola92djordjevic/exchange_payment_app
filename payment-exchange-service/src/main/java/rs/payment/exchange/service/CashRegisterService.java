package rs.payment.exchange.service;

import PlatniPrometDB.tables.pojos.CashRegister;
import PlatniPrometDB.tables.pojos.WorkPlace;
import PlatniPrometDB.tables.records.CashRegisterRecord;
import PlatniPrometDB.tables.records.WorkPlaceRecord;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.payment.exchange.dto.requests.BalanceRequest;
import rs.payment.exchange.dto.respones.CashRegisterResponse;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.exception.error.Error;
import rs.payment.exchange.exception.error.ErrorCode;
import rs.payment.exchange.exception.service.ApiServiceException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static PlatniPrometDB.Tables.CASH_REGISTER;
import static PlatniPrometDB.Tables.WORK_PLACE;

@Service
public class CashRegisterService {

    @Autowired
    private DSLContext db;

    @Autowired
    JWTF<AuthInfo> authJwtf;

    public final BalanceService balanceService;

    public CashRegisterService(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public RpcResponse createCashRegister(CashRegister cashRegister) {
        RpcResponse rpcResponse = new RpcResponse();
        if(cashRegister.getTimestampOfCreation()==null){
            cashRegister.setTimestampOfCreation(OffsetDateTime.now());
        }
        try {
            db.newRecord(CASH_REGISTER, cashRegister).store();
            rpcResponse.setOk();
        } catch (Exception e) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.CONFLICT_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            e.getMessage()), null);
        }
        return rpcResponse;
    }

    public RpcResponse editCashRegister(CashRegister cashRegister) {
        RpcResponse rpcResponse = new RpcResponse();


        CashRegisterRecord cashRegisterRecord = cashRegisterExistValidator(cashRegister.getCashRegisterId());
        try {
            cashRegisterRecord.setName(cashRegister.getName());
            cashRegisterRecord.setFlTreasury(cashRegister.getFlTreasury());
            cashRegisterRecord.store();
            rpcResponse.setOk();
        } catch (Exception e) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.CONFLICT_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            e.getMessage()), null);
        }
        return rpcResponse;
    }

    public CashRegisterResponse getCashRegister(Long cashRegisterId) {

        CashRegisterRecord cashRegisterRecord = db.fetchOne(CASH_REGISTER, CASH_REGISTER.FL_ACTIVE.eq(true)
                .and(CASH_REGISTER.CASH_REGISTER_ID.eq(cashRegisterId)));


        if (cashRegisterRecord == null) {
            return null;
        }
        WorkPlaceRecord workPlaceRecord = new WorkPlaceRecord();
        if(cashRegisterRecord.getFlInUse()){
            workPlaceRecord = db.fetchOne(WORK_PLACE,
                    WORK_PLACE.CASH_REGISTER_ID.eq(cashRegisterRecord.getCashRegisterId())
                            .and(WORK_PLACE.TIMESTAMP_OF_CLOSE.isNull()));
        }

        return new CashRegisterResponse(cashRegisterRecord.into(CashRegister.class),
                workPlaceRecord.into(WorkPlace.class),
                balanceService.calculateBalance(new BalanceRequest(cashRegisterRecord.getCashRegisterId())));

    }

    public List<CashRegisterResponse> getAllCashRegisters() {
        List<CashRegisterResponse> response = new ArrayList<>();

        List<CashRegister> cashRegisterList = db.fetch(CASH_REGISTER, CASH_REGISTER.FL_ACTIVE.eq(true)
        //        .and(CASH_REGISTER.FL_TREASURY.eq(false))
        ).into(CashRegister.class);

        for(CashRegister cashReg :cashRegisterList){
            WorkPlace workPlace = new WorkPlace();
            if(cashReg.getFlInUse()){
                workPlace = db.fetchOne(WORK_PLACE,
                        WORK_PLACE.CASH_REGISTER_ID.eq(cashReg.getCashRegisterId())
                                .and(WORK_PLACE.TIMESTAMP_OF_CLOSE.isNull())).into(WorkPlace.class);
            }
            response.add(new CashRegisterResponse(cashReg,workPlace, balanceService.calculateBalance(new BalanceRequest(cashReg.getCashRegisterId()))));
        }
        return response;
    }

    public List<CashRegister> getAllTreasuries() {
        return db.fetch(CASH_REGISTER, CASH_REGISTER.FL_ACTIVE.eq(true)
                .and(CASH_REGISTER.FL_TREASURY.eq(true))).into(CashRegister.class);
    }

    public List<CashRegister> getAllFreeCashRegisters() {

        return db.select().from(CASH_REGISTER).where(CASH_REGISTER.FL_ACTIVE.eq(true)
                .and(CASH_REGISTER.FL_IN_USE.eq(false)).and(CASH_REGISTER.FL_TREASURY.eq(false))).fetch().into(CashRegister.class);

    }

    public RpcResponse deleteCashRegister(Long cashRegisterId) {
        RpcResponse rpcResponse = new RpcResponse();

        CashRegisterRecord cashRegisterRecord = cashRegisterExistValidator(cashRegisterId);
        try {
            cashRegisterRecord.setFlActive(false);
            cashRegisterRecord.delete();
            rpcResponse.setOk();
        } catch (Exception e) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.CONFLICT_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            e.getMessage()), null);
        }
        return rpcResponse;
    }

    private CashRegisterRecord cashRegisterExistValidator(Long cashRegisterId) {
        if (cashRegisterId == null) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            "CashRegisterId id is null"), null);
        }

        CashRegisterRecord cashRegister = db.fetchOne(CASH_REGISTER, CASH_REGISTER.CASH_REGISTER_ID.eq(cashRegisterId));

        if (cashRegister == null) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            String.format("CashRegister with id %s doesn't exist.", cashRegisterId)), null);
        }
        return cashRegister;
    }
}

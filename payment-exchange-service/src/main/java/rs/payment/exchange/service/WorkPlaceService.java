package rs.payment.exchange.service;

import PlatniPrometDB.Tables;
import PlatniPrometDB.tables.daos.WorkPlaceDao;
import PlatniPrometDB.tables.pojos.CashRegister;
import PlatniPrometDB.tables.pojos.User;
import PlatniPrometDB.tables.pojos.WorkPlace;
import PlatniPrometDB.tables.records.UserRecord;
import PlatniPrometDB.tables.records.WorkPlaceRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.payment.exchange.exception.error.Error;
import rs.payment.exchange.exception.error.ErrorCode;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.exception.service.ApiServiceException;
import rs.payment.exchange.service.WorkPlaceService;

import java.time.OffsetDateTime;
import java.util.List;

import static PlatniPrometDB.Tables.*;
import static PlatniPrometDB.Tables.USER_PERSONAL_INFO;

@Service
public class WorkPlaceService {

    @Autowired
    private DSLContext db;

    @Autowired
    JWTF<AuthInfo> authJwtf;

    public RpcResponse openWorkPlace(WorkPlace workPlace) {
        RpcResponse rpcResponse = new RpcResponse();
        if(workPlace.getTimestampOfOpen()==null){
            workPlace.setTimestampOfOpen(OffsetDateTime.now());
        }
        if(workPlace.getUserId()==null){
            workPlace.setUserId(authJwtf.getPayload().getId());
        }
        try {
            db.newRecord(Tables.WORK_PLACE, workPlace).store();
            db.update(CASH_REGISTER).set(CASH_REGISTER.FL_IN_USE, true)
                    .where(CASH_REGISTER.CASH_REGISTER_ID.eq(workPlace.getCashRegisterId()))
                    .execute();
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

    public RpcResponse closeWorkPlace(Long workPlaceId) {
        RpcResponse response = new RpcResponse();

        Record record = db.fetchOne(WORK_PLACE,WORK_PLACE.WORK_PLACE_ID.eq(workPlaceId));

        if(record == null){
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            String.format("WorkPlace with id %s doesn't exist.", workPlaceId)), null);
        }
        try {
            db.update(WORK_PLACE).set(WORK_PLACE.TIMESTAMP_OF_CLOSE, OffsetDateTime.now())
                    .where(WORK_PLACE.WORK_PLACE_ID.eq(workPlaceId))
                    .execute();
            WorkPlace workPlace = record.into(WorkPlace.class);
            db.update(CASH_REGISTER).set(CASH_REGISTER.FL_IN_USE, false)
                    .where(CASH_REGISTER.CASH_REGISTER_ID.eq(workPlace.getCashRegisterId()))
                    .execute();
            response.setOk();
        } catch (Exception e) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            e.getMessage()), null);
        }
        return response;
    }

    public WorkPlace getWorkPlace(Long workPlaceId) {
        WorkPlaceDao workPlaceDao = new WorkPlaceDao(db.configuration());
        return workPlaceDao.fetchOneByWorkPlaceId(workPlaceId);
    }

    public WorkPlace getActiveWorkPlace() {

        Long userId = authJwtf.getPayload().getId();

        Record rec = db.select().from(WORK_PLACE).where(WORK_PLACE.USER_ID.eq(userId)).and(WORK_PLACE.TIMESTAMP_OF_CLOSE.isNull()).fetchOne();

        if(rec != null){
            return rec.into(WorkPlace.class);
        }else{
            return null;
        }
    }

    public List<WorkPlace> getAllWorkPlaces() {
        return db.fetch(WORK_PLACE).into(WorkPlace.class);
    }
}

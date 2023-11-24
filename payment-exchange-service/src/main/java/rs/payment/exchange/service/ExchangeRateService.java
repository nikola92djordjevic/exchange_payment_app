package rs.payment.exchange.service;

import PlatniPrometDB.Sequences;
import PlatniPrometDB.tables.pojos.Currency;
import PlatniPrometDB.tables.pojos.ExchangeRate;
import PlatniPrometDB.tables.pojos.ExchangeRateEntry;
import PlatniPrometDB.tables.records.ExchangeRateEntryRecord;
import PlatniPrometDB.tables.records.ExchangeRateRecord;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.payment.exchange.dto.requests.ExchangeRateCreateRequest;
import rs.payment.exchange.dto.respones.ExchangeRateResponse;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.dto.security.JWTF;
import rs.payment.exchange.dto.security.auth.AuthInfo;
import rs.payment.exchange.exception.error.Error;
import rs.payment.exchange.exception.error.ErrorCode;
import rs.payment.exchange.exception.service.ApiServiceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static PlatniPrometDB.Tables.*;

@Service
public class ExchangeRateService {

    @Autowired
    private DSLContext db;
    @Autowired
    JWTF<AuthInfo> authJwtf;

    public RpcResponse createExchangeRate(ExchangeRateCreateRequest exchangeRateCreateRequest) {
        RpcResponse rpcResponse = new RpcResponse();

        if (exchangeRateCreateRequest.exchangeRate.getExchangeRateId() == null) {
            exchangeRateCreateRequest.exchangeRate.setExchangeRateId(db.nextval(Sequences.EXCHANGE_RATE_ID_SEQ));
        }

        exchangeRateCreateRequest.exchangeRate.setCreatedBy(authJwtf.getPayload().getId());
        exchangeRateCreateRequest.exchangeRate.setTimestampOfCreation(LocalDateTime.now());

        try {
            db.newRecord(EXCHANGE_RATE, exchangeRateCreateRequest.exchangeRate).store();

            if (!exchangeRateCreateRequest.exchangeRateEntryList.isEmpty()) {
                for (ExchangeRateEntry entry : exchangeRateCreateRequest.exchangeRateEntryList) {
                    if (entry.getExchangeRateEntryId() == null) {
                        entry.setExchangeRateEntryId(db.nextval(Sequences.EXCHANGE_RATE_ENTRY_ID_SEQ));
                    }
                    entry.setExchangeRateId(exchangeRateCreateRequest.exchangeRate.getExchangeRateId());

                    db.newRecord(EXCHANGE_RATE_ENTRY, entry).store();
                }
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

    public RpcResponse editExchangeRate(ExchangeRateCreateRequest editRequest) {

        RpcResponse response = new RpcResponse();

        ExchangeRateRecord exchangeRateRecord = exchangeRateExistValidator(editRequest.exchangeRate.getExchangeRateId());

        exchangeRateRecord.setChangedBy(authJwtf.getPayload().getId());
        try {

            exchangeRateRecord.setTimestampOfStart(editRequest.exchangeRate.getTimestampOfStart());
            exchangeRateRecord.setTimestampOfEnd(editRequest.exchangeRate.getTimestampOfEnd());
            exchangeRateRecord.setChangedBy(authJwtf.getPayload().getId());
            exchangeRateRecord.setTimestampOfChange(LocalDateTime.now());
            exchangeRateRecord.update();

            List<ExchangeRateEntryRecord> exchRtEntryList = db.fetch(EXCHANGE_RATE_ENTRY, EXCHANGE_RATE_ENTRY.EXCHANGE_RATE_ID.eq(editRequest.exchangeRate.getExchangeRateId()));

            for (ExchangeRateEntry rec : editRequest.exchangeRateEntryList) {

                if (rec.getExchangeRateEntryId() == null) {
                    db.newRecord(EXCHANGE_RATE_ENTRY, rec).store();
                } else {
                    for (ExchangeRateEntryRecord recDB : exchRtEntryList) {
                        if (recDB.getExchangeRateEntryId().equals(rec.getExchangeRateEntryId())) {
                            recDB.setBuyRate(rec.getBuyRate());
                            recDB.setMiddleRate(rec.getMiddleRate());
                            recDB.setSellRate(rec.getSellRate());
                            recDB.setCurrencyCode(rec.getCurrencyCode());
                            recDB.setCurrencyNameCode(rec.getCurrencyNameCode());
                            recDB.update();
                        }
                    }
                }
            }
            for (ExchangeRateEntryRecord rec : exchRtEntryList) {
                if (!existsInRequest(rec.into(ExchangeRateEntry.class), editRequest.exchangeRateEntryList)) {
                    rec.delete();
                }
            }
            response.setOk();
            return response;

        } catch (Exception e) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.CONFLICT_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            e.getMessage()), null);
        }
    }

    private boolean existsInRequest(ExchangeRateEntry dbEntry, List<ExchangeRateEntry> requestList) {
        for (ExchangeRateEntry request : requestList) {
            if (request.getExchangeRateEntryId()!= null && request.getExchangeRateEntryId().equals(dbEntry.getExchangeRateEntryId())) {
                return true;
            }
        }

        return false;
    }

    public RpcResponse deleteExchangeRate(Long exchangeRateId) {
        RpcResponse rpcResponse = new RpcResponse();
        exchangeRateExistValidator(exchangeRateId);
        try {
            db.update(EXCHANGE_RATE)
                    .set(EXCHANGE_RATE.FL_ACTIVE, false)
                    .where((EXCHANGE_RATE.EXCHANGE_RATE_ID.eq(exchangeRateId))).execute();
            rpcResponse.setOk();

            return rpcResponse;
        } catch (Exception e) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.CONFLICT_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            e.getMessage()), null);
        }
    }

    public List<ExchangeRateResponse> getAllExchangeRates() {
        List<ExchangeRateResponse> exchangeRateResponseList = new ArrayList<>();

        List<ExchangeRateRecord> exchangeRateRecord = db.fetch(EXCHANGE_RATE, EXCHANGE_RATE.FL_ACTIVE.eq(true));
        for (ExchangeRateRecord rec : exchangeRateRecord) {
            ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();

            exchangeRateResponse.exchangeRate = rec.into(ExchangeRate.class);
            exchangeRateResponse.exchangeRateEntryList = db.select().from(EXCHANGE_RATE_ENTRY)
                    .where(EXCHANGE_RATE_ENTRY.EXCHANGE_RATE_ID.eq(rec.getExchangeRateId()))
                    .fetchInto(ExchangeRateEntry.class);
            exchangeRateResponseList.add(exchangeRateResponse);
        }
        return exchangeRateResponseList;
    }

    public ExchangeRateResponse getExchangeRate(Long exchangeRateId) {
        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();

        ExchangeRateRecord exchangeRateRecord = db.fetchOne(EXCHANGE_RATE, EXCHANGE_RATE.EXCHANGE_RATE_ID.eq(exchangeRateId));
        if (exchangeRateRecord != null) {
            exchangeRateResponse.exchangeRate = exchangeRateRecord.into(ExchangeRate.class);
            exchangeRateResponse.exchangeRateEntryList = db.select().from(EXCHANGE_RATE_ENTRY).where(EXCHANGE_RATE_ENTRY.EXCHANGE_RATE_ID.eq(exchangeRateId)).fetchInto(ExchangeRateEntry.class);
        }
        return exchangeRateResponse;
    }

    public ExchangeRateResponse getActiveExchangeRate() {

        ExchangeRateResponse exchangeRateResponse = new ExchangeRateResponse();

        ExchangeRateRecord exchangeRateRecord = db.fetchOne(EXCHANGE_RATE,
                EXCHANGE_RATE.TIMESTAMP_OF_START.lessOrEqual(LocalDate.now())
                        .and(EXCHANGE_RATE.TIMESTAMP_OF_END.greaterOrEqual(LocalDate.now())));
        if (exchangeRateRecord != null) {
            exchangeRateResponse.exchangeRate = exchangeRateRecord.into(ExchangeRate.class);
            exchangeRateResponse.exchangeRateEntryList = db.select().from(EXCHANGE_RATE_ENTRY).where(EXCHANGE_RATE_ENTRY.EXCHANGE_RATE_ID.eq(exchangeRateRecord.getExchangeRateId())).fetchInto(ExchangeRateEntry.class);
        }
        return exchangeRateResponse;
    }

    public List<Currency> getCurrencyList() {

        return db.fetch(CURRENCY, (Condition) null).into(Currency.class);
    }

    private ExchangeRateRecord exchangeRateExistValidator(Long exchangeRateId) {
        if (exchangeRateId == null) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            "ExchangeRateId id is null"), null);
        }

        ExchangeRateRecord exchangeRateRecord = db.fetchOne(EXCHANGE_RATE, EXCHANGE_RATE.EXCHANGE_RATE_ID.eq(exchangeRateId));

        if (exchangeRateRecord == null) {
            throw new ApiServiceException(
                    new Error(
                            this.getClass().getSimpleName(),
                            ErrorCode.BAD_REQUEST_CODE,
                            new Object() {
                            }.getClass().getEnclosingMethod().getName(),
                            String.format("ExchangeRate with id %s doesn't exist.", exchangeRateId)), null);
        }
        return exchangeRateRecord;
    }
}

/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.daos;


import PlatniPrometDB.tables.BalanceEntry;
import PlatniPrometDB.tables.records.BalanceEntryRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BalanceEntryDao extends DAOImpl<BalanceEntryRecord, PlatniPrometDB.tables.pojos.BalanceEntry, Long> {

    /**
     * Create a new BalanceEntryDao without any configuration
     */
    public BalanceEntryDao() {
        super(BalanceEntry.BALANCE_ENTRY, PlatniPrometDB.tables.pojos.BalanceEntry.class);
    }

    /**
     * Create a new BalanceEntryDao with an attached configuration
     */
    public BalanceEntryDao(Configuration configuration) {
        super(BalanceEntry.BALANCE_ENTRY, PlatniPrometDB.tables.pojos.BalanceEntry.class, configuration);
    }

    @Override
    public Long getId(PlatniPrometDB.tables.pojos.BalanceEntry object) {
        return object.getBalanceEntryId();
    }

    /**
     * Fetch records that have <code>balance_entry_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfBalanceEntryId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.BALANCE_ENTRY_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>balance_entry_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByBalanceEntryId(Long... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.BALANCE_ENTRY_ID, values);
    }

    /**
     * Fetch a unique record that has <code>balance_entry_id = value</code>
     */
    public PlatniPrometDB.tables.pojos.BalanceEntry fetchOneByBalanceEntryId(Long value) {
        return fetchOne(BalanceEntry.BALANCE_ENTRY.BALANCE_ENTRY_ID, value);
    }

    /**
     * Fetch a unique record that has <code>balance_entry_id = value</code>
     */
    public Optional<PlatniPrometDB.tables.pojos.BalanceEntry> fetchOptionalByBalanceEntryId(Long value) {
        return fetchOptional(BalanceEntry.BALANCE_ENTRY.BALANCE_ENTRY_ID, value);
    }

    /**
     * Fetch records that have <code>entry_side BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfEntrySide(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.ENTRY_SIDE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>entry_side IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByEntrySide(Integer... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.ENTRY_SIDE, values);
    }

    /**
     * Fetch records that have <code>cash_register_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfCashRegisterId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.CASH_REGISTER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>cash_register_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByCashRegisterId(Long... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.CASH_REGISTER_ID, values);
    }

    /**
     * Fetch records that have <code>entry_type_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfEntryTypeId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.ENTRY_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>entry_type_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByEntryTypeId(Long... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.ENTRY_TYPE_ID, values);
    }

    /**
     * Fetch records that have <code>currency_code BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfCurrencyCode(String lowerInclusive, String upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.CURRENCY_CODE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>currency_code IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByCurrencyCode(String... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.CURRENCY_CODE, values);
    }

    /**
     * Fetch records that have <code>amount BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfAmount(BigDecimal lowerInclusive, BigDecimal upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.AMOUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>amount IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByAmount(BigDecimal... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.AMOUNT, values);
    }

    /**
     * Fetch records that have <code>job_type_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfJobTypeId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.JOB_TYPE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>job_type_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByJobTypeId(Long... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.JOB_TYPE_ID, values);
    }

    /**
     * Fetch records that have <code>job_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfJobId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.JOB_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>job_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByJobId(Long... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.JOB_ID, values);
    }

    /**
     * Fetch records that have <code>work_place_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfWorkPlaceId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.WORK_PLACE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>work_place_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByWorkPlaceId(Long... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.WORK_PLACE_ID, values);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfUserId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByUserId(Long... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.USER_ID, values);
    }

    /**
     * Fetch records that have <code>timestamp_of_creation BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfTimestampOfCreation(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.TIMESTAMP_OF_CREATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>timestamp_of_creation IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByTimestampOfCreation(OffsetDateTime... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.TIMESTAMP_OF_CREATION, values);
    }

    /**
     * Fetch records that have <code>date_of_creation BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfDateOfCreation(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.DATE_OF_CREATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>date_of_creation IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByDateOfCreation(LocalDate... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.DATE_OF_CREATION, values);
    }

    /**
     * Fetch records that have <code>time_of_creation BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchRangeOfTimeOfCreation(LocalTime lowerInclusive, LocalTime upperInclusive) {
        return fetchRange(BalanceEntry.BALANCE_ENTRY.TIME_OF_CREATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>time_of_creation IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.BalanceEntry> fetchByTimeOfCreation(LocalTime... values) {
        return fetch(BalanceEntry.BALANCE_ENTRY.TIME_OF_CREATION, values);
    }
}

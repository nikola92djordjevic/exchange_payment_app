/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.daos;


import PlatniPrometDB.tables.WorkPlace;
import PlatniPrometDB.tables.records.WorkPlaceRecord;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WorkPlaceDao extends DAOImpl<WorkPlaceRecord, PlatniPrometDB.tables.pojos.WorkPlace, Long> {

    /**
     * Create a new WorkPlaceDao without any configuration
     */
    public WorkPlaceDao() {
        super(WorkPlace.WORK_PLACE, PlatniPrometDB.tables.pojos.WorkPlace.class);
    }

    /**
     * Create a new WorkPlaceDao with an attached configuration
     */
    public WorkPlaceDao(Configuration configuration) {
        super(WorkPlace.WORK_PLACE, PlatniPrometDB.tables.pojos.WorkPlace.class, configuration);
    }

    @Override
    public Long getId(PlatniPrometDB.tables.pojos.WorkPlace object) {
        return object.getWorkPlaceId();
    }

    /**
     * Fetch records that have <code>work_place_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchRangeOfWorkPlaceId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(WorkPlace.WORK_PLACE.WORK_PLACE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>work_place_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchByWorkPlaceId(Long... values) {
        return fetch(WorkPlace.WORK_PLACE.WORK_PLACE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>work_place_id = value</code>
     */
    public PlatniPrometDB.tables.pojos.WorkPlace fetchOneByWorkPlaceId(Long value) {
        return fetchOne(WorkPlace.WORK_PLACE.WORK_PLACE_ID, value);
    }

    /**
     * Fetch a unique record that has <code>work_place_id = value</code>
     */
    public Optional<PlatniPrometDB.tables.pojos.WorkPlace> fetchOptionalByWorkPlaceId(Long value) {
        return fetchOptional(WorkPlace.WORK_PLACE.WORK_PLACE_ID, value);
    }

    /**
     * Fetch records that have <code>cash_register_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchRangeOfCashRegisterId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(WorkPlace.WORK_PLACE.CASH_REGISTER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>cash_register_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchByCashRegisterId(Long... values) {
        return fetch(WorkPlace.WORK_PLACE.CASH_REGISTER_ID, values);
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchRangeOfUserId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(WorkPlace.WORK_PLACE.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchByUserId(Long... values) {
        return fetch(WorkPlace.WORK_PLACE.USER_ID, values);
    }

    /**
     * Fetch records that have <code>timestamp_of_open BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchRangeOfTimestampOfOpen(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(WorkPlace.WORK_PLACE.TIMESTAMP_OF_OPEN, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>timestamp_of_open IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchByTimestampOfOpen(OffsetDateTime... values) {
        return fetch(WorkPlace.WORK_PLACE.TIMESTAMP_OF_OPEN, values);
    }

    /**
     * Fetch records that have <code>timestamp_of_close BETWEEN lowerInclusive
     * AND upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchRangeOfTimestampOfClose(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(WorkPlace.WORK_PLACE.TIMESTAMP_OF_CLOSE, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>timestamp_of_close IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchByTimestampOfClose(OffsetDateTime... values) {
        return fetch(WorkPlace.WORK_PLACE.TIMESTAMP_OF_CLOSE, values);
    }

    /**
     * Fetch records that have <code>timestamp_of_creation BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchRangeOfTimestampOfCreation(OffsetDateTime lowerInclusive, OffsetDateTime upperInclusive) {
        return fetchRange(WorkPlace.WORK_PLACE.TIMESTAMP_OF_CREATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>timestamp_of_creation IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.WorkPlace> fetchByTimestampOfCreation(OffsetDateTime... values) {
        return fetch(WorkPlace.WORK_PLACE.TIMESTAMP_OF_CREATION, values);
    }
}

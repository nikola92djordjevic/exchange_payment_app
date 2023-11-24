/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.daos;


import PlatniPrometDB.tables.Organisation;
import PlatniPrometDB.tables.records.OrganisationRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrganisationDao extends DAOImpl<OrganisationRecord, PlatniPrometDB.tables.pojos.Organisation, Long> {

    /**
     * Create a new OrganisationDao without any configuration
     */
    public OrganisationDao() {
        super(Organisation.ORGANISATION, PlatniPrometDB.tables.pojos.Organisation.class);
    }

    /**
     * Create a new OrganisationDao with an attached configuration
     */
    public OrganisationDao(Configuration configuration) {
        super(Organisation.ORGANISATION, PlatniPrometDB.tables.pojos.Organisation.class, configuration);
    }

    @Override
    public Long getId(PlatniPrometDB.tables.pojos.Organisation object) {
        return object.getOrganisationId();
    }

    /**
     * Fetch records that have <code>organisation_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchRangeOfOrganisationId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Organisation.ORGANISATION.ORGANISATION_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>organisation_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchByOrganisationId(Long... values) {
        return fetch(Organisation.ORGANISATION.ORGANISATION_ID, values);
    }

    /**
     * Fetch a unique record that has <code>organisation_id = value</code>
     */
    public PlatniPrometDB.tables.pojos.Organisation fetchOneByOrganisationId(Long value) {
        return fetchOne(Organisation.ORGANISATION.ORGANISATION_ID, value);
    }

    /**
     * Fetch a unique record that has <code>organisation_id = value</code>
     */
    public Optional<PlatniPrometDB.tables.pojos.Organisation> fetchOptionalByOrganisationId(Long value) {
        return fetchOptional(Organisation.ORGANISATION.ORGANISATION_ID, value);
    }

    /**
     * Fetch records that have <code>name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchRangeOfName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organisation.ORGANISATION.NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchByName(String... values) {
        return fetch(Organisation.ORGANISATION.NAME, values);
    }

    /**
     * Fetch records that have <code>legal_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchRangeOfLegalId(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organisation.ORGANISATION.LEGAL_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>legal_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchByLegalId(String... values) {
        return fetch(Organisation.ORGANISATION.LEGAL_ID, values);
    }

    /**
     * Fetch records that have <code>address BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchRangeOfAddress(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organisation.ORGANISATION.ADDRESS, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>address IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchByAddress(String... values) {
        return fetch(Organisation.ORGANISATION.ADDRESS, values);
    }

    /**
     * Fetch records that have <code>city BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchRangeOfCity(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organisation.ORGANISATION.CITY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>city IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchByCity(String... values) {
        return fetch(Organisation.ORGANISATION.CITY, values);
    }

    /**
     * Fetch records that have <code>phone_number BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchRangeOfPhoneNumber(String lowerInclusive, String upperInclusive) {
        return fetchRange(Organisation.ORGANISATION.PHONE_NUMBER, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>phone_number IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchByPhoneNumber(String... values) {
        return fetch(Organisation.ORGANISATION.PHONE_NUMBER, values);
    }

    /**
     * Fetch records that have <code>timestamp_of_creation BETWEEN
     * lowerInclusive AND upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchRangeOfTimestampOfCreation(LocalDateTime lowerInclusive, LocalDateTime upperInclusive) {
        return fetchRange(Organisation.ORGANISATION.TIMESTAMP_OF_CREATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>timestamp_of_creation IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Organisation> fetchByTimestampOfCreation(LocalDateTime... values) {
        return fetch(Organisation.ORGANISATION.TIMESTAMP_OF_CREATION, values);
    }
}
/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.daos;


import PlatniPrometDB.tables.UserAuth;
import PlatniPrometDB.tables.records.UserAuthRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserAuthDao extends DAOImpl<UserAuthRecord, PlatniPrometDB.tables.pojos.UserAuth, Long> {

    /**
     * Create a new UserAuthDao without any configuration
     */
    public UserAuthDao() {
        super(UserAuth.USER_AUTH, PlatniPrometDB.tables.pojos.UserAuth.class);
    }

    /**
     * Create a new UserAuthDao with an attached configuration
     */
    public UserAuthDao(Configuration configuration) {
        super(UserAuth.USER_AUTH, PlatniPrometDB.tables.pojos.UserAuth.class, configuration);
    }

    @Override
    public Long getId(PlatniPrometDB.tables.pojos.UserAuth object) {
        return object.getUserId();
    }

    /**
     * Fetch records that have <code>user_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchRangeOfUserId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(UserAuth.USER_AUTH.USER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>user_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchByUserId(Long... values) {
        return fetch(UserAuth.USER_AUTH.USER_ID, values);
    }

    /**
     * Fetch a unique record that has <code>user_id = value</code>
     */
    public PlatniPrometDB.tables.pojos.UserAuth fetchOneByUserId(Long value) {
        return fetchOne(UserAuth.USER_AUTH.USER_ID, value);
    }

    /**
     * Fetch a unique record that has <code>user_id = value</code>
     */
    public Optional<PlatniPrometDB.tables.pojos.UserAuth> fetchOptionalByUserId(Long value) {
        return fetchOptional(UserAuth.USER_AUTH.USER_ID, value);
    }

    /**
     * Fetch records that have <code>salt BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchRangeOfSalt(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserAuth.USER_AUTH.SALT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>salt IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchBySalt(String... values) {
        return fetch(UserAuth.USER_AUTH.SALT, values);
    }

    /**
     * Fetch records that have <code>iteration BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchRangeOfIteration(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(UserAuth.USER_AUTH.ITERATION, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>iteration IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchByIteration(Integer... values) {
        return fetch(UserAuth.USER_AUTH.ITERATION, values);
    }

    /**
     * Fetch records that have <code>hash BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchRangeOfHash(String lowerInclusive, String upperInclusive) {
        return fetchRange(UserAuth.USER_AUTH.HASH, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>hash IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchByHash(String... values) {
        return fetch(UserAuth.USER_AUTH.HASH, values);
    }

    /**
     * Fetch records that have <code>login_count BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchRangeOfLoginCount(Integer lowerInclusive, Integer upperInclusive) {
        return fetchRange(UserAuth.USER_AUTH.LOGIN_COUNT, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>login_count IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchByLoginCount(Integer... values) {
        return fetch(UserAuth.USER_AUTH.LOGIN_COUNT, values);
    }

    /**
     * Fetch records that have <code>fl_locked BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchRangeOfFlLocked(Boolean lowerInclusive, Boolean upperInclusive) {
        return fetchRange(UserAuth.USER_AUTH.FL_LOCKED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>fl_locked IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchByFlLocked(Boolean... values) {
        return fetch(UserAuth.USER_AUTH.FL_LOCKED, values);
    }

    /**
     * Fetch records that have <code>date_of_expiry BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchRangeOfDateOfExpiry(LocalDate lowerInclusive, LocalDate upperInclusive) {
        return fetchRange(UserAuth.USER_AUTH.DATE_OF_EXPIRY, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>date_of_expiry IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.UserAuth> fetchByDateOfExpiry(LocalDate... values) {
        return fetch(UserAuth.USER_AUTH.DATE_OF_EXPIRY, values);
    }
}

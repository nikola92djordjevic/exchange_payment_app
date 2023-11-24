/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.daos;


import PlatniPrometDB.tables.Role;
import PlatniPrometDB.tables.records.RoleRecord;

import java.util.List;
import java.util.Optional;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoleDao extends DAOImpl<RoleRecord, PlatniPrometDB.tables.pojos.Role, Long> {

    /**
     * Create a new RoleDao without any configuration
     */
    public RoleDao() {
        super(Role.ROLE, PlatniPrometDB.tables.pojos.Role.class);
    }

    /**
     * Create a new RoleDao with an attached configuration
     */
    public RoleDao(Configuration configuration) {
        super(Role.ROLE, PlatniPrometDB.tables.pojos.Role.class, configuration);
    }

    @Override
    public Long getId(PlatniPrometDB.tables.pojos.Role object) {
        return object.getRoleId();
    }

    /**
     * Fetch records that have <code>role_id BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Role> fetchRangeOfRoleId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Role.ROLE.ROLE_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>role_id IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Role> fetchByRoleId(Long... values) {
        return fetch(Role.ROLE.ROLE_ID, values);
    }

    /**
     * Fetch a unique record that has <code>role_id = value</code>
     */
    public PlatniPrometDB.tables.pojos.Role fetchOneByRoleId(Long value) {
        return fetchOne(Role.ROLE.ROLE_ID, value);
    }

    /**
     * Fetch a unique record that has <code>role_id = value</code>
     */
    public Optional<PlatniPrometDB.tables.pojos.Role> fetchOptionalByRoleId(Long value) {
        return fetchOptional(Role.ROLE.ROLE_ID, value);
    }

    /**
     * Fetch records that have <code>role_name BETWEEN lowerInclusive AND
     * upperInclusive</code>
     */
    public List<PlatniPrometDB.tables.pojos.Role> fetchRangeOfRoleName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Role.ROLE.ROLE_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>role_name IN (values)</code>
     */
    public List<PlatniPrometDB.tables.pojos.Role> fetchByRoleName(String... values) {
        return fetch(Role.ROLE.ROLE_NAME, values);
    }
}

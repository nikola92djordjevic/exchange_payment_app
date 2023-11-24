/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables;


import PlatniPrometDB.Keys;
import PlatniPrometDB.Public;
import PlatniPrometDB.tables.records.RoleRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Role extends TableImpl<RoleRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.role</code>
     */
    public static final Role ROLE = new Role();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RoleRecord> getRecordType() {
        return RoleRecord.class;
    }

    /**
     * The column <code>public.role.role_id</code>.
     */
    public final TableField<RoleRecord, Long> ROLE_ID = createField(DSL.name("role_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.role.role_name</code>.
     */
    public final TableField<RoleRecord, String> ROLE_NAME = createField(DSL.name("role_name"), SQLDataType.VARCHAR(100), this, "");

    private Role(Name alias, Table<RoleRecord> aliased) {
        this(alias, aliased, null);
    }

    private Role(Name alias, Table<RoleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.role</code> table reference
     */
    public Role(String alias) {
        this(DSL.name(alias), ROLE);
    }

    /**
     * Create an aliased <code>public.role</code> table reference
     */
    public Role(Name alias) {
        this(alias, ROLE);
    }

    /**
     * Create a <code>public.role</code> table reference
     */
    public Role() {
        this(DSL.name("role"), null);
    }

    public <O extends Record> Role(Table<O> child, ForeignKey<O, RoleRecord> key) {
        super(child, key, ROLE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<RoleRecord> getPrimaryKey() {
        return Keys.ROLE_PKEY;
    }

    @Override
    public Role as(String alias) {
        return new Role(DSL.name(alias), this);
    }

    @Override
    public Role as(Name alias) {
        return new Role(alias, this);
    }

    @Override
    public Role as(Table<?> alias) {
        return new Role(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Role rename(String name) {
        return new Role(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Role rename(Name name) {
        return new Role(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Role rename(Table<?> name) {
        return new Role(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
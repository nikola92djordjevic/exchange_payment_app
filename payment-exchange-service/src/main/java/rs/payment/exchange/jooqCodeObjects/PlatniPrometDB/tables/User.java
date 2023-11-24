/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables;


import PlatniPrometDB.Keys;
import PlatniPrometDB.Public;
import PlatniPrometDB.tables.records.UserRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
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
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.user</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>public.user.user_id</code>.
     */
    public final TableField<UserRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.user.username</code>.
     */
    public final TableField<UserRecord, String> USERNAME = createField(DSL.name("username"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.user.role_id</code>.
     */
    public final TableField<UserRecord, Integer> ROLE_ID = createField(DSL.name("role_id"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.user.fl_active</code>.
     */
    public final TableField<UserRecord, Boolean> FL_ACTIVE = createField(DSL.name("fl_active"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.user.date_of_start</code>.
     */
    public final TableField<UserRecord, LocalDate> DATE_OF_START = createField(DSL.name("date_of_start"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.user.date_of_end</code>.
     */
    public final TableField<UserRecord, LocalDate> DATE_OF_END = createField(DSL.name("date_of_end"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.user.timestamp_of_creation</code>.
     */
    public final TableField<UserRecord, LocalDateTime> TIMESTAMP_OF_CREATION = createField(DSL.name("timestamp_of_creation"), SQLDataType.LOCALDATETIME(6), this, "");

    private User(Name alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private User(Name alias, Table<UserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.user</code> table reference
     */
    public User(String alias) {
        this(DSL.name(alias), USER);
    }

    /**
     * Create an aliased <code>public.user</code> table reference
     */
    public User(Name alias) {
        this(alias, USER);
    }

    /**
     * Create a <code>public.user</code> table reference
     */
    public User() {
        this(DSL.name("user"), null);
    }

    public <O extends Record> User(Table<O> child, ForeignKey<O, UserRecord> key) {
        super(child, key, USER);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<UserRecord, Long> getIdentity() {
        return (Identity<UserRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.USER_PKEY;
    }

    @Override
    public User as(String alias) {
        return new User(DSL.name(alias), this);
    }

    @Override
    public User as(Name alias) {
        return new User(alias, this);
    }

    @Override
    public User as(Table<?> alias) {
        return new User(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Name name) {
        return new User(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(Table<?> name) {
        return new User(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Long, String, Integer, Boolean, LocalDate, LocalDate, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super Long, ? super String, ? super Integer, ? super Boolean, ? super LocalDate, ? super LocalDate, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super Long, ? super String, ? super Integer, ? super Boolean, ? super LocalDate, ? super LocalDate, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}

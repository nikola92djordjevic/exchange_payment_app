/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables;


import PlatniPrometDB.Keys;
import PlatniPrometDB.Public;
import PlatniPrometDB.tables.records.BalanceEntryRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function13;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row13;
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
public class BalanceEntry extends TableImpl<BalanceEntryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.balance_entry</code>
     */
    public static final BalanceEntry BALANCE_ENTRY = new BalanceEntry();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BalanceEntryRecord> getRecordType() {
        return BalanceEntryRecord.class;
    }

    /**
     * The column <code>public.balance_entry.balance_entry_id</code>.
     */
    public final TableField<BalanceEntryRecord, Long> BALANCE_ENTRY_ID = createField(DSL.name("balance_entry_id"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval(('public.balance_entry_balance_entry_id_seq'::text)::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.balance_entry.entry_side</code>. 1 - In, 2 - Out
     */
    public final TableField<BalanceEntryRecord, Integer> ENTRY_SIDE = createField(DSL.name("entry_side"), SQLDataType.INTEGER.nullable(false), this, "1 - In, 2 - Out");

    /**
     * The column <code>public.balance_entry.cash_register_id</code>.
     */
    public final TableField<BalanceEntryRecord, Long> CASH_REGISTER_ID = createField(DSL.name("cash_register_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.balance_entry.entry_type_id</code>. 1 - Exchange,
     * 2 - Transfer
     */
    public final TableField<BalanceEntryRecord, Long> ENTRY_TYPE_ID = createField(DSL.name("entry_type_id"), SQLDataType.BIGINT, this, "1 - Exchange, 2 - Transfer");

    /**
     * The column <code>public.balance_entry.currency_code</code>.
     */
    public final TableField<BalanceEntryRecord, String> CURRENCY_CODE = createField(DSL.name("currency_code"), SQLDataType.VARCHAR(3), this, "");

    /**
     * The column <code>public.balance_entry.amount</code>.
     */
    public final TableField<BalanceEntryRecord, BigDecimal> AMOUNT = createField(DSL.name("amount"), SQLDataType.NUMERIC(15, 2), this, "");

    /**
     * The column <code>public.balance_entry.job_type_id</code>.
     */
    public final TableField<BalanceEntryRecord, Long> JOB_TYPE_ID = createField(DSL.name("job_type_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.balance_entry.job_id</code>.
     */
    public final TableField<BalanceEntryRecord, Long> JOB_ID = createField(DSL.name("job_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.balance_entry.work_place_id</code>.
     */
    public final TableField<BalanceEntryRecord, Long> WORK_PLACE_ID = createField(DSL.name("work_place_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.balance_entry.user_id</code>.
     */
    public final TableField<BalanceEntryRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.balance_entry.timestamp_of_creation</code>.
     */
    public final TableField<BalanceEntryRecord, OffsetDateTime> TIMESTAMP_OF_CREATION = createField(DSL.name("timestamp_of_creation"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.balance_entry.date_of_creation</code>.
     */
    public final TableField<BalanceEntryRecord, LocalDate> DATE_OF_CREATION = createField(DSL.name("date_of_creation"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.balance_entry.time_of_creation</code>.
     */
    public final TableField<BalanceEntryRecord, LocalTime> TIME_OF_CREATION = createField(DSL.name("time_of_creation"), SQLDataType.LOCALTIME, this, "");

    private BalanceEntry(Name alias, Table<BalanceEntryRecord> aliased) {
        this(alias, aliased, null);
    }

    private BalanceEntry(Name alias, Table<BalanceEntryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.balance_entry</code> table reference
     */
    public BalanceEntry(String alias) {
        this(DSL.name(alias), BALANCE_ENTRY);
    }

    /**
     * Create an aliased <code>public.balance_entry</code> table reference
     */
    public BalanceEntry(Name alias) {
        this(alias, BALANCE_ENTRY);
    }

    /**
     * Create a <code>public.balance_entry</code> table reference
     */
    public BalanceEntry() {
        this(DSL.name("balance_entry"), null);
    }

    public <O extends Record> BalanceEntry(Table<O> child, ForeignKey<O, BalanceEntryRecord> key) {
        super(child, key, BALANCE_ENTRY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<BalanceEntryRecord> getPrimaryKey() {
        return Keys.BALANCE_ENTRY_PKEY;
    }

    @Override
    public BalanceEntry as(String alias) {
        return new BalanceEntry(DSL.name(alias), this);
    }

    @Override
    public BalanceEntry as(Name alias) {
        return new BalanceEntry(alias, this);
    }

    @Override
    public BalanceEntry as(Table<?> alias) {
        return new BalanceEntry(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public BalanceEntry rename(String name) {
        return new BalanceEntry(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BalanceEntry rename(Name name) {
        return new BalanceEntry(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public BalanceEntry rename(Table<?> name) {
        return new BalanceEntry(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row13 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row13<Long, Integer, Long, Long, String, BigDecimal, Long, Long, Long, Long, OffsetDateTime, LocalDate, LocalTime> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function13<? super Long, ? super Integer, ? super Long, ? super Long, ? super String, ? super BigDecimal, ? super Long, ? super Long, ? super Long, ? super Long, ? super OffsetDateTime, ? super LocalDate, ? super LocalTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function13<? super Long, ? super Integer, ? super Long, ? super Long, ? super String, ? super BigDecimal, ? super Long, ? super Long, ? super Long, ? super Long, ? super OffsetDateTime, ? super LocalDate, ? super LocalTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
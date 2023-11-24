/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables;


import PlatniPrometDB.Keys;
import PlatniPrometDB.Public;
import PlatniPrometDB.tables.records.ExchangeJobRecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function12;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row12;
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
public class ExchangeJob extends TableImpl<ExchangeJobRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.exchange_job</code>
     */
    public static final ExchangeJob EXCHANGE_JOB = new ExchangeJob();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ExchangeJobRecord> getRecordType() {
        return ExchangeJobRecord.class;
    }

    /**
     * The column <code>public.exchange_job.exchange_job_id</code>.
     */
    public final TableField<ExchangeJobRecord, Long> EXCHANGE_JOB_ID = createField(DSL.name("exchange_job_id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>public.exchange_job.job_type_id</code>. 1 - Buy, 2 -
     * Sell
     */
    public final TableField<ExchangeJobRecord, Long> JOB_TYPE_ID = createField(DSL.name("job_type_id"), SQLDataType.BIGINT, this, "1 - Buy, 2 - Sell");

    /**
     * The column <code>public.exchange_job.received_amount</code>.
     */
    public final TableField<ExchangeJobRecord, BigDecimal> RECEIVED_AMOUNT = createField(DSL.name("received_amount"), SQLDataType.NUMERIC(10, 2), this, "");

    /**
     * The column <code>public.exchange_job.received_currency_code</code>.
     */
    public final TableField<ExchangeJobRecord, Integer> RECEIVED_CURRENCY_CODE = createField(DSL.name("received_currency_code"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.exchange_job.exchange_rate_entry_id</code>.
     */
    public final TableField<ExchangeJobRecord, Long> EXCHANGE_RATE_ENTRY_ID = createField(DSL.name("exchange_rate_entry_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.exchange_job.middle_rate_amount</code>.
     */
    public final TableField<ExchangeJobRecord, BigDecimal> MIDDLE_RATE_AMOUNT = createField(DSL.name("middle_rate_amount"), SQLDataType.NUMERIC(10, 2), this, "");

    /**
     * The column <code>public.exchange_job.exchange_amount</code>.
     */
    public final TableField<ExchangeJobRecord, BigDecimal> EXCHANGE_AMOUNT = createField(DSL.name("exchange_amount"), SQLDataType.NUMERIC(10, 2), this, "");

    /**
     * The column
     * <code>public.exchange_job.exchange_cash_denomination_id</code>.
     */
    public final TableField<ExchangeJobRecord, Long> EXCHANGE_CASH_DENOMINATION_ID = createField(DSL.name("exchange_cash_denomination_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.exchange_job.user_id</code>.
     */
    public final TableField<ExchangeJobRecord, Long> USER_ID = createField(DSL.name("user_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>public.exchange_job.timestamp_of_creation</code>.
     */
    public final TableField<ExchangeJobRecord, OffsetDateTime> TIMESTAMP_OF_CREATION = createField(DSL.name("timestamp_of_creation"), SQLDataType.TIMESTAMPWITHTIMEZONE(6), this, "");

    /**
     * The column <code>public.exchange_job.date_of_creation</code>.
     */
    public final TableField<ExchangeJobRecord, LocalDate> DATE_OF_CREATION = createField(DSL.name("date_of_creation"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.exchange_job.time_of_creation</code>.
     */
    public final TableField<ExchangeJobRecord, LocalTime> TIME_OF_CREATION = createField(DSL.name("time_of_creation"), SQLDataType.LOCALTIME, this, "");

    private ExchangeJob(Name alias, Table<ExchangeJobRecord> aliased) {
        this(alias, aliased, null);
    }

    private ExchangeJob(Name alias, Table<ExchangeJobRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.exchange_job</code> table reference
     */
    public ExchangeJob(String alias) {
        this(DSL.name(alias), EXCHANGE_JOB);
    }

    /**
     * Create an aliased <code>public.exchange_job</code> table reference
     */
    public ExchangeJob(Name alias) {
        this(alias, EXCHANGE_JOB);
    }

    /**
     * Create a <code>public.exchange_job</code> table reference
     */
    public ExchangeJob() {
        this(DSL.name("exchange_job"), null);
    }

    public <O extends Record> ExchangeJob(Table<O> child, ForeignKey<O, ExchangeJobRecord> key) {
        super(child, key, EXCHANGE_JOB);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<ExchangeJobRecord, Long> getIdentity() {
        return (Identity<ExchangeJobRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<ExchangeJobRecord> getPrimaryKey() {
        return Keys.EXCHANGE_JOB_PKEY;
    }

    @Override
    public ExchangeJob as(String alias) {
        return new ExchangeJob(DSL.name(alias), this);
    }

    @Override
    public ExchangeJob as(Name alias) {
        return new ExchangeJob(alias, this);
    }

    @Override
    public ExchangeJob as(Table<?> alias) {
        return new ExchangeJob(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public ExchangeJob rename(String name) {
        return new ExchangeJob(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public ExchangeJob rename(Name name) {
        return new ExchangeJob(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public ExchangeJob rename(Table<?> name) {
        return new ExchangeJob(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row12 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row12<Long, Long, BigDecimal, Integer, Long, BigDecimal, BigDecimal, Long, Long, OffsetDateTime, LocalDate, LocalTime> fieldsRow() {
        return (Row12) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function12<? super Long, ? super Long, ? super BigDecimal, ? super Integer, ? super Long, ? super BigDecimal, ? super BigDecimal, ? super Long, ? super Long, ? super OffsetDateTime, ? super LocalDate, ? super LocalTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function12<? super Long, ? super Long, ? super BigDecimal, ? super Integer, ? super Long, ? super BigDecimal, ? super BigDecimal, ? super Long, ? super Long, ? super OffsetDateTime, ? super LocalDate, ? super LocalTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
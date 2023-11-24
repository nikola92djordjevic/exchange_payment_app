/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables;


import PlatniPrometDB.Keys;
import PlatniPrometDB.Public;
import PlatniPrometDB.tables.records.CurrencyRecord;

import java.util.function.Function;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function6;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row6;
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
public class Currency extends TableImpl<CurrencyRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.currency</code>
     */
    public static final Currency CURRENCY = new Currency();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CurrencyRecord> getRecordType() {
        return CurrencyRecord.class;
    }

    /**
     * The column <code>public.currency.currency_code</code>.
     */
    public final TableField<CurrencyRecord, String> CURRENCY_CODE = createField(DSL.name("currency_code"), SQLDataType.VARCHAR(3).nullable(false), this, "");

    /**
     * The column <code>public.currency.currency_name_code</code>.
     */
    public final TableField<CurrencyRecord, String> CURRENCY_NAME_CODE = createField(DSL.name("currency_name_code"), SQLDataType.VARCHAR(3).nullable(false), this, "");

    /**
     * The column <code>public.currency.full_name</code>.
     */
    public final TableField<CurrencyRecord, String> FULL_NAME = createField(DSL.name("full_name"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>public.currency.quantity</code>.
     */
    public final TableField<CurrencyRecord, Integer> QUANTITY = createField(DSL.name("quantity"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.currency.country</code>.
     */
    public final TableField<CurrencyRecord, String> COUNTRY = createField(DSL.name("country"), SQLDataType.VARCHAR(50).nullable(false), this, "");

    /**
     * The column <code>public.currency.sign_logo</code>.
     */
    public final TableField<CurrencyRecord, String> SIGN_LOGO = createField(DSL.name("sign_logo"), SQLDataType.VARCHAR(50), this, "");

    private Currency(Name alias, Table<CurrencyRecord> aliased) {
        this(alias, aliased, null);
    }

    private Currency(Name alias, Table<CurrencyRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.currency</code> table reference
     */
    public Currency(String alias) {
        this(DSL.name(alias), CURRENCY);
    }

    /**
     * Create an aliased <code>public.currency</code> table reference
     */
    public Currency(Name alias) {
        this(alias, CURRENCY);
    }

    /**
     * Create a <code>public.currency</code> table reference
     */
    public Currency() {
        this(DSL.name("currency"), null);
    }

    public <O extends Record> Currency(Table<O> child, ForeignKey<O, CurrencyRecord> key) {
        super(child, key, CURRENCY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public UniqueKey<CurrencyRecord> getPrimaryKey() {
        return Keys.CURRENCY_PKEY;
    }

    @Override
    public Currency as(String alias) {
        return new Currency(DSL.name(alias), this);
    }

    @Override
    public Currency as(Name alias) {
        return new Currency(alias, this);
    }

    @Override
    public Currency as(Table<?> alias) {
        return new Currency(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Currency rename(String name) {
        return new Currency(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Currency rename(Name name) {
        return new Currency(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Currency rename(Table<?> name) {
        return new Currency(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row6 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, String, Integer, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function6<? super String, ? super String, ? super String, ? super Integer, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function6<? super String, ? super String, ? super String, ? super Integer, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}

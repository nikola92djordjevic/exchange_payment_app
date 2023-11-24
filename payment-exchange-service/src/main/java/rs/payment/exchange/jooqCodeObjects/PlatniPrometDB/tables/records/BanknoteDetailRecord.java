/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.records;


import PlatniPrometDB.tables.BanknoteDetail;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record14;
import org.jooq.Row14;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BanknoteDetailRecord extends UpdatableRecordImpl<BanknoteDetailRecord> implements Record14<Long, Long, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.banknote_detail.banknote_detail_id</code>.
     */
    public void setBanknoteDetailId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.banknote_detail.banknote_detail_id</code>.
     */
    public Long getBanknoteDetailId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.banknote_detail.balance_entry_id</code>.
     */
    public void setBalanceEntryId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.banknote_detail.balance_entry_id</code>.
     */
    public Long getBalanceEntryId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.banknote_detail.x1</code>.
     */
    public void setX1(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x1</code>.
     */
    public Integer getX1() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.banknote_detail.x2</code>.
     */
    public void setX2(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x2</code>.
     */
    public Integer getX2() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>public.banknote_detail.x5</code>.
     */
    public void setX5(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x5</code>.
     */
    public Integer getX5() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.banknote_detail.x10</code>.
     */
    public void setX10(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x10</code>.
     */
    public Integer getX10() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>public.banknote_detail.x20</code>.
     */
    public void setX20(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x20</code>.
     */
    public Integer getX20() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>public.banknote_detail.x50</code>.
     */
    public void setX50(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x50</code>.
     */
    public Integer getX50() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>public.banknote_detail.x100</code>.
     */
    public void setX100(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x100</code>.
     */
    public Integer getX100() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>public.banknote_detail.x200</code>.
     */
    public void setX200(Integer value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x200</code>.
     */
    public Integer getX200() {
        return (Integer) get(9);
    }

    /**
     * Setter for <code>public.banknote_detail.x500</code>.
     */
    public void setX500(Integer value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x500</code>.
     */
    public Integer getX500() {
        return (Integer) get(10);
    }

    /**
     * Setter for <code>public.banknote_detail.x1000</code>.
     */
    public void setX1000(Integer value) {
        set(11, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x1000</code>.
     */
    public Integer getX1000() {
        return (Integer) get(11);
    }

    /**
     * Setter for <code>public.banknote_detail.x2000</code>.
     */
    public void setX2000(Integer value) {
        set(12, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x2000</code>.
     */
    public Integer getX2000() {
        return (Integer) get(12);
    }

    /**
     * Setter for <code>public.banknote_detail.x5000</code>.
     */
    public void setX5000(Integer value) {
        set(13, value);
    }

    /**
     * Getter for <code>public.banknote_detail.x5000</code>.
     */
    public Integer getX5000() {
        return (Integer) get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record14 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row14<Long, Long, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    @Override
    public Row14<Long, Long, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> valuesRow() {
        return (Row14) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return BanknoteDetail.BANKNOTE_DETAIL.BANKNOTE_DETAIL_ID;
    }

    @Override
    public Field<Long> field2() {
        return BanknoteDetail.BANKNOTE_DETAIL.BALANCE_ENTRY_ID;
    }

    @Override
    public Field<Integer> field3() {
        return BanknoteDetail.BANKNOTE_DETAIL.X1;
    }

    @Override
    public Field<Integer> field4() {
        return BanknoteDetail.BANKNOTE_DETAIL.X2;
    }

    @Override
    public Field<Integer> field5() {
        return BanknoteDetail.BANKNOTE_DETAIL.X5;
    }

    @Override
    public Field<Integer> field6() {
        return BanknoteDetail.BANKNOTE_DETAIL.X10;
    }

    @Override
    public Field<Integer> field7() {
        return BanknoteDetail.BANKNOTE_DETAIL.X20;
    }

    @Override
    public Field<Integer> field8() {
        return BanknoteDetail.BANKNOTE_DETAIL.X50;
    }

    @Override
    public Field<Integer> field9() {
        return BanknoteDetail.BANKNOTE_DETAIL.X100;
    }

    @Override
    public Field<Integer> field10() {
        return BanknoteDetail.BANKNOTE_DETAIL.X200;
    }

    @Override
    public Field<Integer> field11() {
        return BanknoteDetail.BANKNOTE_DETAIL.X500;
    }

    @Override
    public Field<Integer> field12() {
        return BanknoteDetail.BANKNOTE_DETAIL.X1000;
    }

    @Override
    public Field<Integer> field13() {
        return BanknoteDetail.BANKNOTE_DETAIL.X2000;
    }

    @Override
    public Field<Integer> field14() {
        return BanknoteDetail.BANKNOTE_DETAIL.X5000;
    }

    @Override
    public Long component1() {
        return getBanknoteDetailId();
    }

    @Override
    public Long component2() {
        return getBalanceEntryId();
    }

    @Override
    public Integer component3() {
        return getX1();
    }

    @Override
    public Integer component4() {
        return getX2();
    }

    @Override
    public Integer component5() {
        return getX5();
    }

    @Override
    public Integer component6() {
        return getX10();
    }

    @Override
    public Integer component7() {
        return getX20();
    }

    @Override
    public Integer component8() {
        return getX50();
    }

    @Override
    public Integer component9() {
        return getX100();
    }

    @Override
    public Integer component10() {
        return getX200();
    }

    @Override
    public Integer component11() {
        return getX500();
    }

    @Override
    public Integer component12() {
        return getX1000();
    }

    @Override
    public Integer component13() {
        return getX2000();
    }

    @Override
    public Integer component14() {
        return getX5000();
    }

    @Override
    public Long value1() {
        return getBanknoteDetailId();
    }

    @Override
    public Long value2() {
        return getBalanceEntryId();
    }

    @Override
    public Integer value3() {
        return getX1();
    }

    @Override
    public Integer value4() {
        return getX2();
    }

    @Override
    public Integer value5() {
        return getX5();
    }

    @Override
    public Integer value6() {
        return getX10();
    }

    @Override
    public Integer value7() {
        return getX20();
    }

    @Override
    public Integer value8() {
        return getX50();
    }

    @Override
    public Integer value9() {
        return getX100();
    }

    @Override
    public Integer value10() {
        return getX200();
    }

    @Override
    public Integer value11() {
        return getX500();
    }

    @Override
    public Integer value12() {
        return getX1000();
    }

    @Override
    public Integer value13() {
        return getX2000();
    }

    @Override
    public Integer value14() {
        return getX5000();
    }

    @Override
    public BanknoteDetailRecord value1(Long value) {
        setBanknoteDetailId(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value2(Long value) {
        setBalanceEntryId(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value3(Integer value) {
        setX1(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value4(Integer value) {
        setX2(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value5(Integer value) {
        setX5(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value6(Integer value) {
        setX10(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value7(Integer value) {
        setX20(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value8(Integer value) {
        setX50(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value9(Integer value) {
        setX100(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value10(Integer value) {
        setX200(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value11(Integer value) {
        setX500(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value12(Integer value) {
        setX1000(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value13(Integer value) {
        setX2000(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord value14(Integer value) {
        setX5000(value);
        return this;
    }

    @Override
    public BanknoteDetailRecord values(Long value1, Long value2, Integer value3, Integer value4, Integer value5, Integer value6, Integer value7, Integer value8, Integer value9, Integer value10, Integer value11, Integer value12, Integer value13, Integer value14) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BanknoteDetailRecord
     */
    public BanknoteDetailRecord() {
        super(BanknoteDetail.BANKNOTE_DETAIL);
    }

    /**
     * Create a detached, initialised BanknoteDetailRecord
     */
    public BanknoteDetailRecord(Long banknoteDetailId, Long balanceEntryId, Integer x1, Integer x2, Integer x5, Integer x10, Integer x20, Integer x50, Integer x100, Integer x200, Integer x500, Integer x1000, Integer x2000, Integer x5000) {
        super(BanknoteDetail.BANKNOTE_DETAIL);

        setBanknoteDetailId(banknoteDetailId);
        setBalanceEntryId(balanceEntryId);
        setX1(x1);
        setX2(x2);
        setX5(x5);
        setX10(x10);
        setX20(x20);
        setX50(x50);
        setX100(x100);
        setX200(x200);
        setX500(x500);
        setX1000(x1000);
        setX2000(x2000);
        setX5000(x5000);
    }

    /**
     * Create a detached, initialised BanknoteDetailRecord
     */
    public BanknoteDetailRecord(PlatniPrometDB.tables.pojos.BanknoteDetail value) {
        super(BanknoteDetail.BANKNOTE_DETAIL);

        if (value != null) {
            setBanknoteDetailId(value.getBanknoteDetailId());
            setBalanceEntryId(value.getBalanceEntryId());
            setX1(value.getX1());
            setX2(value.getX2());
            setX5(value.getX5());
            setX10(value.getX10());
            setX20(value.getX20());
            setX50(value.getX50());
            setX100(value.getX100());
            setX200(value.getX200());
            setX500(value.getX500());
            setX1000(value.getX1000());
            setX2000(value.getX2000());
            setX5000(value.getX5000());
        }
    }
}
/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB.tables.records;


import PlatniPrometDB.tables.JobType;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class JobTypeRecord extends UpdatableRecordImpl<JobTypeRecord> implements Record3<Long, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.job_type.job_type_id</code>.
     */
    public void setJobTypeId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.job_type.job_type_id</code>.
     */
    public Long getJobTypeId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.job_type.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.job_type.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.job_type.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.job_type.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return JobType.JOB_TYPE.JOB_TYPE_ID;
    }

    @Override
    public Field<String> field2() {
        return JobType.JOB_TYPE.NAME;
    }

    @Override
    public Field<String> field3() {
        return JobType.JOB_TYPE.DESCRIPTION;
    }

    @Override
    public Long component1() {
        return getJobTypeId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public Long value1() {
        return getJobTypeId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public JobTypeRecord value1(Long value) {
        setJobTypeId(value);
        return this;
    }

    @Override
    public JobTypeRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public JobTypeRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public JobTypeRecord values(Long value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached JobTypeRecord
     */
    public JobTypeRecord() {
        super(JobType.JOB_TYPE);
    }

    /**
     * Create a detached, initialised JobTypeRecord
     */
    public JobTypeRecord(Long jobTypeId, String name, String description) {
        super(JobType.JOB_TYPE);

        setJobTypeId(jobTypeId);
        setName(name);
        setDescription(description);
    }

    /**
     * Create a detached, initialised JobTypeRecord
     */
    public JobTypeRecord(PlatniPrometDB.tables.pojos.JobType value) {
        super(JobType.JOB_TYPE);

        if (value != null) {
            setJobTypeId(value.getJobTypeId());
            setName(value.getName());
            setDescription(value.getDescription());
        }
    }
}

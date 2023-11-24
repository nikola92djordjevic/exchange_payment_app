/*
 * This file is generated by jOOQ.
 */
package PlatniPrometDB;


import PlatniPrometDB.tables.BalanceEntry;
import PlatniPrometDB.tables.BanknoteDetail;
import PlatniPrometDB.tables.CashRegister;
import PlatniPrometDB.tables.Currency;
import PlatniPrometDB.tables.ExchangeCashDenomination;
import PlatniPrometDB.tables.ExchangeJob;
import PlatniPrometDB.tables.ExchangeRate;
import PlatniPrometDB.tables.ExchangeRateEntry;
import PlatniPrometDB.tables.JobType;
import PlatniPrometDB.tables.Organisation;
import PlatniPrometDB.tables.Role;
import PlatniPrometDB.tables.User;
import PlatniPrometDB.tables.UserAuth;
import PlatniPrometDB.tables.UserPersonalInfo;
import PlatniPrometDB.tables.WorkPlace;
import PlatniPrometDB.tables.records.BalanceEntryRecord;
import PlatniPrometDB.tables.records.BanknoteDetailRecord;
import PlatniPrometDB.tables.records.CashRegisterRecord;
import PlatniPrometDB.tables.records.CurrencyRecord;
import PlatniPrometDB.tables.records.ExchangeCashDenominationRecord;
import PlatniPrometDB.tables.records.ExchangeJobRecord;
import PlatniPrometDB.tables.records.ExchangeRateEntryRecord;
import PlatniPrometDB.tables.records.ExchangeRateRecord;
import PlatniPrometDB.tables.records.JobTypeRecord;
import PlatniPrometDB.tables.records.OrganisationRecord;
import PlatniPrometDB.tables.records.RoleRecord;
import PlatniPrometDB.tables.records.UserAuthRecord;
import PlatniPrometDB.tables.records.UserPersonalInfoRecord;
import PlatniPrometDB.tables.records.UserRecord;
import PlatniPrometDB.tables.records.WorkPlaceRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<BalanceEntryRecord> BALANCE_ENTRY_PKEY = Internal.createUniqueKey(BalanceEntry.BALANCE_ENTRY, DSL.name("balance_entry_pkey"), new TableField[] { BalanceEntry.BALANCE_ENTRY.BALANCE_ENTRY_ID }, true);
    public static final UniqueKey<BanknoteDetailRecord> BANKNOTE_DETAIL_PKEY = Internal.createUniqueKey(BanknoteDetail.BANKNOTE_DETAIL, DSL.name("banknote_detail_pkey"), new TableField[] { BanknoteDetail.BANKNOTE_DETAIL.BANKNOTE_DETAIL_ID }, true);
    public static final UniqueKey<CashRegisterRecord> CASH_REGISTER_NAME_KEY = Internal.createUniqueKey(CashRegister.CASH_REGISTER, DSL.name("cash_register_name_key"), new TableField[] { CashRegister.CASH_REGISTER.NAME }, true);
    public static final UniqueKey<CashRegisterRecord> CASH_REGISTER_PKEY = Internal.createUniqueKey(CashRegister.CASH_REGISTER, DSL.name("cash_register_pkey"), new TableField[] { CashRegister.CASH_REGISTER.CASH_REGISTER_ID }, true);
    public static final UniqueKey<CurrencyRecord> CURRENCY_PKEY = Internal.createUniqueKey(Currency.CURRENCY, DSL.name("currency_pkey"), new TableField[] { Currency.CURRENCY.CURRENCY_CODE }, true);
    public static final UniqueKey<ExchangeCashDenominationRecord> EXCHANGE_CASH_DENOMINATION_PKEY = Internal.createUniqueKey(ExchangeCashDenomination.EXCHANGE_CASH_DENOMINATION, DSL.name("exchange_cash_denomination_pkey"), new TableField[] { ExchangeCashDenomination.EXCHANGE_CASH_DENOMINATION.EXCHANGE_CASH_DENOMINATION_ID }, true);
    public static final UniqueKey<ExchangeJobRecord> EXCHANGE_JOB_PKEY = Internal.createUniqueKey(ExchangeJob.EXCHANGE_JOB, DSL.name("exchange_job_pkey"), new TableField[] { ExchangeJob.EXCHANGE_JOB.EXCHANGE_JOB_ID }, true);
    public static final UniqueKey<ExchangeRateRecord> EXCHANGE_RATE_PKEY = Internal.createUniqueKey(ExchangeRate.EXCHANGE_RATE, DSL.name("exchange_rate_pkey"), new TableField[] { ExchangeRate.EXCHANGE_RATE.EXCHANGE_RATE_ID }, true);
    public static final UniqueKey<ExchangeRateEntryRecord> EXCHANGE_RATE_ENTRY_PKEY = Internal.createUniqueKey(ExchangeRateEntry.EXCHANGE_RATE_ENTRY, DSL.name("exchange_rate_entry_pkey"), new TableField[] { ExchangeRateEntry.EXCHANGE_RATE_ENTRY.EXCHANGE_RATE_ENTRY_ID }, true);
    public static final UniqueKey<JobTypeRecord> JOB_TYPE_PKEY = Internal.createUniqueKey(JobType.JOB_TYPE, DSL.name("job_type_pkey"), new TableField[] { JobType.JOB_TYPE.JOB_TYPE_ID }, true);
    public static final UniqueKey<OrganisationRecord> ORGANISATION_PKEY = Internal.createUniqueKey(Organisation.ORGANISATION, DSL.name("organisation_pkey"), new TableField[] { Organisation.ORGANISATION.ORGANISATION_ID }, true);
    public static final UniqueKey<RoleRecord> ROLE_PKEY = Internal.createUniqueKey(Role.ROLE, DSL.name("role_pkey"), new TableField[] { Role.ROLE.ROLE_ID }, true);
    public static final UniqueKey<UserRecord> USER_PKEY = Internal.createUniqueKey(User.USER, DSL.name("user_pkey"), new TableField[] { User.USER.USER_ID }, true);
    public static final UniqueKey<UserAuthRecord> USER_AUTH_PKEY = Internal.createUniqueKey(UserAuth.USER_AUTH, DSL.name("user_auth_pkey"), new TableField[] { UserAuth.USER_AUTH.USER_ID }, true);
    public static final UniqueKey<UserPersonalInfoRecord> USER_PERSONAL_INFO_PKEY = Internal.createUniqueKey(UserPersonalInfo.USER_PERSONAL_INFO, DSL.name("user_personal_info_pkey"), new TableField[] { UserPersonalInfo.USER_PERSONAL_INFO.USER_ID }, true);
    public static final UniqueKey<WorkPlaceRecord> WORK_PLACE_PKEY = Internal.createUniqueKey(WorkPlace.WORK_PLACE, DSL.name("work_place_pkey"), new TableField[] { WorkPlace.WORK_PLACE.WORK_PLACE_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<UserPersonalInfoRecord, UserRecord> USER_PERSONAL_INFO__USER_PERSONAL_INFO_FK = Internal.createForeignKey(UserPersonalInfo.USER_PERSONAL_INFO, DSL.name("user_personal_info_fk"), new TableField[] { UserPersonalInfo.USER_PERSONAL_INFO.USER_ID }, Keys.USER_PKEY, new TableField[] { User.USER.USER_ID }, true);
}

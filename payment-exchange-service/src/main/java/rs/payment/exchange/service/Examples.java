package rs.payment.exchange.service;

import PlatniPrometDB.tables.daos.OrganisationDao;
import PlatniPrometDB.tables.records.OrganisationRecord;
import PlatniPrometDB.tables.records.UserRecord;
import org.jooq.*;
import org.jooq.Record;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static PlatniPrometDB.Sequences.USER_SEQ;
import static PlatniPrometDB.Tables.*;
import static org.jooq.impl.DSL.count;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;

public class Examples {
    @Autowired
    DSLContext create;


    public void examples() throws IOException {



        /*final Record inputRec = db.newRecord(Tables.ORGANISATION);
        inputRec.from(organisation);
        //final List<Field<?>> fields = new ArrayList<>();
        final List<Object> values = new ArrayList<>();
        for (Field<?> f : inputRec.fields()) {
            final Object v = inputRec.getValue(f);
            if (v != null) { *//*fields.add(f);*//* values.add(v); }
        }
        db.insertInto(Tables.ORGANISATION).values(values).execute();*/
/*
        OrganisationDao organisationDao = new OrganisationDao(create.configuration());
        //Organisation organisation1 = organisationDao.findById(1);


        create.select(USER_PERSONAL_INFO.FIRST_NAME, USER_PERSONAL_INFO.LAST_NAME, count())
                .from(USER)
                .join(USER_PERSONAL_INFO).on(USER.USER_ID.eq(USER_PERSONAL_INFO.USER_ID))
                .where(BOOK.LANGUAGE.eq("DE"))
                .and(BOOK.PUBLISHED_IN.gt(2008))
                .groupBy(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .orderBy(USER.USERNAME.asc().nullsFirst())
                .fetch();


        create.select(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, count())
                .from(AUTHOR)
                .join(BOOK).on(BOOK.AUTHOR_ID.eq(AUTHOR.ID))
                .where(BOOK.LANGUAGE.eq("DE"))
                .and(BOOK.PUBLISHED_IN.gt(2008))
                .groupBy(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .orderBy(AUTHOR.LAST_NAME.asc().nullsFirst())
                .fetch();



        Record record1 =
                create.insertInto(AUTHOR, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                        .values("Charlotte", "Roche")
                        .returningResult(AUTHOR.ID)
                        .fetchOne();

        create.insertInto(AUTHOR,
                        AUTHOR.ID, AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME)
                .values(100, "Hermann", "Hesse")
                .values(101, "Alfred", "Döblin")
                .execute();

        String title = create.update(BOOK)
                .set(BOOK.TITLE, "Animal Farm")
                .where(BOOK.ID.eq(5))
                .returningResult(BOOK.TITLE)
                .fetchOne().getValue(BOOK.TITLE);

        create.delete(AUTHOR)
                .where(AUTHOR.ID.eq(100))
                .execute();

        create.mergeInto(USER)
                .using(create.selectOne())
                .on(USER.USERNAME.eq("Hitchcock"))
                .whenMatchedThenUpdate()
                .set(USER.USERNAME, "John")
                .whenNotMatchedThenInsert(USER.USERNAME)
                .values("Hitchcock")
                .execute();

        create.mergeInto(AUTHOR)
                .using(create.selectOne())
                .on(AUTHOR.LAST_NAME.eq("Hitchcock"))
                .whenMatchedThenUpdate()
                .set(AUTHOR.FIRST_NAME, "John")
                .whenNotMatchedThenInsert(AUTHOR.LAST_NAME)
                .values("Hitchcock")
                .execute();


        create.mergeInto(AUTHOR)
                .using(create.selectOne())
                .on(AUTHOR.LAST_NAME.eq("Hitchcock"))
                .whenMatchedThenUpdate()
                .set(AUTHOR.FIRST_NAME, "John")
                .whenNotMatchedThenInsert(AUTHOR.LAST_NAME)
                .values("Hitchcock")
                .execute();

        // Define privileges
        Privilege select = privilege("select");
        Privilege insert = privilege("insert");
        User user1 = user("user");
        // Grant privileges to a given user or role
        create.grant(select, insert).on(BALANCE_ENTRY).to(user).execute();
        create.revoke(select, insert).on(BALANCE_ENTRY).from(user).execute();
        // Grant privileges to everyone
        create.grant(select, insert).on(BALANCE_ENTRY).toPublic().execute();
        create.revoke(select, insert).on(BALANCE_ENTRY).fromPublic().execute();


        // Kreiranje nove tabele
        create.createTable("table")
                .column("column1", INTEGER.default_(1))
                .execute();
        // Ažuriranje tabele dodavanje novih kolona
        create.alterTable("table").add(field(name("column2"), INTEGER),
                field(name("column3"), VARCHAR)).execute();
        // Dodavanje primarnog ključa u tabeli
        create.alterTable("table").add(constraint("table_pk")
                .primaryKey("column1")).execute();
        // Drop tabele
        create.dropTable("table").execute();


        // Adding a single column to a table
        create.alterTable("table").add("column", INTEGER).execute();
        // Adding several columns to a table in one go
        create.alterTable("table").add(field(name("column1"), INTEGER), field(name("column2"), INTEGER)).execute();
        // Adding an unnamed constraint to a table
        create.alterTable("table").add(primaryKey("id")).execute();
        create.alterTable("table").add(unique("user_name")).execute();
        create.alterTable("table").add(foreignKey("author_id").references("author")).execute();
        create.alterTable("table").add(check(length(field(name("user_name"), VARCHAR)).gt(5))).execute();
        // Adding a named constraint to a table
        create.alterTable("table").add(constraint("pk").primaryKey("id")).execute();
        create.alterTable("table").add(constraint("uk").unique("user_name")).execute();
        create.alterTable("table").add(constraint("fk").foreignKey("author_id").references("author")).execute();
        create.alterTable("table").add(constraint("ck").check(length(field(name("user_name"), VARCHAR)).gt(5))).execute();


        String user1 = create.fetchValue(create.select(USER.USER_ID)
                .from(USER).where(USER.USER_ID.eq(1l)));



        assertEquals(v1.Public.PUBLIC, v2.Public.PUBLIC);

        List<Table<?>> tables1 = v1.Public.PUBLIC.getTables();
        List<Table<?>> tables2 = v2.Public.PUBLIC.getTables();
        assertEquals(tables1, tables2);

        for (int i = 0; i < tables1.size(); i++) {
            Table<?> table1 = tables1.get(i);
            Table<?> table2 = tables2.get(i);

            assertArrayEquals(format("Fields differ for %s and %s", table1, table2),
                    table1.fields(), table2.fields());
            assertEquals(format("Primary keys differ for %s and %s", table1, table2),
                    table1.getPrimaryKey(), table2.getPrimaryKey());
            assertEquals(format("Schemas differ for %s and %s", table1, table2),
                    table1.getSchema(), table2.getSchema());
            assertEquals(format("Identities differ for %s and %s", table1, table2),
                    table1.getIdentity(), table2.getIdentity());
            assertEquals(format("Keys differ for %s and %s", table1, table2),
                    table1.getKeys(), table2.getKeys());
            assertEquals(format("References differ for %s and %s", table1, table2),
                    table1.getReferences(), table2.getReferences());
        }



// We're using interpreted Meta objects. But any other type of Meta can be used, too
        Meta m1 = create.meta("create table t (i int)");
        Meta m2 = create.meta("create table t (i int, j int)");
// The diff is now printed in both directions:
        System.out.println("-- Schema upgrade");
        System.out.println(m1.migrateTo(m2));
        System.out.println();
        System.out.println("-- Schema downgrade");
        System.out.println(m2.migrateTo(m1));




        Long userID = create.nextval(USER_SEQ);




        String csv1 = create.selectFrom(BALANCE_ENTRY)
                .where(BALANCE_ENTRY.CASH_REGISTER_ID.eq(1l)).fetch().formatCSV();

        String json = create.selectFrom(USER)
                .where(USER.ORGANISATION_ID.eq(1l)).fetch().formatJSON();

        String xml = create.selectFrom(EXCHANGE_RATE)
                .orderBy(EXCHANGE_RATE.TIMESTAMP_OF_CREATION).fetch().formatXML();

        String html = create.selectFrom(USER).fetch().formatHTML();


        File inputStream = new File("cc") ;

        create.loadInto(CURRENCY)
                .onDuplicateKeyUpdate()
                .loadCSV(inputStream)
                .fields(CURRENCY.CURRENCY_CODE, CURRENCY.FULL_NAME, CURRENCY.COUNTRY).execute();

        create.loadInto(BALANCE_ENTRY)
                .batchAfter(5000)
                .loadCSV(inputStream)
                .fields(BALANCE_ENTRY.BALANCE_ENTRY_ID, BALANCE_ENTRY.CURRENCY_CODE, BALANCE_ENTRY.AMOUNT, BALANCE_ENTRY.TIMESTAMP_OF_CREATION)
                .execute();


        // Prikupljanje iz jedne tabele
        OrganisationRecord organisation = create.selectFrom(ORGANISATION)
                .where(ORGANISATION.ORGANISATION_ID.eq(1l)).fetchOne();

        // Prikupljanje rezultata iz dve tabele
        Record record = create.select()
                .from(USER)
                .join(ORGANISATION).on(USER.ORGANISATION_ID.eq(ORGANISATION.ORGANISATION_ID))
                .where(USER.USER_ID.eq(1l))
                .fetchOne();
        // "extract" the two individual strongly typed TableRecord types from the denormalised Record:
        UserRecord user = record.into(USER);
        OrganisationRecord organisation = record.into(ORGANISATION);



        // Primer izgleda POJO objekta
        public class Role {
            @Column(name = "ID")
            public int myId;
            @Column(name = "TITLE")
            public String myTitle;
        }
        // Različite "into()" metode koje omogućuju snimanje rezultata u POJO objekte
        Role myRole = create.select().from(ROLE).fetchAny().into(Role.class);
        List<Role> myRoles = create.select().from(ROLE).fetch().into(Role.class);
        List<Role> myRoles = create.select().from(ROLE).fetchInto(Role.class);


        PlatniPrometDB.tables.pojos.Role role = new PlatniPrometDB.tables.pojos.Role();

        create.begin(
                begin(
                        insertInto(ROLE).columns(ROLE.ROLE_ID, ROLE.ROLE_NAME).values(1l,"Admin"),
                        insertInto(ROLE).columns(ROLE.ROLE_ID, ROLE.ROLE_NAME).values(2l,"Manager"),
                        insertInto(ROLE).columns(ROLE.ROLE_ID, ROLE.ROLE_NAME).values(3l,"User"),
                )
        ).execute();


        // OrganisationRecord record = db.fetchOne(ORGANISATION, ORGANISATION.ORGANISATION_ID.eq("0001"));
        //assert record != null;
        //record.setOrganisationId("1245");
        //record.setAddress("NovaAdresa11");

        //      OrganisationDao org = new OrganisationDao(record);

        //record.merge();


        List<OrganisationRecord> records = this.create.selectFrom(ORGANISATION).where(PlatniPrometDB.tables.Organisation.ORGANISATION.ORGANISATION_ID.like("00%")).fetch();

        for (OrganisationRecord org : records) {
            org.setCity(org.getCity() + "55");
        }

        records.forEach(UpdatableRecordImpl::update);
        /*OrganisationRecord record = new OrganisationRecord(organisation);

        record.merge();
        record.insert();*/


        read(ORGANISATION.getClass());

        // db.insertInto(PlatniPrometDB.tables.Organisation.ORGANISATION).values(organisation.getOrganisationCode(), organisation.getName());


    }

    public <T> void read(T className) {


        Result<Record> records = create.selectFrom((Name) className).where(PlatniPrometDB.tables.Organisation.ORGANISATION.ORGANISATION_ID.like("00%")).fetch();
        if (records != null) {

        }
    }
}

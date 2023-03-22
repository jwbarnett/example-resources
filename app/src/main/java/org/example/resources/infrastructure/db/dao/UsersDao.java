package org.example.resources.infrastructure.db.dao;

import static org.example.resources.infrastructure.db.table.users.Tables.USER;

import org.example.resources.infrastructure.db.table.users.tables.records.UserRecord;
import org.jooq.*;
import org.jooq.impl.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class UsersDao {

    private static final List<String> SAMPLE_NAMES = List.of(
            "Bob Belcher",
            "Bart Simpson",
            "Doctor Zoidberg",
            "Ted Lasso"
    );

    private final DSLContext context;

    public UsersDao(DataSource dataSource) {
        context = DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    public void generateUser() {
        int randomIndex = ThreadLocalRandom.current().nextInt(SAMPLE_NAMES.size() - 1);

        context.insertInto(USER)
                .set(new UserRecord(UUID.randomUUID(), SAMPLE_NAMES.get(randomIndex)))
                .execute();
    }

}

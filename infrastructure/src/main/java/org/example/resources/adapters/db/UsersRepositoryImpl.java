package org.example.resources.adapters.db;

import org.example.resources.adapters.db.table.users.tables.records.UserRecord;
import org.example.resources.domain.entity.User;
import org.example.resources.domain.entity.type.UserId;
import org.example.resources.ports.db.UsersRepository;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static org.example.resources.adapters.db.table.users.Tables.USER;

public class UsersRepositoryImpl implements UsersRepository {

    private static final List<String> SAMPLE_NAMES = List.of(
            "Bob Belcher",
            "Bart Simpson",
            "Doctor Zoidberg",
            "Ted Lasso"
    );

    private final DSLContext context;
    private final Supplier<UUID> uuidSupplier;

    public UsersRepositoryImpl(DataSource dataSource, Supplier<UUID> uuidSupplier) {
        context = DSL.using(dataSource, SQLDialect.POSTGRES);
        this.uuidSupplier = uuidSupplier;
    }

    public User generateUser() {
        int randomIndex = ThreadLocalRandom.current().nextInt(SAMPLE_NAMES.size() - 1);
        UUID newId = uuidSupplier.get();

        context.insertInto(USER)
                .set(new UserRecord(newId, SAMPLE_NAMES.get(randomIndex)))
                .execute();

        return get(new UserId(newId)).orElseThrow();
    }

    public List<User> getAll() {
        return context.selectFrom(USER)
                .stream()
                .map(record -> new User(new UserId(record.getId()), record.getName()))
                .toList();
    }

    public Optional<User> get(UserId userId) {
        Optional<UserRecord> retrievedUser = context.selectFrom(USER)
                .where(USER.ID.eq(userId.getUUID()))
                .fetchOptional();

        return retrievedUser.map(record -> new User(new UserId(record.getId()), record.getName()));
    }

}

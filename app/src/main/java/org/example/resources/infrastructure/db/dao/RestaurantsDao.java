package org.example.resources.infrastructure.db.dao;

import org.example.resources.infrastructure.db.table.restaurants.tables.records.RestaurantRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.example.resources.infrastructure.db.table.restaurants.Tables.RESTAURANT;

public class RestaurantsDao {

    private static final List<String> SAMPLE_NAMES = List.of(
            "Bob's Burgers",
            "JJ's Diner",
            "Paddy's Irish Pub",
            "Central Perk",
            "Pizza Planet",
            "MacLaren's Pub",
            "La Ratatouille");

    private final DSLContext context;

    public RestaurantsDao(DataSource dataSource) {
        context = DSL.using(dataSource, SQLDialect.POSTGRES);
    }

    public void generateRestaurant() {
        int randomIndex = ThreadLocalRandom.current().nextInt(SAMPLE_NAMES.size() - 1);

        context.insertInto(RESTAURANT)
                .set(new RestaurantRecord(UUID.randomUUID(), SAMPLE_NAMES.get(randomIndex)))
                .execute();
    }

    public List<RestaurantRecord> getAll() {
        return context.selectFrom(RESTAURANT)
                .stream()
                .toList();
    }

    public RestaurantRecord get(UUID id) {
        return context.selectFrom(RESTAURANT)
                .where(RESTAURANT.ID.eq(id))
                .fetchAny();
    }
}
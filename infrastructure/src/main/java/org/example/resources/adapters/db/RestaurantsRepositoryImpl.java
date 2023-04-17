package org.example.resources.adapters.db;

import org.example.resources.adapters.db.table.restaurants.tables.records.RestaurantRecord;
import org.example.resources.domain.entity.Restaurant;
import org.example.resources.domain.entity.type.RestaurantId;
import org.example.resources.ports.db.RestaurantsRepository;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static org.example.resources.adapters.db.table.restaurants.Tables.RESTAURANT;

public class RestaurantsRepositoryImpl implements RestaurantsRepository {

    private static final List<String> SAMPLE_NAMES = List.of(
            "Bob's Burgers",
            "JJ's Diner",
            "Paddy's Irish Pub",
            "Central Perk",
            "Pizza Planet",
            "MacLaren's Pub",
            "La Ratatouille");

    private final DSLContext context;
    private final Supplier<UUID> uuidSupplier;

    public RestaurantsRepositoryImpl(DataSource dataSource, Supplier<UUID> uuidSupplier) {
        context = DSL.using(dataSource, SQLDialect.POSTGRES);
        this.uuidSupplier = uuidSupplier;
    }

    public Restaurant generateRestaurant() {
        int randomIndex = ThreadLocalRandom.current().nextInt(SAMPLE_NAMES.size() - 1);
        UUID newId = uuidSupplier.get();

        context.insertInto(RESTAURANT)
                .set(new RestaurantRecord(newId, SAMPLE_NAMES.get(randomIndex)))
                .execute();

        return get(new RestaurantId(newId)).orElseThrow();
    }

    public List<Restaurant> getAll() {
        return context.selectFrom(RESTAURANT)
                .stream()
                .map(record -> new Restaurant(new RestaurantId(record.getId()), record.getName()))
                .toList();
    }

    public Optional<Restaurant> get(RestaurantId restaurantId) {
        Optional<RestaurantRecord> retrievedRestaurant = context.selectFrom(RESTAURANT)
                .where(RESTAURANT.ID.eq(restaurantId.getUUID()))
                .fetchOptional();

        return retrievedRestaurant.map(record -> new Restaurant(new RestaurantId(record.getId()), record.getName()));
    }

}

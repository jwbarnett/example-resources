package org.example.resources.adapters.db;

import org.example.resources.domain.entity.Restaurant;
import org.example.resources.domain.entity.type.RestaurantId;
import org.example.resources.ports.db.RestaurantsRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static com.github.npathai.hamcrestopt.OptionalMatchers.isEmpty;
import static com.github.npathai.hamcrestopt.OptionalMatchers.isPresent;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class RestaurantsRepositoryTest extends PostgresqlTest {

    private static final UUID generatedUUID = UUID.randomUUID();

    private final RestaurantsRepository underTest = new RestaurantsRepositoryImpl(DATASOURCE, () -> generatedUUID);

    @Test
    void generateARestaurant() {
        Restaurant generatedRestaurant = underTest.generateRestaurant();
        assertThat(generatedRestaurant, notNullValue());
    }

    @Test
    void retrieveARestaurantWhenNotPresent() {
        Optional<Restaurant> retrievedRestaurant = underTest.get(new RestaurantId(generatedUUID));
        assertThat(retrievedRestaurant, isEmpty());
    }

    @Test
    void retrieveACreatedRestaurant() {
        Restaurant generatedRestaurant = underTest.generateRestaurant();
        Optional<Restaurant> retrievedRestaurant = underTest.get(new RestaurantId(generatedUUID));
        assertThat(retrievedRestaurant, isPresent());
        assertThat(retrievedRestaurant.get().getName(), equalTo(generatedRestaurant.getName()));
    }

}

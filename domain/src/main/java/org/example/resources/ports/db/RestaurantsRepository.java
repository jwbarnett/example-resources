package org.example.resources.ports.db;

import org.example.resources.domain.entity.Restaurant;
import org.example.resources.domain.entity.type.RestaurantId;

import java.util.List;
import java.util.Optional;

public interface RestaurantsRepository {

    Restaurant generateRestaurant();

    List<Restaurant> getAll();

    Optional<Restaurant> get(RestaurantId restaurantId);

}

package org.example.resources.domain.entity;

import org.example.resources.domain.entity.type.RestaurantId;

public class Restaurant {

    private final RestaurantId restaurantId;
    private String name;

    public Restaurant(RestaurantId restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

}
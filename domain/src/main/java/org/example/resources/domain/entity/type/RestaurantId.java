package org.example.resources.domain.entity.type;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class RestaurantId {

    private final UUID UUID;

    public RestaurantId() {
        this.UUID = randomUUID();
    }

    @Default
    public RestaurantId(UUID UUID) {
        this.UUID = UUID;
    }

    public UUID getUUID() {
        return UUID;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof RestaurantId && ((RestaurantId) o).getUUID().equals(UUID);
    }

    @Override
    public int hashCode() {
        return UUID.hashCode();
    }

}

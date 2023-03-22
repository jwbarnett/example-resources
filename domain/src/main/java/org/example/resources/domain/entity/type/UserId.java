package org.example.resources.domain.entity.type;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class UserId {

    private final UUID UUID;

    public UserId() {
        this.UUID = randomUUID();
    }

    @Default
    public UserId(UUID UUID) {
        this.UUID = UUID;
    }

    public UUID getUUID() {
        return UUID;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof UserId && ((UserId) o).getUUID().equals(UUID);
    }

    @Override
    public int hashCode() {
        return UUID.hashCode();
    }

}

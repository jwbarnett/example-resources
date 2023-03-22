package org.example.resources.domain.entity.type;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class TableId {

    private final UUID UUID;

    public TableId() {
        this.UUID = randomUUID();
    }

    @Default
    public TableId(UUID UUID) {
        this.UUID = UUID;
    }

    public UUID getUUID() {
        return UUID;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof TableId && ((TableId) o).getUUID().equals(UUID);
    }

    @Override
    public int hashCode() {
        return UUID.hashCode();
    }

}

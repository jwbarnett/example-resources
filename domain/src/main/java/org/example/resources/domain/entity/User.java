package org.example.resources.domain.entity;

import org.example.resources.domain.entity.type.UserId;

public class User {

    private final UserId userId;
    private String name;

    public User(UserId userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

}

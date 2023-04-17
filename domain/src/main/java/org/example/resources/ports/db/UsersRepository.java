package org.example.resources.ports.db;

import org.example.resources.domain.entity.User;
import org.example.resources.domain.entity.type.UserId;

import java.util.List;
import java.util.Optional;

public interface UsersRepository {

    User generateUser();

    List<User> getAll();

    Optional<User> get(UserId userId);

}

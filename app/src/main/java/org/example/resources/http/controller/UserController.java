package org.example.resources.http.controller;

import org.example.resources.domain.entity.User;
import org.example.resources.ports.db.UsersRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PostMapping("/generate")
    public User generate() {
        return usersRepository.generateUser();
    }

}

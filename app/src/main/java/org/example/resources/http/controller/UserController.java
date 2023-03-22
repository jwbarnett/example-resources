package org.example.resources.http.controller;

import org.example.resources.infrastructure.db.dao.UsersDao;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UsersDao usersDao;

    public UserController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @GetMapping("/generate")
    public void generate() {
        usersDao.generateUser();
    }

}

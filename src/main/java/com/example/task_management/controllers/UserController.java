package com.example.task_management.controllers;

import com.example.task_management.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create a new user
    @PostMapping
    void createUser() {

    }

    // get user details by id
    @GetMapping
    void getUserById(UUID id) {

    }
}

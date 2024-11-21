package com.example.task_management.controllers;

import com.example.task_management.domains.category.Category;
import com.example.task_management.domains.user.User;
import com.example.task_management.domains.user.UserDto;
import com.example.task_management.repositories.UserRepository;
import com.example.task_management.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    // create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody() UserDto dto) {
        User user = this.userService.createUser(dto);
        return ResponseEntity.ok(user);
    }

    // get user details by id
    @GetMapping("{uuid}")
    public ResponseEntity<User> getUserById(@PathVariable("uuid") UUID uuid) {
        List<User> data = this.userRepository.findAllById(Collections.singleton(uuid));
        return ResponseEntity.ok(data.getFirst());
    }
}

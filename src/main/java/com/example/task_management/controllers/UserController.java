package com.example.task_management.controllers;

import com.example.task_management.domains.user.User;
import com.example.task_management.domains.user.UserDto;
import com.example.task_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody() UserDto dto) {
        User user = this.userService.createUser(dto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // get user details by id
    @GetMapping("{uuid}")
    public ResponseEntity<User> getUserById(@PathVariable("uuid") UUID uuid) {
        return ResponseEntity.ok(userService.getUserData(uuid));
    }
}

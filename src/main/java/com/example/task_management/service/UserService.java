package com.example.task_management.service;

import com.example.task_management.domains.user.User;
import com.example.task_management.domains.user.UserDto;
import com.example.task_management.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        userRepository.save(user);
        return user;
    }
}

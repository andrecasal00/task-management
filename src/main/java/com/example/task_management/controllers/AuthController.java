package com.example.task_management.controllers;

import com.example.task_management.domains.auth.AuthResponseDto;
import com.example.task_management.domains.auth.SignInDto;
import com.example.task_management.domains.user.User;
import com.example.task_management.domains.user.UserDto;
import com.example.task_management.infra.security.TokenService;
import com.example.task_management.repositories.UserRepository;
import com.example.task_management.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("signin")
    public ResponseEntity signIn(@RequestBody SignInDto body) {
        User user = this.userRepository.findByEmail(body.email()).orElseThrow(
                () -> new RuntimeException("User not found!")
        );

        if (this.passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new AuthResponseDto(user.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("register")
    public ResponseEntity signUp(@RequestBody UserDto body) {
        Optional<User> user = this.userRepository.findByEmail(body.email());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setUsername(body.username());

            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new AuthResponseDto(newUser.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }
}

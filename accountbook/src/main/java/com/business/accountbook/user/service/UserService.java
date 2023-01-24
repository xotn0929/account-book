package com.business.accountbook.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.business.accountbook.user.entity.User;
import com.business.accountbook.user.repository.UserRepository;

import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Mono<User> createUser(User user) {
        return userRepository.save(user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .enabled(true)
                .createdDate(LocalDateTime.now())
                .build())
                .doOnSuccess(u -> log.info("Created new user with ID = " + u.getId()));
    }

    public Mono<User> getUser(UUID userId) {
        return userRepository.findById(userId);
    }
}
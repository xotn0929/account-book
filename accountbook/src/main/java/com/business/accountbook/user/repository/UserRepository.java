package com.business.accountbook.user.repository;

import java.util.UUID;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.business.accountbook.user.entity.User;

import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, UUID> {
    Mono<User> findByUsername(String username);
}
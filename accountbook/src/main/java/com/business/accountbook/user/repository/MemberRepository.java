package com.business.accountbook.user.repository;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import com.business.accountbook.user.entity.Member;

import reactor.core.publisher.Flux;

public interface MemberRepository extends R2dbcRepository<Member, UUID>{
    Flux<Member> findAllBy(Pageable pageable);
}

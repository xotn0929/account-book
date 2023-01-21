package com.business.accountbook.user.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.business.accountbook.user.dto.MemberDTO;

import reactor.core.publisher.Mono;

public interface MemberServiceImpl {
    
    // 전체조회
    public Mono<Page<MemberDTO>> getAll(PageRequest pageRequest);

    // 단일조회
    public Mono<MemberDTO> getMember(UUID id);
    public Mono<MemberDTO> getMember(String userid);

    // 사용자등록
    public Mono<Void> save(MemberDTO memberDTO);
}

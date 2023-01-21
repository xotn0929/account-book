package com.business.accountbook.user.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.business.accountbook.user.dto.MemberDTO;
import com.business.accountbook.user.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberServiceImpl{

    private final MemberRepository memberRepository;

    @Override
    public Mono<Page<MemberDTO>> getAll(PageRequest pageRequest) {
        return memberRepository.findAllBy(pageRequest)
                               .map(MemberDTO::new)
                               .collectList()
                               .zipWith(memberRepository.count())
                               .map(t -> new PageImpl<>(t.getT1(), pageRequest, t.getT2()));
    }

    @Override
    public Mono<MemberDTO> getMember(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<MemberDTO> getMember(String userid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Void> save(MemberDTO memberDTO) {
        return memberRepository.save(memberDTO.toEntity()).then();
    }
    
}

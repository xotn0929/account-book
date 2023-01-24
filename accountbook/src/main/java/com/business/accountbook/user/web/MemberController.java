package com.business.accountbook.user.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.business.accountbook.user.dto.MemberDTO;
import com.business.accountbook.user.service.MemberServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

//@Api(tags = {"사용자 등록 조회"})
@Tag(name = "회원 API", description = "회원 조회, 등록")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    
    private final MemberServiceImpl memberServiceImpl;

    @GetMapping("/all")
    public Mono<Page<MemberDTO>> getAll(@RequestParam("page") int page, @RequestParam("size") int size){
        return memberServiceImpl.getAll(PageRequest.of(page, size));
    }

    @PostMapping("/sign")
    public Mono<MemberDTO> saveMember(@Valid @RequestBody MemberDTO memberDTO){
        return memberServiceImpl.save(memberDTO).thenReturn(memberDTO);
    }
}

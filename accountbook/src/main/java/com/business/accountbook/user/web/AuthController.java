package com.business.accountbook.user.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.business.accountbook.user.dto.MemberDTO;
import com.business.accountbook.user.dto.ResponseModel;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Tag(name = "인증 API", description = "회원 인증, 가입")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    
    @GetMapping("/test")
    public Mono<ResponseEntity<ResponseModel<String>>> auth(){
        return Mono.just(
            ResponseEntity.ok(
                new ResponseModel<>("인증됨", "")
            )
        ).onErrorResume(e -> Mono.just(ResponseEntity.ok(
            new ResponseModel<>("인증 실패", "")
        )));
    } 

    @PostMapping("/login")
    public Mono<ResponseEntity<ResponseModel<MemberDTO>>> login(@Valid @RequestBody MemberDTO memberDTO){
        return null;
    }
}

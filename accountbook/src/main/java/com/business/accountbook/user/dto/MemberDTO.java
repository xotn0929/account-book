package com.business.accountbook.user.dto;

import java.util.UUID;

import com.business.accountbook.user.entity.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Schema(description = "RequestBody")
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class MemberDTO {
    
    @Schema(name = "아이디", example = "UUID", type = "UUID")
    private UUID id;
    
    @Schema(name = "회원 아이디", example = "test@google.com", type = "String")
    @NotBlank
    @Email
    private String username;

    @Schema(name = "회원 비밀번호", example = "test", type = "String")
    @NotBlank
    private String password;

    @Builder
    public MemberDTO(UUID id, String UserId, String UserPw){
        this.id = id;
        this.username = UserId;
        this.password = UserPw;
    }

    // DTO -> entity
    public Member toEntity(){
        return Member.builder().id(id)
                                .UserId(username)
                                .UserPw(password)
                                .build();
    }

    // entity -> DTO
    public MemberDTO(Member member){
        this.id = member.getId();
        this.username = member.getUserId();
        this.password = member.getUserPw();
    }
}

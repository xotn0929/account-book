package com.business.accountbook.user.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ApiModel(description = "RequestBody")
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class MemberDTO {
    
    @ApiModelProperty(value = "기본키", dataType = "UUID")
    private UUID id;
    
    @ApiModelProperty(value = "회원 아이디", dataType = "String", example = "test@google.com")
    @NotBlank
    @Email
    private String UserId;

    @ApiModelProperty(value = "회원 비밀번호", dataType = "String")
    @NotBlank
    private String UserPw;

    @Builder
    public MemberDTO(UUID id, String UserId, String UserPw){
        this.id = id;
        this.UserId = UserId;
        this.UserPw = UserPw;
    }

    // DTO -> entity
    // TODO: Member Entity 생성
/*     public Member toEntity(){
        return Member.builder().id(id)
                                .userId(UserId)
                                .userPw(UserPw)
                                .build();
    }
 */
    // entity -> DTO
/*     public MemberDTO(Member member){
        this.id = member.getId();
        this.UserId = member.getUserId();
        this.UserPw = member.gerUserPw();
    } */
}

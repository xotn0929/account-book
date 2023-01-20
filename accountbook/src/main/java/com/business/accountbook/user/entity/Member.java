package com.business.accountbook.user.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import nonapi.io.github.classgraph.json.Id;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Table("TB_USER")
public class Member {
    
    @Id
    private UUID id;

    @Column("user_id")
    private String UserId;

    @Column("user_pw")
    private String UserPw;

    @Column("token")
    private String token;

    @CreatedDate
    @Column("create_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column("update_date")
    private LocalDateTime updateDate;
    
    @Builder
    public Member(UUID id, String UserId, String UserPw, String token){
        this.id = id;
        this.UserId = UserId;
        this.UserPw = UserPw;
        this.token = token;
    }
}

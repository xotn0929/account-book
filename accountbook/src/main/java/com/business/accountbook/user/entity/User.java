package com.business.accountbook.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table("TB_USER")
public class User {
    
    @Id
    private UUID id;
    private String username;
    private String password;
    
    @CreatedDate
    @Column("create_date")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column("update_date")
    private LocalDateTime updateDate;

    private List<String> roles;

    private boolean enabled;
}
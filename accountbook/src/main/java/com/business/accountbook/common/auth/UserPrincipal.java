package com.business.accountbook.common.auth;

import java.security.Principal;
import java.util.UUID;

public class UserPrincipal implements Principal {
    private UUID id;
    private String name;

    public UserPrincipal(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
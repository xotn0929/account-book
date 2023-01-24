package com.business.accountbook.common.auth;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.business.accountbook.common.app.ApiException;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ApiException {
    public UnauthorizedException(String message) {
        super(message, "UNAUTHORIZED");
    }
}
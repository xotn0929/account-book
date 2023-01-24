package com.business.accountbook.common.app;

public class AuthException extends ApiException {
    public AuthException(String message, String errorCode) {
        super(message, errorCode);
    }
}
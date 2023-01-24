package com.business.accountbook.user.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ResponseModel<T> {
    private T data;

    private String message;

    public T getData(){
        return this.data;
    }
    
    public String getMessage(){
        return this.message;
    }
}

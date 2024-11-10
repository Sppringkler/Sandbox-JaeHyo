package com.ssafy.sandbox.global.exception;

public class ExpiredAuthenticationException extends BusinessException{
    public ExpiredAuthenticationException(String message) {
        super(message);
    }
}

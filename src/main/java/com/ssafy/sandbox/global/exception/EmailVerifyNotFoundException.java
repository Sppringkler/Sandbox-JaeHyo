package com.ssafy.sandbox.global.exception;

public class EmailVerifyNotFoundException extends DatabaseException{

    public EmailVerifyNotFoundException(String message) {
        super(message);
    }
}

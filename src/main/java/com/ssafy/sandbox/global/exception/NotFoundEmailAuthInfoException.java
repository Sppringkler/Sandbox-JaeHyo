package com.ssafy.sandbox.global.exception;

public class NotFoundEmailAuthInfoException extends DatabaseException{
    public NotFoundEmailAuthInfoException(String message) {
        super(message);
    }
}

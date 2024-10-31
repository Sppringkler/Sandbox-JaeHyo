package com.ssafy.sandbox.global.exception;

public class TodoNotFoundException extends DatabaseException{

    public TodoNotFoundException(String message) {
        super(message);
    }
}

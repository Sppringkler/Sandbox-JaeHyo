package com.ssafy.sandbox.domain.todo.exception;

public class TodoNotFoundException extends DatabaseException{

    public TodoNotFoundException(String message) {
        super(message);
    }
}

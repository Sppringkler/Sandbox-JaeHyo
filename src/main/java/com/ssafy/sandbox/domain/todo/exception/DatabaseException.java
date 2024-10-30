package com.ssafy.sandbox.domain.todo.exception;

// 예외 타입 대분류 -> repository 계층에서 발생한 예외를 처리할 때 사용 ex) 존재하지 않는 자원 조회 등
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}

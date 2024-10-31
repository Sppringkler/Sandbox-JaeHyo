package com.ssafy.sandbox.global.exception;

// 예외 타입 대분류 -> service 계층에서 발생한 예외를 처리할 때 사용 ex) 중복 검사 실패 등
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}

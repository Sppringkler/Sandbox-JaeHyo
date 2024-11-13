package com.ssafy.sandbox.global.exception.type;

import com.ssafy.sandbox.global.exception.ErrorCode;
import lombok.Getter;

// 예외 타입 대분류 -> controller 계층에서 발생한 예외를 처리할 때 사용 ex) 형식에 맞지 않는 입력 검증 등
@Getter
public class ValidationException extends RuntimeException{
    private final ErrorCode errorCode;

    public ValidationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

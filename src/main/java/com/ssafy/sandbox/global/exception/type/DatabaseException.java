package com.ssafy.sandbox.global.exception.type;

import com.ssafy.sandbox.global.exception.ErrorCode;
import lombok.Getter;

// 예외 타입 대분류 -> repository 계층에서 발생한 예외를 처리할 때 사용 ex) 존재하지 않는 자원 조회 등
@Getter
public class DatabaseException extends RuntimeException {
    private final ErrorCode errorCode;

    public DatabaseException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

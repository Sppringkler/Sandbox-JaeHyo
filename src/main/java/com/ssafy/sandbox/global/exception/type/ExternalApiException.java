package com.ssafy.sandbox.global.exception.type;

import com.ssafy.sandbox.global.exception.ErrorCode;
import lombok.Getter;

// 예외 타입 대분류 -> 외부 api 요청시 발생한 예외를 처리할 때 사용
@Getter
public class ExternalApiException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExternalApiException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}

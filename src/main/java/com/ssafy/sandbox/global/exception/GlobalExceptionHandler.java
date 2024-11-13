package com.ssafy.sandbox.global.exception;

import com.ssafy.sandbox.controller.response.BooleanSuccessRes;
import com.ssafy.sandbox.controller.response.ErrorRes;
import com.ssafy.sandbox.global.exception.type.BusinessException;
import com.ssafy.sandbox.global.exception.type.DatabaseException;
import com.ssafy.sandbox.global.exception.type.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ErrorRes> handleDatabaseException(DatabaseException ex) {
        log.error("DatabaseException 발생: {}", ex.getMessage(), ex);

        return ResponseEntity.status(404)
                .body(new ErrorRes(ex.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        log.error("BusinessException 발생: {}", ex.getMessage(), ex);

        ErrorCode errorCode = ex.getErrorCode();

        Map<String, Object> errorResponse = new HashMap<>();

        if (ErrorCode.KAKAO_OAUTH_AUTHORIZATION_CODE_MISSING.equals(errorCode)) {
            errorResponse.put("status", "400");
            errorResponse.put("code", "ERR_MISSING_AUTHORIZATION_CODE");
        } else if (ErrorCode.KAKAO_OAUTH_ACCESS_TOKEN_MISSING.equals(errorCode)) {
            errorResponse.put("status", "400");
            errorResponse.put("code", "ERR_MISSING_ACCESS_TOKEN");
        } else if (ErrorCode.KAKAO_OAUTH_ACCESS_TOKEN_INVALID.equals(errorCode)) {
            errorResponse.put("status", "401");
            errorResponse.put("code", "ERR_ACCESS_TOKEN_EXPIRED");
        } else if (ErrorCode.KAKAO_OAUTH_ACCESS_TOKEN_USER_NOT_FOUND.equals(errorCode)) {
            errorResponse.put("status", "404");
            errorResponse.put("code", "ERR_NOT_FOUND_MEMBER");
        } else if (ErrorCode.KAKAO_OAUTH_REFRESH_TOKEN_MISSING.equals(errorCode)) {
            errorResponse.put("status", "400");
            errorResponse.put("code", "ERR_MISSING_REFRESH_TOKEN");
        } else if (ErrorCode.KAKAO_OAUTH_REFRESH_TOKEN_INVALID.equals(errorCode)) {
            errorResponse.put("status", "401");
            errorResponse.put("code", "ERR_REFRESH_TOKEN_EXPIRED");
        }

        return ResponseEntity.status(errorCode.getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<BooleanSuccessRes> handleValidationException(ValidationException ex) {
        log.error("ValidationException 발생: {}", ex.getMessage(), ex);

        return ResponseEntity.status(404)
                .body(new BooleanSuccessRes(false));
    }
}

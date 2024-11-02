package com.ssafy.sandbox.global.exception;

import com.ssafy.sandbox.controller.response.ErrorRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorRes> handleTodoNotFoundException(TodoNotFoundException ex) {
        log.error("TodoNotFoundException 발생: {}", ex.getMessage(), ex);

        return ResponseEntity.status(404)
                .body(new ErrorRes(ex.getMessage()));
    }
}

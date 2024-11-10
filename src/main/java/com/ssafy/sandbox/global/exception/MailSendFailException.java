package com.ssafy.sandbox.global.exception;

public class MailSendFailException extends BusinessException{
    public MailSendFailException(String message) {
        super(message);
    }
}

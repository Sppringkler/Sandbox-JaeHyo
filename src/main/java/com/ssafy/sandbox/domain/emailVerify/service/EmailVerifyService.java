package com.ssafy.sandbox.domain.emailVerify.service;

import com.ssafy.sandbox.controller.request.EmailVerifyReq;
import com.ssafy.sandbox.controller.request.SendEmailReq;

public interface EmailVerifyService {

    void sendEmailVerifyCode(SendEmailReq sendEmailReq);
    void verifyEmailCode(EmailVerifyReq emailVerifyReq);
}

package com.ssafy.sandbox.domain.emailVerify.repository;

import com.ssafy.sandbox.domain.emailVerify.entity.EmailVerify;

import java.util.Optional;

public interface EmailVerifyRepository {
   void saveVerifyCode(EmailVerify emailVerify);
    Optional<EmailVerify> findByEmail(String email);
    void deleteByEmail(String email);
}


package com.ssafy.sandbox.domain.emailauthinfo.service;

public interface EmailAuthInfoService {
    void sendEmailAuthCode(String email);

    void authenticate(String email, String authentication);
}

package com.ssafy.sandbox.domain.user.service;

public interface UserService {
    void sendEmailAuthCode(String email);

    void authenticate(String email, String authentication);
}

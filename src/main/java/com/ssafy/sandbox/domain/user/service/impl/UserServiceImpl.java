package com.ssafy.sandbox.domain.user.service.impl;

import com.ssafy.sandbox.domain.emailauthinfo.service.EmailAuthInfoService;
import com.ssafy.sandbox.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EmailAuthInfoService emailAuthInfoService;

    @Override
    public void sendEmailAuthCode(String email) {
        emailAuthInfoService.sendEmailAuthCode(email);
    }

    @Override
    public void authenticate(String email, String authentication) {
        emailAuthInfoService.authenticate(email, authentication);
    }
}

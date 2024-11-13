package com.ssafy.sandbox.domain.user.service.impl;

import com.ssafy.sandbox.domain.emailauthinfo.service.EmailAuthInfoService;
import com.ssafy.sandbox.domain.user.entity.User;
import com.ssafy.sandbox.domain.user.repository.UserRepository;
import com.ssafy.sandbox.domain.user.service.UserService;
import com.ssafy.sandbox.global.exception.type.DatabaseException;
import com.ssafy.sandbox.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EmailAuthInfoService emailAuthInfoService;

    @Override
    public void sendEmailAuthCode(String email) {
        emailAuthInfoService.sendEmailAuthCode(email);
    }

    @Override
    public void authenticate(String email, String authentication) {
        emailAuthInfoService.authenticate(email, authentication);
    }

    @Override
    public boolean existKakaoUser(String kakaoId) {
        return userRepository.existsByKakaoId(kakaoId);
    }

    @Override
    public void createKakaoUser(String kakaoId, String nickname) {
        User user = new User(kakaoId, nickname);
        userRepository.save(user);
    }

    @Override
    public User readKakaoUser(String kakaoId) {
        return userRepository.findByKakaoId(kakaoId)
                .orElseThrow(() -> new DatabaseException(ErrorCode.USER_NOT_FOUND));
    }
}

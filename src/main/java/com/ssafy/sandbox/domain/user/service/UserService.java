package com.ssafy.sandbox.domain.user.service;


import com.ssafy.sandbox.domain.user.entity.User;

import java.util.Optional;

public interface UserService {
    void sendEmailAuthCode(String email);

    void authenticate(String email, String authentication);

    boolean existKakaoUser(String kakaoId);

    void createKakaoUser(String kakaoId, String nickname);

    User readKakaoUser(String kakaoId);
}

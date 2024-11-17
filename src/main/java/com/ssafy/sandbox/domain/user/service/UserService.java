package com.ssafy.sandbox.domain.user.service;

import com.ssafy.sandbox.domain.user.entity.User;

public interface UserService {
    boolean getUser(String kakaoId);
    void createUser(User user);
}

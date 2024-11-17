package com.ssafy.sandbox.domain.user.repository;

import com.ssafy.sandbox.domain.user.entity.User;

public interface UserRepository {

    void save(User user);

    User findById(String kakaoId);

}

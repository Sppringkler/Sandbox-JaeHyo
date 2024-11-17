package com.ssafy.sandbox.domain.user.service.impl;

import com.ssafy.sandbox.domain.user.entity.User;
import com.ssafy.sandbox.domain.user.repository.UserRepository;
import com.ssafy.sandbox.domain.user.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public boolean getUser(String kakaoId) {
        return userRepository.findById(kakaoId) != null;
    }

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }
}

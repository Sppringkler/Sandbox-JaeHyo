package com.ssafy.sandbox.domain.user.repository;

import com.ssafy.sandbox.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByKakaoId(String kakaoId);

    Optional<User> findByKakaoId(String kakaoId);
}

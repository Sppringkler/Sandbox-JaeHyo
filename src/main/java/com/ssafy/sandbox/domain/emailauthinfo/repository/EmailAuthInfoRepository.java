package com.ssafy.sandbox.domain.emailauthinfo.repository;

import com.ssafy.sandbox.domain.emailauthinfo.entity.EmailAuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailAuthInfoRepository extends JpaRepository<EmailAuthInfo, Integer> {

    Optional<EmailAuthInfo> findByEmail(String email);

    void deleteByEmail(String email);
}

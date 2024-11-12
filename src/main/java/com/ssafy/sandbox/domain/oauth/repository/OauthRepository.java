package com.ssafy.sandbox.domain.oauth.repository;

import com.ssafy.sandbox.domain.oauth.entity.OauthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthRepository extends JpaRepository<OauthInfo, Integer> {

    Optional<OauthInfo> findByKakaoId(String kakaoId);

    Optional<OauthInfo> findByAccessToken(String accessToken);

}

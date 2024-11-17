package com.ssafy.sandbox.domain.oauth.repository;

import com.ssafy.sandbox.domain.oauth.entity.OauthInfo;

import java.util.Optional;

public interface OauthRepository {

    void save(OauthInfo oauthInfo);
    Optional<OauthInfo> findByRefreshToken(String refreshToken);
}

package com.ssafy.sandbox.domain.oauth.service.impl;

import com.ssafy.sandbox.domain.oauth.dto.ReadUserResDto;
import com.ssafy.sandbox.domain.oauth.dto.ReadTokenResDto;
import com.ssafy.sandbox.domain.oauth.dto.ReissueTokenResDto;
import com.ssafy.sandbox.domain.oauth.service.OauthService;
import com.ssafy.sandbox.domain.user.service.UserService;
import com.ssafy.sandbox.external.KakaoOauthClient;
import com.ssafy.sandbox.external.response.KakaoLogoutRes;
import com.ssafy.sandbox.external.response.KakaoReissueTokenRes;
import com.ssafy.sandbox.external.response.KakaoTokenRes;
import com.ssafy.sandbox.external.response.KakaoUserInfoRes;
import com.ssafy.sandbox.global.exception.ErrorCode;
import com.ssafy.sandbox.global.exception.type.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Service
@RequiredArgsConstructor
@Slf4j
public class OauthServiceImpl implements OauthService {

    private final KakaoOauthClient kakaoOauthClient;
    private final UserService userService;

    @Override
    @Transactional
    public ReadTokenResDto readToken(String code) {

        // 인가코드가 누락된 경우
        if (code == null || code.isEmpty()) {
            throw new BusinessException(ErrorCode.KAKAO_OAUTH_AUTHORIZATION_CODE_MISSING);
        }

        // 인가코드로 access token 발급 받기
        KakaoTokenRes kakaoTokenRes = kakaoOauthClient.getToken(code);

        // access token으로 kakao  서버에 저장된 user 정보 조회하기
        KakaoUserInfoRes kakaoUserInfoRes = kakaoOauthClient.getUserInfo(kakaoTokenRes.getAccessToken());

        String kakaoId = kakaoUserInfoRes.getId();

        kakaoUserInfoRes.getProperties().forEach((key, value) ->
                System.out.println("Key: " + key + ", Value: " + value)
        );

        if (!userService.existKakaoUser(kakaoId)) {
            userService.createKakaoUser(kakaoId, kakaoUserInfoRes.getProperties().get("nickname"));
        }

        return new ReadTokenResDto(
                kakaoTokenRes.getAccessToken(),
                kakaoTokenRes.getRefreshToken());
    }

    @Override
    public ReadUserResDto readMember(String accessToken) {
        // 인가코드가 누락된 경우
        if (accessToken == null || accessToken.isEmpty()) {
            throw new BusinessException(ErrorCode.KAKAO_OAUTH_ACCESS_TOKEN_MISSING);
        }

        accessToken = accessToken.replaceFirst("Bearer ", "");

        try {
            KakaoUserInfoRes kakaoUserInfoRes = kakaoOauthClient.getUserInfo(accessToken);
            return new ReadUserResDto(kakaoUserInfoRes.getProperties().get("nickname"));

        } catch (WebClientResponseException e) {
            if (HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
                throw new BusinessException(ErrorCode.KAKAO_OAUTH_ACCESS_TOKEN_INVALID);
            } else if (HttpStatus.NOT_FOUND.equals(e.getStatusCode())) {
                throw new BusinessException(ErrorCode.KAKAO_OAUTH_ACCESS_TOKEN_USER_NOT_FOUND);
            } else {
                throw new BusinessException(ErrorCode.UNEXPECTED_EXCEPTION);
            }
        }
    }

    @Override
    public ReissueTokenResDto reissueToken(String refreshToken) {
        // refreshToken이 누락된 경우
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new BusinessException(ErrorCode.KAKAO_OAUTH_REFRESH_TOKEN_MISSING);
        }

        try {
            KakaoReissueTokenRes kakaoReissueTokenRes = kakaoOauthClient.reissueToken(refreshToken);
            return new ReissueTokenResDto(kakaoReissueTokenRes.getAccessToken());
        } catch (WebClientResponseException e) {
            if (HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
                throw new BusinessException(ErrorCode.KAKAO_OAUTH_REFRESH_TOKEN_INVALID);
            } else {
                throw new BusinessException(ErrorCode.UNEXPECTED_EXCEPTION);
            }
        }
    }

    @Override
    public void logout(String refreshToken) {
        // refreshToken이 누락된 경우
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new BusinessException(ErrorCode.KAKAO_OAUTH_REFRESH_TOKEN_MISSING);
        }

        try {
            KakaoReissueTokenRes kakaoReissueTokenRes = kakaoOauthClient.reissueToken(refreshToken);
            String accessToken = kakaoReissueTokenRes.getAccessToken();

            KakaoLogoutRes kakaoLogoutRes = kakaoOauthClient.logout(accessToken);
            log.info("{}번 회원이 로그아웃 되었습니다.", kakaoLogoutRes.getId());

        } catch (WebClientResponseException e) {
            if (HttpStatus.UNAUTHORIZED.equals(e.getStatusCode())) {
                throw new BusinessException(ErrorCode.KAKAO_OAUTH_REFRESH_TOKEN_INVALID);
            } else {
                throw new BusinessException(ErrorCode.UNEXPECTED_EXCEPTION);
            }
        }
    }
}

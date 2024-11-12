package com.ssafy.sandbox.domain.oauth.service.impl;

import com.ssafy.sandbox.domain.oauth.dto.ReadUserResDto;
import com.ssafy.sandbox.domain.oauth.dto.ReadTokenResDto;
import com.ssafy.sandbox.domain.oauth.entity.OauthInfo;
import com.ssafy.sandbox.domain.oauth.repository.OauthRepository;
import com.ssafy.sandbox.domain.oauth.service.OauthService;
import com.ssafy.sandbox.domain.user.entity.User;
import com.ssafy.sandbox.domain.user.service.UserService;
import com.ssafy.sandbox.external.KakaoOauthClient;
import com.ssafy.sandbox.external.response.KakaoTokenRes;
import com.ssafy.sandbox.external.response.KakaoUserInfoRes;
import com.ssafy.sandbox.global.exception.TodoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService {

    private final KakaoOauthClient kakaoOauthClient;
    private final UserService userService;
    private final OauthRepository oauthRepository;

    @Override
    @Transactional
    public ReadTokenResDto readToken(String code) {

        // 인가코드로 access token 발급 받기
        KakaoTokenRes kakaoTokenRes = kakaoOauthClient.getToken(code);

        // access token으로 kakao  서버에 저장된 user 정보 조회하기
        KakaoUserInfoRes kakaoUserInfoRes = kakaoOauthClient.getUserInfo(kakaoTokenRes.getAccessToken());

        String kakaoId = kakaoUserInfoRes.getId();

        kakaoUserInfoRes.getProperties().forEach((key, value) ->
                System.out.println("Key: " + key + ", Value: " + value)
        );

        if (userService.existKakaoUser(kakaoId)) {
            OauthInfo oauthInfo = oauthRepository.findByKakaoId(kakaoId)
                    .orElseThrow(() -> new TodoNotFoundException("정상적이지 않은 요청입니다."));

            oauthInfo.update(kakaoTokenRes.getAccessToken(), kakaoTokenRes.getRefreshToken());

        } else {
            userService.createKakaoUser(kakaoId, kakaoUserInfoRes.getProperties().get("nickname"));

            OauthInfo oauthInfo = OauthInfo.builder()
                    .kakaoId(kakaoId)
                    .accessToken(kakaoTokenRes.getAccessToken())
                    .refreshToken(kakaoTokenRes.getRefreshToken())
                    .build();

            oauthRepository.save(oauthInfo);
        }

        return new ReadTokenResDto(
                kakaoTokenRes.getAccessToken(),
                kakaoTokenRes.getRefreshToken());
    }

    @Override
    public ReadUserResDto readMember(String accessToken) {
        accessToken = accessToken.replaceFirst("Bearer ", "");

        OauthInfo oauthInfo = oauthRepository.findByAccessToken(accessToken)
                .orElseThrow(() -> new TodoNotFoundException("정상적이지 않은 요청입니다."));

        User user = userService.readKakaoUser(oauthInfo.getKakaoId());

        return new ReadUserResDto(user.getNickname());
    }
}

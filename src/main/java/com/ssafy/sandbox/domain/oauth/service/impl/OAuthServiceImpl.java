package com.ssafy.sandbox.domain.oauth.service.impl;

import com.ssafy.sandbox.controller.request.CreateAccessTokenReq;
import com.ssafy.sandbox.domain.oauth.dto.response.ReadAccessTokenResDto;
import com.ssafy.sandbox.domain.oauth.dto.response.ReadMemberResDto;
import com.ssafy.sandbox.domain.oauth.dto.response.ReissueAccessTokenResDto;
import com.ssafy.sandbox.domain.oauth.entity.OauthInfo;
import com.ssafy.sandbox.domain.oauth.repository.OauthRepository;
import com.ssafy.sandbox.domain.oauth.service.OAuthService;
import com.ssafy.sandbox.domain.user.entity.User;
import com.ssafy.sandbox.domain.user.service.UserService;
import com.ssafy.sandbox.global.exception.BusinessException;
import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthServiceImpl implements OAuthService {

    private final OauthRepository oauthRepository;
    private final UserService userService;

    private final String KAUTH_TOKEN_URL_HOST="https://kauth.kakao.com";
    private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    @Value("${kakao.client_id}")
    private String clientId;

    @Override
    public ReadAccessTokenResDto readAccessToken(CreateAccessTokenReq req) {
        String code = req.getCode();

        //access token 생성
        ReadAccessTokenResDto readAccessTokenResDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(ReadAccessTokenResDto.class)
                .block();

        log.info(" [Kakao Service] Access Token ------> {}", readAccessTokenResDto.getAccessToken());
        log.info(" [Kakao Service] Refresh Token ------> {}", readAccessTokenResDto.getRefreshToken());

        //user 정보 조회
        ReadMemberResDto readMemberResDto = getUserInfo(readAccessTokenResDto.getAccessToken());

        //db에 저장된 멤버가 있는지 조회
        if(!userService.getUser(readMemberResDto.getId())) {
            User user = User.builder()
                        .kakaoId(readMemberResDto.getId())
                        .nickname(readMemberResDto.getProperties().get("nickname"))
                        .build();
            userService.createUser(user);

            OauthInfo oauthInfo = OauthInfo.builder()
                    .kakaoId(user.getKakaoId())
                    .accessToken(readAccessTokenResDto.getAccessToken())
                    .refreshToken(readAccessTokenResDto.getRefreshToken())
                            .build();
            oauthRepository.save(oauthInfo);
        }
        return readAccessTokenResDto;
    }

    @Override
    public ReadMemberResDto readMember(String accessToken) {
        accessToken = accessToken.replaceFirst("Bearer ", "");
        ReadMemberResDto readMemberResDto = getUserInfo(accessToken);

        log.info("[ Kakao Service ] NickName ---> {} ", readMemberResDto.getProperties().get("nickname"));

        return readMemberResDto;
    }

    @Override
    public ReissueAccessTokenResDto reissueAccessToken(String refreshToken) {

        //access token 갱신
        ReissueAccessTokenResDto reissueAccessTokenResDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "refresh_token")
                        .queryParam("client_id", clientId)
                        .queryParam("refresh_token", refreshToken)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .bodyToMono(ReissueAccessTokenResDto.class)
                .block();

        log.info(" [Kakao Service] Access Token ------> {}", reissueAccessTokenResDto.getAccessToken());

        return reissueAccessTokenResDto;
    }

    @Override
    public void logout(String refreshToken) {
        OauthInfo oauthInfo = oauthRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new BusinessException("유효하지 않은 토큰입니다."));

        WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v1/user/logout")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ oauthInfo.getAccessToken())
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("API 호출 실패")))
                .bodyToMono(ReadMemberResDto.class)
                .block();
    }

    public ReadMemberResDto getUserInfo(String accessToken) {
        return WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer "+ accessToken)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new RuntimeException("API 호출 실패")))
                .bodyToMono(ReadMemberResDto.class)
                .block();
    }
}

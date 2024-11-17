package com.ssafy.sandbox.domain.oauth.service;

import com.ssafy.sandbox.controller.request.CreateAccessTokenReq;
import com.ssafy.sandbox.domain.oauth.dto.response.ReadAccessTokenResDto;
import com.ssafy.sandbox.domain.oauth.dto.response.ReadMemberResDto;
import com.ssafy.sandbox.domain.oauth.dto.response.ReissueAccessTokenResDto;

public interface OAuthService {
    ReadAccessTokenResDto readAccessToken(CreateAccessTokenReq req);
    ReadMemberResDto readMember(String accessToken);
    ReissueAccessTokenResDto reissueAccessToken(String refreshToken);
    void logout(String refreshToken);
}

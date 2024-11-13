package com.ssafy.sandbox.domain.oauth.service;

import com.ssafy.sandbox.domain.oauth.dto.ReadUserResDto;
import com.ssafy.sandbox.domain.oauth.dto.ReadTokenResDto;
import com.ssafy.sandbox.domain.oauth.dto.ReissueTokenResDto;

public interface OauthService {
    ReadTokenResDto readToken(String code);

    ReadUserResDto readMember(String accessToken);

    ReissueTokenResDto reissueToken(String refreshToken);
}

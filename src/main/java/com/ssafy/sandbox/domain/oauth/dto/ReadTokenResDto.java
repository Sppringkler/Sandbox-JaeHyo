package com.ssafy.sandbox.domain.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReadTokenResDto {

    private String accessToken;
    private String refreshToken;
}

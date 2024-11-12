package com.ssafy.sandbox.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoUserInfoRes {
    private String id;
    private Map<String, String> properties;
}

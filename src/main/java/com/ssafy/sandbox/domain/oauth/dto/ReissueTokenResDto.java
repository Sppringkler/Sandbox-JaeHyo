package com.ssafy.sandbox.domain.oauth.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReissueTokenResDto {

    private String accessToken;
}

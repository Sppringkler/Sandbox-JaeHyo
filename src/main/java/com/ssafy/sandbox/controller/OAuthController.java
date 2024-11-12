package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.controller.request.ReadTokenReq;
import com.ssafy.sandbox.controller.response.AccessTokenRes;
import com.ssafy.sandbox.controller.response.ReadMemberRes;
import com.ssafy.sandbox.domain.oauth.dto.ReadUserResDto;
import com.ssafy.sandbox.domain.oauth.dto.ReadTokenResDto;
import com.ssafy.sandbox.domain.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "https://ssafysandbox.vercel.app", allowCredentials = "true")
@RequestMapping("/oauth")
@Slf4j
public class OAuthController {

    private final OauthService oauthService;

    @PostMapping("/auth")
    public ResponseEntity<AccessTokenRes> readToken(
            @RequestBody ReadTokenReq req) {

        ReadTokenResDto resDto = oauthService.readToken(req.getCode());

        return ResponseEntity.ok()
                .header("Set-Cookie", "RefreshToken=" + resDto.getRefreshToken() + "; Path=/; HttpOnly; Secure; SameSite=None")
                .body(new AccessTokenRes(resDto.getAccessToken()));
    }

    @GetMapping("/member")
    public ResponseEntity<ReadMemberRes> readMember(
            @RequestHeader(value = "Authorization", required = false) String accessToken) {

        ReadUserResDto resDto = oauthService.readMember(accessToken);

        return ResponseEntity.ok(
                new ReadMemberRes(resDto.getNickname())
        );
    }
}

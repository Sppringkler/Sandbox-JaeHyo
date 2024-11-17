package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.controller.request.CreateAccessTokenReq;
import com.ssafy.sandbox.controller.response.AccessTokenRes;
import com.ssafy.sandbox.controller.response.MemberRes;
import com.ssafy.sandbox.domain.oauth.dto.response.ReadAccessTokenResDto;
import com.ssafy.sandbox.domain.oauth.dto.response.ReadMemberResDto;
import com.ssafy.sandbox.domain.oauth.dto.response.ReissueAccessTokenResDto;
import com.ssafy.sandbox.domain.oauth.service.OAuthService;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "https://ssafysandbox.vercel.app", allowCredentials = "true")
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {
    private final OAuthService oAuthService;

    @PostMapping("/auth")
    public ResponseEntity<AccessTokenRes> readAccessToken(@RequestBody CreateAccessTokenReq request) {
        ReadAccessTokenResDto readAccessTokenResDto = oAuthService.readAccessToken(request);

        return ResponseEntity.ok()
                .header("Set-Cookie", "RefreshToken=" + readAccessTokenResDto.getRefreshToken() + "; Path=/; HttpOnly; Secure; SameSite=None")
                .body(new AccessTokenRes(readAccessTokenResDto.getAccessToken()));
    }

    @GetMapping("/member")
    public ResponseEntity<MemberRes> readMember(@RequestHeader(value = "Authorization", required = false) String accessToken) {

        ReadMemberResDto readMemberResDto = oAuthService.readMember(accessToken);
        return ResponseEntity.ok(new MemberRes(readMemberResDto.getProperties().get("nickname")));
    }

    @GetMapping("/reissue")
    public ResponseEntity<AccessTokenRes> reissueAccessToken(@CookieValue(value = "RefreshToken", required = false) String refreshToken) {
        ReissueAccessTokenResDto reissueAccessTokenResDto = oAuthService.reissueAccessToken(refreshToken);

        return ResponseEntity.ok(new AccessTokenRes(reissueAccessTokenResDto.getAccessToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<HttpStatus> logout(@CookieValue(value = "RefreshToken", required = false) String refreshToken) {
//        oAuthService.logout(refreshToken);

        Cookie cookie = new Cookie("RefreshToken", "value");
        cookie.setMaxAge(0); // 유효시간 0인 쿠키 보내서 만료 효과
        return ResponseEntity.ok()
                .header("Set-Cookie", cookie + "; Path=/; HttpOnly; Secure; SameSite=None")
                .body(HttpStatus.OK);
    }
}

package com.ssafy.sandbox.external;

import com.ssafy.sandbox.external.response.KakaoLogoutRes;
import com.ssafy.sandbox.external.response.KakaoReissueTokenRes;
import com.ssafy.sandbox.external.response.KakaoUserInfoRes;
import com.ssafy.sandbox.external.response.KakaoTokenRes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class KakaoOauthClient {

    private final String clientId = "0da56a0700a56821782b91c49ce03b42";
    private final String redirectUri = "https://ssafysandbox.vercel.app/oauth/redirect";

    public KakaoTokenRes getToken(String code) {
        String url = "https://kauth.kakao.com/oauth/token";

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "authorization_code")
                        .with("client_id", clientId)
                        .with("redirect_uri", redirectUri)
                        .with("code", code))
                .retrieve()
                .bodyToMono(KakaoTokenRes.class)
                .block();
    }

    public KakaoUserInfoRes getUserInfo(String accessToken) {
        String url = "https://kapi.kakao.com/v2/user/me";

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .get()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoUserInfoRes.class)
                .block();
    }

    public KakaoReissueTokenRes reissueToken(String refreshToken) {
        String url = "https://kauth.kakao.com/oauth/token";

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "refresh_token")
                        .with("client_id", clientId)
                        .with("refresh_token", refreshToken))
                .retrieve()
                .bodyToMono(KakaoReissueTokenRes.class)
                .block();
    }

    public KakaoLogoutRes logout(String accessToken) {
        String url = "https://kapi.kakao.com/v1/user/logout";

        return WebClient.builder()
                .baseUrl(url)
                .build()
                .post()
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoLogoutRes.class)
                .block();
    }
}

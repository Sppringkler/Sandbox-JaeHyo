package com.ssafy.sandbox.controller.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationReq {
    private String email;
    private String authentication;
}

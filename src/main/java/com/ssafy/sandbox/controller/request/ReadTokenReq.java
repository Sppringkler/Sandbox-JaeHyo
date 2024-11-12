package com.ssafy.sandbox.controller.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadTokenReq {
    private String code;
}

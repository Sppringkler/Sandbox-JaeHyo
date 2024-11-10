package com.ssafy.sandbox.controller.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailAuthReq {
    private String email;
}

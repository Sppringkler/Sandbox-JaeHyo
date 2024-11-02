package com.ssafy.sandbox.controller.request;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateTodosReq {
    private String content;
}

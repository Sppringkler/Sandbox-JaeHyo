package com.ssafy.sandbox.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessVerifyRes {
    @JsonProperty("isSuccess")
    private boolean isSuccess;
}

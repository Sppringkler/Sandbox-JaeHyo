package com.ssafy.sandbox.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooleanRes {

    @JsonProperty("isOk")
    private boolean isOk;
}

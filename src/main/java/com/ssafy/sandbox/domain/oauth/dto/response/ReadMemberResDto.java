package com.ssafy.sandbox.domain.oauth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReadMemberResDto {
    private String id;
    private Map<String, String> properties;
}

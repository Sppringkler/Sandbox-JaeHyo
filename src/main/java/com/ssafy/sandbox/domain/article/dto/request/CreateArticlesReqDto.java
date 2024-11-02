package com.ssafy.sandbox.domain.article.dto.request;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateArticlesReqDto {
    private String title;
}

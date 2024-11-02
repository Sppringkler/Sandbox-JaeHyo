package com.ssafy.sandbox.domain.article.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReadArticlesResDto {
    private int id;
    private String title;
    private String createdAt;
}

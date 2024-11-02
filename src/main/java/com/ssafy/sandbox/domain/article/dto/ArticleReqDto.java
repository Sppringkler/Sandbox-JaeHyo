package com.ssafy.sandbox.domain.article.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleReqDto {
    private int id;
    private String title;
    private LocalDateTime createdAt;
}

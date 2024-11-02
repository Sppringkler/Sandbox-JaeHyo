package com.ssafy.sandbox.controller.request;

import com.ssafy.sandbox.domain.article.dto.ArticleReqDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MakeArticlesReq {

    private List<ArticleReqDto> articles;
}

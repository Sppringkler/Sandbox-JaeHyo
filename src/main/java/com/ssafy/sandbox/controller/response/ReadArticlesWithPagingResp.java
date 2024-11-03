package com.ssafy.sandbox.controller.response;

import com.ssafy.sandbox.domain.article.dto.response.ReadArticlesWithPagingRespDto;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadArticlesWithPagingResp {

    private int totalPage;
    private List<ReadArticlesWithPagingRespDto> articles;

    public ReadArticlesWithPagingResp(Page<ReadArticlesWithPagingRespDto> pagedArticles) {
        this.totalPage = pagedArticles.getTotalPages();
        this.articles = pagedArticles.getContent();
    }
}

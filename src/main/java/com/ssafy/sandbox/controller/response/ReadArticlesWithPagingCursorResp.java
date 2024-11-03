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
public class ReadArticlesWithPagingCursorResp {

    private int lastId;
    private List<ReadArticlesWithPagingRespDto> articles;

    public ReadArticlesWithPagingCursorResp(Page<ReadArticlesWithPagingRespDto> pagedArticles, int cursorId) {
        this.lastId = cursorId + pagedArticles.getNumberOfElements();
        this.articles = pagedArticles.getContent();
    }
}

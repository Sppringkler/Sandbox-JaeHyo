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

    private Integer lastId;
    private List<ReadArticlesWithPagingRespDto> articles;

    public ReadArticlesWithPagingCursorResp(Page<ReadArticlesWithPagingRespDto> pagedArticles, int cursorId) {
        if(pagedArticles.getNumberOfElements()==0){ //데이터 다 가져온 경우
            this.lastId = null;
        } else{
            this.lastId = cursorId + pagedArticles.getNumberOfElements();
        }
        this.articles = pagedArticles.getContent();
    }
}

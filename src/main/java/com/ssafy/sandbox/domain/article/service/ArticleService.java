package com.ssafy.sandbox.domain.article.service;

import com.ssafy.sandbox.domain.article.dto.request.ArticleReqDto;
import com.ssafy.sandbox.domain.article.dto.response.ReadArticlesWithPagingRespDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {

    Page<ReadArticlesWithPagingRespDto> readArticlesWithPagingOffset(int size, int page);
    Page<ReadArticlesWithPagingRespDto> readArticlesWithPagingCursor(int size, int cursorId);
    void makeArticles(List<ArticleReqDto> articles);
}

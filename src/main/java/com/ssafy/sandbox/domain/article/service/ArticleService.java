package com.ssafy.sandbox.domain.article.service;

import com.ssafy.sandbox.domain.article.dto.ArticleReqDto;
import com.ssafy.sandbox.domain.article.dto.ReadArticlesWithPagingRespDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {

    Page<ReadArticlesWithPagingRespDto> readArticlesWithPaging(int size, int page);

    void makeArticles(List<ArticleReqDto> articles);
}

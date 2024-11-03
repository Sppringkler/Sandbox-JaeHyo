package com.ssafy.sandbox.domain.article.service.impl;

import com.ssafy.sandbox.domain.article.dto.request.ArticleReqDto;
import com.ssafy.sandbox.domain.article.entity.Article;
import com.ssafy.sandbox.domain.article.mapper.ArticleServiceMapper;
import com.ssafy.sandbox.domain.article.repository.ArticleRepository;
import com.ssafy.sandbox.domain.article.service.ArticleService;
import com.ssafy.sandbox.domain.article.dto.response.ReadArticlesWithPagingRespDto;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final ArticleServiceMapper mapper;

    @Override
    public Page<ReadArticlesWithPagingRespDto> readArticlesWithPagingOffset(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Article> articlesPage = articleRepository.findPagedArticlesOffset(pageable);

        return articlesPage.map(mapper::to);
    }

    @Override
    public Page<ReadArticlesWithPagingRespDto> readArticlesWithPagingCursor(int size, int cursorId) {
        Pageable pageable = PageRequest.of(0, size); //항상 0

        Page<Article> articlesPage = articleRepository.findPagedArticlesCursor(pageable,cursorId);

        return articlesPage.map(mapper::to);
    }

    @Override
    @Transactional
    public void makeArticles(List<ArticleReqDto> reqDtoList) {
        List<Article> articleList = reqDtoList.stream()
                .map(dto -> Article.builder()
                        .title(dto.getTitle())
                        .createdAt(dto.getCreatedAt())
                        .build())
                .toList();

        for (Article article : articleList) {
            articleRepository.save(article);
        }
    }
}

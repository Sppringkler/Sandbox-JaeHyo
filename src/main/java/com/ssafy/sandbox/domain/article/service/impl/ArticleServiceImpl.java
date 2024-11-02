package com.ssafy.sandbox.domain.article.service.impl;

import com.ssafy.sandbox.domain.article.dto.ArticleReqDto;
import com.ssafy.sandbox.domain.article.entity.Article;
import com.ssafy.sandbox.domain.article.mapper.ArticleServiceMapper;
import com.ssafy.sandbox.domain.article.repository.ArticleRepository;
import com.ssafy.sandbox.domain.article.service.ArticleService;
import com.ssafy.sandbox.domain.article.dto.ReadArticlesWithPagingRespDto;

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
    public Page<ReadArticlesWithPagingRespDto> readArticlesWithPaging(int size, int page) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Article> articlesPage = articleRepository.findPagedArticles(pageable);

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

package com.ssafy.sandbox.domain.article.repository;

import com.ssafy.sandbox.domain.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepository{

    Page<Article> findPagedArticles(Pageable pageable);

    void save(Article article);
}

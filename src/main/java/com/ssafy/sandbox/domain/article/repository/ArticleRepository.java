package com.ssafy.sandbox.domain.article.repository;

import com.ssafy.sandbox.domain.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ArticleRepository{

    Page<Article> findPagedArticlesOffset(Pageable pageable);
    Page<Article> findPagedArticlesCursor(Pageable pageable, int cursorId);

    void save(Article article);
}

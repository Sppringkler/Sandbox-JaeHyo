package com.ssafy.sandbox.domain.article.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.domain.article.entity.Article;
import com.ssafy.sandbox.domain.article.repository.ArticleRepository;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ssafy.sandbox.domain.article.entity.QArticle.article;

@Repository
public class ArticleRepositoryImpl implements ArticleRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public ArticleRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Article> findPagedArticlesOffset(Pageable pageable) {
        List<Article> articleList = jpaQueryFactory
                .selectFrom(article)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = Optional.ofNullable(jpaQueryFactory
                .select(article.count())
                .from(article)
                .fetchOne()).orElse(0L);

        return new PageImpl<>(articleList, pageable, total);
    }

    @Override
    public Page<Article> findPagedArticlesCursor(Pageable pageable, int cursorId) {
        List<Article> articleList = jpaQueryFactory
                .selectFrom(article)
                .where(article.id.gt(cursorId))
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(articleList);
    }

    @Override
    public void save(Article article) {
        entityManager.persist(article);
    }
}

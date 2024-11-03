package com.ssafy.sandbox.domain.article.mapper;

import com.ssafy.sandbox.domain.article.dto.response.ReadArticlesWithPagingRespDto;
import com.ssafy.sandbox.domain.article.entity.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArticleServiceMapper {

    List<ReadArticlesWithPagingRespDto> to(List<Article> articles);

    ReadArticlesWithPagingRespDto to(Article article);
}

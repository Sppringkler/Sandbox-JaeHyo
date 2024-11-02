package com.ssafy.sandbox.domain.article.mapper;

import com.ssafy.sandbox.domain.article.dto.response.ReadArticlesResDto;
import com.ssafy.sandbox.domain.article.entity.Article;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoServiceMapper {

    List<ReadArticlesResDto> toReadArticlesResDto(List<Article> todos);
}
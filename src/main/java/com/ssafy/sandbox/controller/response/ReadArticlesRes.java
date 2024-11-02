package com.ssafy.sandbox.controller.response;

import com.ssafy.sandbox.domain.article.dto.response.ReadArticlesResDto;
import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class ReadArticlesRes {
    private final String message;
    private final List<ReadArticlesResDto> articles;
}

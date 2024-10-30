package com.ssafy.sandbox.controller.response;

import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Builder
@Getter
public class ReadTodosRes {
    private final String message;
    private final List<ReadTodosResDto> todos;
}

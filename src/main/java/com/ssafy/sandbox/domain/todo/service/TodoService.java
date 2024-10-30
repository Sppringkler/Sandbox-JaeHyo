package com.ssafy.sandbox.domain.todo.service;

import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import com.ssafy.sandbox.domain.todo.entity.Todo;

import java.util.List;

public interface TodoService {
    public List<ReadTodosResDto> readTodos();
}

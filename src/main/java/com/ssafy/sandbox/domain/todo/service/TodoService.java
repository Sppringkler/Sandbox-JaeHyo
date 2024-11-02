package com.ssafy.sandbox.domain.todo.service;

import com.ssafy.sandbox.controller.request.CreateTodosReq;
import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;

import java.util.List;

public interface TodoService {
    List<ReadTodosResDto> readTodos();
    int createTodos(CreateTodosReq req);
    void updateTodos(int id);
    void deleteTodos(int id);
}

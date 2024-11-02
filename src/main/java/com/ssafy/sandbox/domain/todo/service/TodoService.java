package com.ssafy.sandbox.domain.todo.service;

import com.ssafy.sandbox.controller.request.CreateTodoReq;
import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;

import java.util.List;

public interface TodoService {

    List<ReadTodosResDto> readTodos();

    int createTodo(CreateTodoReq req);

    void deleteTodo(int todoId);

    void updateTodo(int todoId);

}

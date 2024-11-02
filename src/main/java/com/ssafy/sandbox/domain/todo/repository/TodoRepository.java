package com.ssafy.sandbox.domain.todo.repository;

import com.ssafy.sandbox.controller.request.CreateTodosReq;
import com.ssafy.sandbox.domain.todo.entity.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
    int save(Todo todo);
    void deleteById(int id);
    Todo findById(int id);
}

package com.ssafy.sandbox.domain.todo.repository;

import com.ssafy.sandbox.domain.todo.entity.Todo;

import java.util.List;

public interface TodoRepository {
    List<Todo> findAll();
}

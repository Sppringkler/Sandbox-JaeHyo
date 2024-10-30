package com.ssafy.sandbox.domain.todo.repository;

import com.ssafy.sandbox.domain.todo.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository {

    List<Todo> findAll();

    int save(Todo todo);

    void deleteById(int todoId);

    Optional<Todo> findById(int todoId);
}

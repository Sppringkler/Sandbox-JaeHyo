package com.ssafy.sandbox.domain.todo.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.domain.todo.entity.Todo;
import com.ssafy.sandbox.domain.todo.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.sandbox.domain.todo.entity.QTodo.todo;

@Repository
@RequiredArgsConstructor
public class TodoRepositoryImpl implements TodoRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Todo> findAll() {
        return jpaQueryFactory.selectFrom(todo).fetch();
    }
}

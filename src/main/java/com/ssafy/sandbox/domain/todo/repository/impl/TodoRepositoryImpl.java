package com.ssafy.sandbox.domain.todo.repository.impl;

import com.ssafy.sandbox.domain.todo.entity.Todo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.domain.todo.repository.TodoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.ssafy.sandbox.domain.todo.entity.QTodo.todo;

@Repository
public class TodoRepositoryImpl implements TodoRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public TodoRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Todo> findAll() {
        return jpaQueryFactory
                .selectFrom(todo)
                .fetch();
    }

    @Override
    public int save(Todo todo) {
        entityManager.persist(todo);

        return todo.getId();
    }

    @Override
    public void deleteById(int todoId) {
        jpaQueryFactory
            .delete(todo)
            .where(todo.id.eq(todoId))
            .execute();
    }

    @Override
    public Optional<Todo> findById(int todoId) {
        return Optional.ofNullable(
                jpaQueryFactory
                    .selectFrom(todo)
                    .where(todo.id.eq(todoId))
                    .fetchOne()
        );
    }
}

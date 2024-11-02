package com.ssafy.sandbox.domain.todo.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.controller.request.CreateTodosReq;
import com.ssafy.sandbox.domain.todo.entity.Todo;
import com.ssafy.sandbox.domain.todo.repository.TodoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ssafy.sandbox.domain.todo.entity.QTodo.todo;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    private EntityManager entityManager;

    public TodoRepositoryImpl(EntityManager entityManager){
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public List<Todo> findAll() {
        return jpaQueryFactory.selectFrom(todo).fetch();
    }

    @Override
    public int save(Todo todo) {
        //Quertfactory를 통해서는 insert X -> entityManager를 repository에 선언해둬야겠다.!
        entityManager.persist(todo);
        return todo.getId(); //영속성 -> pk인 id 값 얻어올 수 있는 것
    }

    @Override
    public void deleteById(int id) {
        jpaQueryFactory
                .delete(todo)
                .where(todo.id.eq(id))
                .execute();
    }

    @Override
    public Todo findById(int id) { //여기서 boolean 업데이트 x -> service에서 set -> transactional -> db 반영
        return jpaQueryFactory
                .selectFrom(todo)
                .where(todo.id.eq(id))
                .fetchOne();
    }
}

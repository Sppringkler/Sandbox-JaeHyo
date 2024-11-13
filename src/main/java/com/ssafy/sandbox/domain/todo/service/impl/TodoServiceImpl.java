package com.ssafy.sandbox.domain.todo.service.impl;

import com.ssafy.sandbox.controller.request.CreateTodoReq;
import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import com.ssafy.sandbox.domain.todo.entity.Todo;

import com.ssafy.sandbox.domain.todo.mapper.TodoServiceMapper;
import com.ssafy.sandbox.domain.todo.repository.TodoRepository;
import com.ssafy.sandbox.domain.todo.service.TodoService;
import com.ssafy.sandbox.global.exception.type.DatabaseException;
import com.ssafy.sandbox.global.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepository;

    private final TodoServiceMapper mapper;

    @Override
    public List<ReadTodosResDto> readTodos() {
        List<Todo> todoList = todoRepository.findAll();

        return mapper.toReadTodosResDto(todoList);
    }

    @Override
    @Transactional
    public int createTodo(CreateTodoReq req) {
        Todo todo = Todo.builder()
                .content(req.getContent())
                .completed(false)
                .build();

        return todoRepository.save(todo);
    }

    @Override
    @Transactional
    public void updateTodo(int todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new DatabaseException(ErrorCode.TODO_NOT_FOUND));

        todo.setCompleted(!todo.isCompleted());
    }

    @Override
    @Transactional
    public void deleteTodo(int todoId) {
        Todo todo = todoRepository.findById(todoId)
                .orElseThrow(() -> new DatabaseException(ErrorCode.TODO_NOT_FOUND));

        todoRepository.deleteById(todo.getId());
    }
}

package com.ssafy.sandbox.domain.todo.service.impl;

import com.ssafy.sandbox.controller.response.ReadTodosRes;
import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import com.ssafy.sandbox.domain.todo.entity.Todo;
import com.ssafy.sandbox.domain.todo.mapper.TodoServiceMapper;
import com.ssafy.sandbox.domain.todo.repository.TodoRepository;
import com.ssafy.sandbox.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository todoRepository;
    private final TodoServiceMapper todoServiceMapper;

    @Override
    @Transactional(readOnly=true)
    public List<ReadTodosResDto> readTodos(){
        return todoServiceMapper.toReadTodosResDto(todoRepository.findAll());
    }
}

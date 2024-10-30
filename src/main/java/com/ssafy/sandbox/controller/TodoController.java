package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.controller.response.ReadTodosRes;
import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import com.ssafy.sandbox.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<ReadTodosRes> readTodos() {
        List<ReadTodosResDto> todoList = todoService.readTodos();

        return ResponseEntity.ok(new ReadTodosRes("정상적으로 요청되었습니다.",todoList));
    }

}

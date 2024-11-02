package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.controller.request.CreateTodosReq;
import com.ssafy.sandbox.controller.response.CommonRes;
import com.ssafy.sandbox.controller.response.ReadTodosRes;
import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import com.ssafy.sandbox.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<ReadTodosRes> readTodos() {
        List<ReadTodosResDto> todoList = todoService.readTodos();

        return ResponseEntity.ok(new ReadTodosRes("정상적으로 요청되었습니다.",todoList));
    }

    @PostMapping
    public ResponseEntity<CommonRes> createTodos(@RequestBody CreateTodosReq createTodosreq) {
        int todoId = todoService.createTodos(createTodosreq);

        return ResponseEntity.ok(new CommonRes(todoId+"의 todo가 생성되었습니다."));
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<CommonRes> updateTodos(@PathVariable("todoId") int id) {
        todoService.updateTodos(id);

        return ResponseEntity.ok(new CommonRes(id+"의 todo가 삭제되었습니다."));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<CommonRes> deleteTodos(@PathVariable("todoId") int id) {
        todoService.deleteTodos(id);

        return ResponseEntity.ok(new CommonRes(id+"의 completed가 정상적으로 토글되었습니다."));
    }

}

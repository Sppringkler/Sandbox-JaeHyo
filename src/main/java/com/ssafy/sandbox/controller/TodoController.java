package com.ssafy.sandbox.controller;

import com.ssafy.sandbox.controller.request.CreateTodoReq;
import com.ssafy.sandbox.controller.response.ReadTodosRes;
import com.ssafy.sandbox.controller.response.SuccessRes;
import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import com.ssafy.sandbox.domain.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping("")
    public ResponseEntity<ReadTodosRes> readTodos() {
        List<ReadTodosResDto> readTodoRespDto = todoService.readTodos();

        return ResponseEntity.ok(
                new ReadTodosRes("정상적으로 요청되었습니다.", readTodoRespDto)
        );
    }

    @PostMapping("")
    public ResponseEntity<SuccessRes> createTodo(@RequestBody CreateTodoReq req) {
        int id = todoService.createTodo(req);

        return ResponseEntity.ok(
                new SuccessRes(id + "의 todo가 정상적으로 생성되었습니다."));
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<SuccessRes> updateTodo(@PathVariable int todoId) {
        todoService.updateTodo(todoId);

        return ResponseEntity.ok(
                new SuccessRes(todoId + "의 completed가 정상적으로 토글되었습니다."));
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<SuccessRes> deleteTodo(@PathVariable int todoId) {
        todoService.deleteTodo(todoId);

        return ResponseEntity.ok(
                new SuccessRes(todoId + "의 todo가 삭제되었습니다."));
    }
}

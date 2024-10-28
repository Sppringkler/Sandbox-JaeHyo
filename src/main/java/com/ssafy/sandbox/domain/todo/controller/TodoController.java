package com.ssafy.sandbox.domain.todo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

}

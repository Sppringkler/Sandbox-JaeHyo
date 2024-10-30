package com.ssafy.sandbox.domain.todo.mapper;

import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import com.ssafy.sandbox.domain.todo.entity.Todo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoServiceMapper {

    List<ReadTodosResDto> toReadTodosResDto(List<Todo> todos);
}
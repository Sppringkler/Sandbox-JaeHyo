package com.ssafy.sandbox.controller.response;

import com.ssafy.sandbox.domain.todo.dto.response.ReadTodosResDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadTodosRes {

    public String message;

    public List<ReadTodosResDto> todos;

}

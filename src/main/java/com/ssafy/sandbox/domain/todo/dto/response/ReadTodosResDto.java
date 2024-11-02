package com.ssafy.sandbox.domain.todo.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadTodosResDto {

    private Long id;
    private String content;
    private boolean completed;
}

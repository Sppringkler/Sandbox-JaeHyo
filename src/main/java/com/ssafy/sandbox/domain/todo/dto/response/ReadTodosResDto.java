package com.ssafy.sandbox.domain.todo.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReadTodosResDto {
    private int id;
    private String content;
    private boolean completed;
}

package com.ssafy.sandbox.domain.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment 지원
    private int id;

    private String content;

    @ColumnDefault("0")
    private boolean completed;
}

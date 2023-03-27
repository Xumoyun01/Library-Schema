package com.example.spring_project.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Card {
    private Integer cardId;
    private String name;
    private String cardNumber;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

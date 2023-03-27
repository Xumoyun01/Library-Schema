package com.example.spring_project.dto;

import com.example.spring_project.model.Card;
import com.example.spring_project.model.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Getter
@Setter
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer genderId;
    private String phone;
    private String password;
    private Integer age;
    private LocalDate birthDate;
    private Integer cardId;
    private Card card;
    private Gender gender;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

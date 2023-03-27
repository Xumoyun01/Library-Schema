package com.example.spring_project.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private boolean success;
    private String message;
    /*
    0 - hammasi yaxshi
    -1 - not found
    -2 - validation error;
    -3 - database error;
    */
    private int code;
    private T data;
}

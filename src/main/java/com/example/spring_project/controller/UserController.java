package com.example.spring_project.controller;

import com.example.spring_project.dto.ResponseDto;
import com.example.spring_project.dto.UserDto;
import com.example.spring_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/create")
    public ResponseDto<UserDto> createUser(@RequestBody UserDto dto){
        return userService.createUser(dto);
    }
    @GetMapping("/get")
    public ResponseDto<UserDto> getUser(@RequestParam(value = ("id")) Integer userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/update")
    public ResponseDto<UserDto> updateUser(@RequestBody UserDto dto, @RequestParam(value = ("id")) Integer userId) {
        return userService.updateUser(dto, userId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<UserDto> deleteUser(@RequestParam Integer userId) {
        return userService.deleteUser(userId);
    }
    public ResponseDto<List<UserDto>> getAllUser(){
        return userService.getAllUser();
    }
}

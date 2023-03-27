package com.example.spring_project.service;

import com.example.spring_project.dto.ResponseDto;
import com.example.spring_project.dto.UserDto;
import com.example.spring_project.model.Users;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<Users> userList;
    private Integer userIndex;
    private CardService cardService;
    public UserService(CardService cardService){
        this.cardService = cardService;
        this.userList = new ArrayList<>();
        this.userIndex = 0;
    }

    public ResponseDto<UserDto> createUser(UserDto dto) {
        if (this.cardService.getCard(dto.getCardId()).getData() == null){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("Card is not found")
                    .build();
        }
        Users user = toEntity(dto);
        user.setUserId(++this.userIndex);
        user.setCreateAt(LocalDateTime.now());
        this.userList.add(user);
        return ResponseDto.<UserDto>builder()
                .message("User successful created")
                .success(true)
                .data(dto)
                .build();
    }

    public ResponseDto<UserDto> getUser(Integer userId) {
        for (Users user : userList) {
            if (user.getUserId().equals(userId)){
                UserDto dto = toDto(user);
                return ResponseDto.<UserDto>builder()
                        .message("Ok")
                        .success(true)
                        .data(dto)
                        .build();
            }
        }
        return ResponseDto.<UserDto>builder()
                .message("User is not found")
                .success(false)
                .build();
    }

    public ResponseDto<UserDto> updateUser(UserDto dto, Integer userId) {
        if (this.cardService.getCard(dto.getCardId()).getData() == null){
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("Card is not found")
                    .build();
        }
        for (Users user : userList) {
            if (user.getUserId().equals(userId)){
                user = toEntity(dto);
                user.setUpdateAt(LocalDateTime.now());
                this.userList.add(user);
                return ResponseDto.<UserDto>builder()
                        .message("User successful created")
                        .success(true)
                        .build();
            }
        }
        return ResponseDto.<UserDto>builder()
                .message("User is not found")
                .success(false)
                .build();
    }

    public ResponseDto<UserDto> deleteUser(Integer userId) {
        for (Users user : userList) {
            if (user.getUserId().equals(userId)){
                this.userList.remove(user);
                return ResponseDto.<UserDto>builder()
                        .success(true)
                        .message("Ok")
                        .build();
            }
        }
        return ResponseDto.<UserDto>builder()
                .message("User is not found")
                .success(false)
                .build();
    }

    public ResponseDto<List<UserDto>> getAllUser() {
        List<UserDto> userDtoList = this.userList
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseDto.<List<UserDto>>builder()
                .message("Ok")
                .data(this.userList
                        .stream()
                        .map(this::toDto)
                        .toList())
                .build();
    }
    public ResponseDto<List<UserDto>> getAgeUser(){
        List<UserDto> userDtoList = this.userList.stream()
                .filter(age ->{
                    if (age.getAge() > 18){
                        return true;
                    }else {
                        return false;
                    }
                })
                .map(this::toDto)
                .toList();
        return ResponseDto.<List<UserDto>>builder()
                .message("Ok")
                .success(true)
                .data(userDtoList)
                .build();
    }

    private Users toEntity(UserDto dto){
        Users user = new Users();
        user.setLastName(dto.getLastName());
        user.setFirstName(dto.getFirstName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setAge(dto.getAge());
        user.setCardId(dto.getCardId());
        user.setCard(dto.getCard());
        user.setGender(dto.getGender());
        user.setBirthDate(dto.getBirthDate());
        return user ;
    }
    private UserDto toDto(Users user){
        UserDto dto = new UserDto();
        dto.setFirstName(user.getFirstName());
        dto.setEmail(user.getEmail());
        dto.setLastName(user.getLastName());
        dto.setPassword(user.getPassword());
        dto.setAge(user.getAge());
        dto.setCardId(user.getCardId());
        dto.setCard(user.getCard());
        dto.setGender(user.getGender());
        dto.setBirthDate(user.getBirthDate());
        dto.setCreateAt(user.getCreateAt());
        dto.setUpdateAt(user.getUpdateAt());
        return dto;
    }
}

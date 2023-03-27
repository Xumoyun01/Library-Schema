package com.example.spring_project.service;

import com.example.spring_project.model.Gender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenderService {
    private List<Gender> genderList;
    private Integer index;
    public GenderService(){
        this.genderList = new ArrayList<>();
        this.index = 0;
    }
    public String create(Gender gender) {
        gender.setGenderId(++this.index);
        gender.setCreateAt(LocalDateTime.now());
        this.genderList.add(gender);
        return "Gender successful created";
    }

    public Gender get(Integer id) {
        for (Gender gender:this.genderList) {
            if (gender.getGenderId().equals(id) && gender.getDeleteAt() == null){
                return gender;
            }
        }
        return null;
    }

    public String update(Gender gender, Integer id) {
        Gender g = get(id);
        if (g == null){
            return "Gender is not found";
        }
        g.setName(gender.getName());
        g.setUpdateAt(LocalDateTime.now());
        genderList.add(g);
        return "Gender successful updated";
    }

    public String delete(Integer id) {
        Gender gender = get(id);
        if(gender == null){
            return "Gender is not found";
        }
        gender.setDeleteAt(LocalDateTime.now());
        this.genderList.add(gender);
        return "Gender successful deleted";
    }
}

package com.example.spring_project.controller;

import com.example.spring_project.model.Gender;
import com.example.spring_project.service.GenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("gender")
public class GenderController {
    private GenderService genderService;
    public GenderController(GenderService genderService){
        this.genderService = genderService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Gender gender){
        String result = genderService.create(gender);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getUser(@RequestParam Integer id) {
        Gender result = genderService.get(id);
        if (result == null){
            return ResponseEntity.ok("Gender is not found");
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody Gender gender, @RequestParam Integer id) {
        String result = genderService.update(gender, id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam Integer id) {
        String result = genderService.delete(id);
        return ResponseEntity.ok(result);
    }

}

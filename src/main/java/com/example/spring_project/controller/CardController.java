package com.example.spring_project.controller;

import com.example.spring_project.dto.CardDto;
import com.example.spring_project.dto.ResponseDto;
import com.example.spring_project.service.CardService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {
    private CardService cardService;
    public CardController(CardService cardService){
        this.cardService = cardService;
    }
    @PostMapping("/create")
    public ResponseDto<CardDto> createCard(@RequestBody CardDto cardDto){
        return cardService.createCard(cardDto);
    }
    @GetMapping("/get")
    public ResponseDto<CardDto> getCard(@RequestParam Integer id) {
        return cardService.getCard(id);
    }

    @PutMapping("/update")
    public ResponseDto<CardDto> updateCard(@RequestBody CardDto cardDto, @RequestParam Integer id) {
        return cardService.updateCard(cardDto, id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<CardDto> deleteCard(@RequestParam Integer id) {
        return cardService.deleteCard(id);
    }
}

package com.example.spring_project.service;

import com.example.spring_project.dto.CardDto;
import com.example.spring_project.dto.ResponseDto;
import com.example.spring_project.model.Card;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {
    private List<Card> cardList;
    private Integer index;
    public CardService(){
        this.cardList = new ArrayList<>();
        this.index = 0;
    }
    public ResponseDto<CardDto> createCard(CardDto dto) {
        if (dto.getCardNumber().length() > 25){
            return ResponseDto.<CardDto>builder()
                    .message("Card code error")
                    .success(false)
                    .data(dto)
                    .build();
        }
        Card card = toEntity(dto);
        card.setCardId(++this.index);
        card.setCreateAt(LocalDateTime.now());
        this.cardList.add(card);
        return ResponseDto.<CardDto>builder()
                .message("Ok")
                .success(true)
                .data(toDto(card))
                .build();
    }

    public ResponseDto<CardDto> getCard(Integer cardId) {
        for (Card card:this.cardList) {
            if (card.getCardId().equals(cardId)){
                return ResponseDto.<CardDto>builder()
                        .message("Ok")
                        .success(true)
                        .data(toDto(card))
                        .build();
            }
        }
        return ResponseDto.<CardDto>builder()
                .message("Card is not found")
                .code(-1)
                .build();
    }

    public ResponseDto<CardDto> updateCard(CardDto dto, Integer cardId) {
        for (Card card : cardList) {
            if (card.getCardId().equals(cardId)){
                card = toEntity(dto);
                card.setUpdateAt(LocalDateTime.now());
                this.cardList.add(card);
                return ResponseDto.<CardDto>builder()
                        .message("Ok")
                        .data(toDto(card))
                        .success(true)
                        .build();
            }
        }
        return ResponseDto.<CardDto>builder()
                .message("Card is not found")
                .code(-1)
                .build();
    }

    public ResponseDto<CardDto> deleteCard (Integer cardId) {
        for (Card card : cardList) {
            if (card.getCardId().equals(cardId)){
                this.cardList.remove(card);
                return ResponseDto.<CardDto>builder()
                        .message("Ok")
                        .data(toDto(card))
                        .success(true)
                        .build();
            }
        }
        return ResponseDto.<CardDto>builder()
                .message("Card is not found")
                .code(-1)
                .build();
    }
    private Card toEntity(CardDto dto) {
        Card card = new Card();
        card.setCardNumber(dto.getCardNumber());
        card.setName(dto.getName());
        return card;
    }
    private CardDto toDto(Card card) {
        CardDto dto = new CardDto();
        dto.setCardId(card.getCardId());
        dto.setName(card.getName());
        dto.setCardNumber(card.getCardNumber());
        dto.setCreateAt(card.getCreateAt());
        dto.setUpdateAt(card.getUpdateAt());
        return dto;
    }
}

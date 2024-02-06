package com.slimdevs.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slimdevs.cards.constants.CardConstants;
import com.slimdevs.cards.dto.CardDto;
import com.slimdevs.cards.dto.ResponseDto;
import com.slimdevs.cards.service.ICardService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CardController {

    ICardService iCardService;
    
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(String mobileNumber) {
        iCardService.createCard(mobileNumber);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCard(String mobileNumber) {
        CardDto cardDto = iCardService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardDto);
    }
}

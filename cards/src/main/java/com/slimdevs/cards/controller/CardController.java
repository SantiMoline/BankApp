package com.slimdevs.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<ResponseDto> createCard(@RequestParam String mobileNumber) {
        iCardService.createCard(mobileNumber);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCard(@RequestParam String mobileNumber) {
        CardDto cardDto = iCardService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardDto);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@RequestBody CardDto cardDto) {
        boolean isUpdated = iCardService.updateCard(cardDto);
        if (isUpdated) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam String mobileNumber) {
        boolean isDeleted = iCardService.deleteCard(mobileNumber);

        if(isDeleted) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_DELETE));
        }
    }
}

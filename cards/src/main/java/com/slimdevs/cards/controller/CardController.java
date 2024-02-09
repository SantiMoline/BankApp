package com.slimdevs.cards.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.slimdevs.cards.constants.CardConstants;
import com.slimdevs.cards.dto.CardDto;
import com.slimdevs.cards.dto.ErrorResponseDto;
import com.slimdevs.cards.dto.ResponseDto;
import com.slimdevs.cards.service.ICardService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(
    name = "CRUD REST APIs for Cards in BankApp",
    description = "CRUD REST APIs in BankApp to CREATE, UPDATE, FETCH AND DELETE card's details"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CardController {

    ICardService iCardService;
    
    @Operation(
        summary = "Creates card REST API.",
        description = "REST API to create a new Card in BankApp."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED."
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error.",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam 
                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits.")
                            String mobileNumber) {
        iCardService.createCard(mobileNumber);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @Operation(
        summary = "Fetches card's details REST API.",
        description = "REST API to fetch a Card's based on a given mobile number."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK."
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error.",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardDto> fetchCard(@Valid @RequestParam 
                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits.")
                            String mobileNumber) {
        CardDto cardDto = iCardService.fetchCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardDto);
    }

    @Operation(
        summary = "Updates Card's details REST API.",
        description = "REST API to update Card's based on a given card number."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK."
        ),
        @ApiResponse(
            responseCode = "417",
            description = "HTTP Status Expectation Failed."
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error.",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardDto) {
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

    @Operation(
        summary = "Deletes Card's details REST API.",
        description = "REST API to delete Card's based on a given mobile number."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK."
        ),
        @ApiResponse(
            responseCode = "417",
            description = "HTTP Status Expectation Failed."
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error.",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@Valid @RequestParam
                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits.")
                            String mobileNumber) {
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

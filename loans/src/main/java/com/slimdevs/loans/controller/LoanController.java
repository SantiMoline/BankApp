package com.slimdevs.loans.controller;

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

import com.slimdevs.loans.constant.LoanConstants;
import com.slimdevs.loans.dto.ErrorResponseDto;
import com.slimdevs.loans.dto.LoanDto;
import com.slimdevs.loans.dto.ResponseDto;
import com.slimdevs.loans.service.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD REST APIs for Loans in BankApp.",
    description = "Create, fetch, update & deletes Loan's details.")
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class LoanController {
    
    ILoanService iLoanService;

    @Operation(summary = "Create loan REST API"
        ,description = "Create new loan in BankApp.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = LoanConstants.MESSAGE_201),
        @ApiResponse(
            responseCode = "500",
            description = "Http Status Internal Server Error.",
            content = @Content (
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )   
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam 
                                        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
                                        String mobileNumber) {
        iLoanService.createLoan(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }


    @Operation(summary = "Fetch loan's details REST API.",
        description = "Fetches loan's details based on a given mobile number.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = LoanConstants.MESSAGE_200),
        @ApiResponse(
            responseCode = "500",
            description = "Http Status Internal Server Error.",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam 
                                        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
                                        String mobileNumber) {
        LoanDto loanDto = iLoanService.fetchLoan(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(loanDto);
    }

    @Operation(summary = "Updates loan's details REST API.",
        description = "Updates loan's details based on a Loan Number.")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = LoanConstants.MESSAGE_200
        ),
        @ApiResponse(
            responseCode = "417",
            description = LoanConstants.MESSAGE_417_UPDATE
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Http Status Internal Server Error.",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoanDto loanDto) {
        boolean isUpdate = iLoanService.updateLoan(loanDto);
        if (isUpdate) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(summary = "Dete loan's details REST API",
        description = "Deletes loan's details based on a mobile number.")
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = LoanConstants.MESSAGE_200
        ),
        @ApiResponse(
            responseCode = "417",
            description = LoanConstants.MESSAGE_417_DELETE
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Http Status Internal Server Error.",
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam 
                                                @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
                                                String mobileNumber) {
        boolean isDeleted = iLoanService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }

}

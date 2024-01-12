package com.slimdevs.accounts.controller;

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

import com.slimdevs.accounts.constants.AccountConstants;
import com.slimdevs.accounts.dto.CustomerDto;
import com.slimdevs.accounts.dto.ErrorResponseDto;
import com.slimdevs.accounts.dto.ResponseDto;
import com.slimdevs.accounts.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD REST APIs for Accounts in BankApp.",
    description = "Create, update, fetch and delete account details.")
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {
    
    IAccountService iAccountService;

    @Operation(summary = "Create account REST API",
        description = "Creates new customer & new account in BankApp.")
    @ApiResponse(responseCode = "201", description = AccountConstants.MESSAGE_201)
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountService.createAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch account's details REST API",
        description = "Fetches customer & account's details based on mobile number.")
    @ApiResponse(responseCode = "200", description = AccountConstants.MESSAGE_200)
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.") 
                                        String mobileNumber) {
        CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerDto);
    }

    @Operation(summary = "Updated account's details REST API",
        description = "Updates customer & account's details based on an account number.")
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = AccountConstants.MESSAGE_200
        ),
        @ApiResponse(
            responseCode = "500",
            description = AccountConstants.MESSAGE_500,
            content = @Content(
                schema = @Schema(implementation = ErrorResponseDto.class)
            )
        )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }
    }

    @Operation(summary = "Delete customer & account's details REST API",
        description = "Deletes customer & account's details based on a mobile number.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = AccountConstants.MESSAGE_200),
        @ApiResponse(responseCode = "500", description = AccountConstants.MESSAGE_500)
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam 
                                        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
                                        String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);

        if (isDeleted) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountConstants.STATUS_500, AccountConstants.MESSAGE_500));
        }        
    }

}

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
import com.slimdevs.loans.dto.LoanDto;
import com.slimdevs.loans.dto.ResponseDto;
import com.slimdevs.loans.service.ILoanService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class LoanController {
    
    ILoanService iLoanService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam 
                                        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
                                        String mobileNumber) {
        iLoanService.createLoan(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam 
                                        @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
                                        String mobileNumber) {
        LoanDto loanDto = iLoanService.fetchLoan(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(loanDto);
    }

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

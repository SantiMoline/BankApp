package com.slimdevs.loans.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slimdevs.loans.service.ILoanService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LoanController {
    
    ILoanService loanService;

    
}

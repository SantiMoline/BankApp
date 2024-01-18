package com.slimdevs.loans.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.slimdevs.loans.constant.LoanConstants;
import com.slimdevs.loans.dto.LoanDto;
import com.slimdevs.loans.entity.Loan;
import com.slimdevs.loans.exception.LoanAlreadyExistsException;
import com.slimdevs.loans.exception.ResourceNotFoundException;
import com.slimdevs.loans.mapper.LoanMapper;
import com.slimdevs.loans.repository.LoanRepository;
import com.slimdevs.loans.service.ILoanService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoanService  {
    
    private LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("There's already a loan registered with given mobile number: " + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
        
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber.toString()) 
            );

        return LoanMapper.mapToLoanDto(loan, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan loan = loanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
            () -> new ResourceNotFoundException("Loan", "loanNumber", loanDto.getLoanNumber())
        );

        LoanMapper.mapToLoan(loanDto, loan);
        loanRepository.save(loan);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber.toString())
        );

        loanRepository.deleteById(loan.getLoanId());
        return true;
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);

        //Agregados para realizar pruebas iniciales en los endpoint. Luego se configurará la parte de auditoría y autocompletado.
        newLoan.setCreatedAt(LocalDateTime.now());
        newLoan.setCreatedBy("Santi");
        return newLoan;
    }


}

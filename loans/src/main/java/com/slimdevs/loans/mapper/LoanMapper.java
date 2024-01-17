package com.slimdevs.loans.mapper;

import com.slimdevs.loans.dto.LoanDto;
import com.slimdevs.loans.entity.Loan;

public class LoanMapper {
    
    public static LoanDto mapToLoanDto(Loan loan, LoanDto loanDto) {
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setLoanType(loan.getLoanType());
        loanDto.setMobileNumber(loan.getMobileNumber());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());
        loanDto.setTotalLoan(loan.getTotalLoan());

        return loanDto;
    }

    public static Loan mapToLoan(LoanDto loanDto, Loan loan) {
        loan.setAmountPaid(loanDto.getAmountPaid());
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setLoanType(loanDto.getLoanType());
        loan.setMobileNumber(loanDto.getMobileNumber());
        loan.setOutstandingAmount(loanDto.getOutstandingAmount());
        loan.setTotalLoan(loanDto.getTotalLoan());
        
        return loan;
    }
}

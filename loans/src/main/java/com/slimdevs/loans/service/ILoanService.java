package com.slimdevs.loans.service;

import com.slimdevs.loans.dto.LoanDto;

public interface ILoanService {
    
    /**
     * 
     * @param mobileNumber - Customer's mobile number.
     */
    void createLoan(String mobileNumber);

    /**
     * 
     * @param mobileNumber - Customer's mobile number.
     * @return Loan's details based on a given mobile number.
     */
    LoanDto fetchLoan(String mobileNumber);


    /**
     * 
     * @param loanDto - LoanDto Object
     * @return boolean indicating if the update of the loan's details was successful or not.
     */
    boolean updateLoan(LoanDto loanDto);

    /**
     * 
     * @param mobileNumber - Input mobile number.
     * @return boolean indicating if the deletion of the loan's details was successful or not.
     */
    boolean deleteLoan(String mobileNumber);
}

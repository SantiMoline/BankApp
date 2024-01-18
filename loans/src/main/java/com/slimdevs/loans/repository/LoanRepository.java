package com.slimdevs.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slimdevs.loans.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    
    Optional<Loan> findByMobileNumber(String mobileNumber);
    Optional<Loan> findByLoanNumber(String loanNumber);
}

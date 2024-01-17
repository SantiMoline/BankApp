package com.slimdevs.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slimdevs.loans.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    
}

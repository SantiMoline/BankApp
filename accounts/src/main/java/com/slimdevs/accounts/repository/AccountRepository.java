package com.slimdevs.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.slimdevs.accounts.entity.Account;

import jakarta.transaction.Transactional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByCustomerId(Long customerId);
    
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
    
}

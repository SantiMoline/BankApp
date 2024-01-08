package com.slimdevs.accounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slimdevs.accounts.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
}

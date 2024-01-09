package com.slimdevs.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slimdevs.accounts.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    Optional<Customer> findByMobileNumber(String mobileNumber);
}

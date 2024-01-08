package com.slimdevs.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.slimdevs.accounts.constants.AccountConstants;
import com.slimdevs.accounts.dto.CustomerDto;
import com.slimdevs.accounts.entity.Account;
import com.slimdevs.accounts.entity.Customer;
import com.slimdevs.accounts.exception.CustomerAlreadyExistsException;
import com.slimdevs.accounts.mapper.CustomerMapper;
import com.slimdevs.accounts.repository.AccountRepository;
import com.slimdevs.accounts.repository.CustomerRepository;
import com.slimdevs.accounts.service.IAccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    AccountRepository accountRepository;
    CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
    
        if(optionalCustomer.isPresent())
            throw new CustomerAlreadyExistsException("Customer already registered with given phone number" + customerDto.getMobileNumber());
    
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        //Como al guardar el customer en la BD se le asignará un ID, acá tenemos que guardar el objeto que retorna el método Save para conocer ese Id.
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }
    
    /**
     * Receives a customer and generates them a new account. Inside contains the logic to assign a new AccountNumber.
     * @param customer - Customer Object
     * @return the new account's details.
     */
    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());

        //
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Anonymous");

        return newAccount;
    }
}

package com.slimdevs.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.slimdevs.accounts.constants.AccountConstants;
import com.slimdevs.accounts.dto.AccountDto;
import com.slimdevs.accounts.dto.CustomerDto;
import com.slimdevs.accounts.entity.Account;
import com.slimdevs.accounts.entity.Customer;
import com.slimdevs.accounts.exception.CustomerAlreadyExistsException;
import com.slimdevs.accounts.exception.ResourceNotFoundException;
import com.slimdevs.accounts.mapper.AccountMapper;
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

    
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
            ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));

        return customerDto; 
    }


    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if (accountDto != null) {
            Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "accountNumber", accountDto.getAccountNumber().toString())
            );
        
            AccountMapper.mapToAccount(accountDto, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "id", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    
    

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber) 
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
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

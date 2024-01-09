package com.slimdevs.accounts.service;

import com.slimdevs.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     * 
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);
    
    /**
     * 
     * @param mobileNumber - Input mobile number
     * @return Account's details given a mobileNumber.
     */
    CustomerDto fetchAccount(String mobileNumber);

}

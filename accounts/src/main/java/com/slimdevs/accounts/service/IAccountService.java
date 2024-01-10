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

    /**
     * 
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details was successful or not.
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     * 
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the deletion of account's details was successful or not.
     */
    boolean deleteAccount(String mobileNumber);
}

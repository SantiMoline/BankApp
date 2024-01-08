package com.slimdevs.accounts.service;

import com.slimdevs.accounts.dto.CustomerDto;

public interface IAccountService {
    /**
     * 
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);
}

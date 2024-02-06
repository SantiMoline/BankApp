package com.slimdevs.cards.service;

import com.slimdevs.cards.dto.CardDto;

public interface ICardService {
    
    /**
     * 
     * @param mobileNumber  - Customer's mobile number*/
    void createCard(String mobileNumber);

    /**
     * 
     * @param mobileNumber - Input mobile number.
     * @return Card details based on a given mobile number.
     */
    CardDto fetchCard(String mobileNumber);
}

package com.slimdevs.cards.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.slimdevs.cards.constants.CardConstants;
import com.slimdevs.cards.dto.CardDto;
import com.slimdevs.cards.entity.Card;
import com.slimdevs.cards.exception.ResourceNotFoundException;
import com.slimdevs.cards.mapper.CardMapper;
import com.slimdevs.cards.repository.CardRepository;
import com.slimdevs.cards.service.ICardService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {
    
    CardRepository cardRepository;

    @Override
    public void createCard(String mobileNumber) {

        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if (optionalCard.isPresent()) {
            //THROW CARD ALREADY EXISTS EXCEPTION.
        }
        cardRepository.save(createNewCard(mobileNumber));
    }

    private Card createNewCard(String mobileNumber) {
        Card newCard = new Card();
        
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));

        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);        
        
        return newCard;
    }

    @Override
    public CardDto fetchCard(String mobileNumber) {
        Card card = cardRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        return CardMapper.mapToCardDto(card, new CardDto());
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card = cardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
            () -> new ResourceNotFoundException("Card", "cardNumber", cardDto.getCardNumber())
        );

        CardMapper.mapToCard(cardDto, card);
        cardRepository.save(card);
        return true;
    }

    
    
    

}

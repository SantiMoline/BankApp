package com.slimdevs.cards.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class CardDto {
    
    @NotEmpty(message = "Mobile number cannot be null or empty.")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits.")
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be null or empty.")
    @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits.")
    private String cardNumber;

    @NotEmpty(message = "CardType cannot be a null or empty.")
    private String cardType;

    @Positive(message = "Total card limit must be greater than zero.")
    private int totalLimit;
    
    @PositiveOrZero(message = "Total amount used must be equal or greater than zero.")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount must be equal or greater than zero.")
    private int availableAmount;
}

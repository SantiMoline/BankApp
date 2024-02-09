package com.slimdevs.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
    name = "Card",
    description = "Schema to hold card's information."
    )
@Data
public class CardDto {
    
    @Schema(description = "Customer's mobile number", example = "4444444444")
    @NotEmpty(message = "Mobile number cannot be null or empty.")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits.")
    private String mobileNumber;

    @Schema(description = "Card's number", example = "100354678539")
    @NotEmpty(message = "Card number cannot be null or empty.")
    @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits.")
    private String cardNumber;

    @Schema(description = "Card's type", example = "Credit Card")
    @NotEmpty(message = "CardType cannot be a null or empty.")
    private String cardType;

    @Schema(description = "Total amount limit available against a card.", example = "250000")
    @Positive(message = "Total card limit must be greater than zero.")
    private int totalLimit;
    
    @Schema(description = "Total amount used by the customer with this card", example = "25000")
    @PositiveOrZero(message = "Total amount used must be equal or greater than zero.")
    private int amountUsed;

    @Schema(description = "Total available amount in this card", example = "225000")
    @PositiveOrZero(message = "Total available amount must be equal or greater than zero.")
    private int availableAmount;
}

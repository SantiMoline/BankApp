package com.slimdevs.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Loan", description = "Schema to hold loan's details information.")
@Data
public class LoanDto {
    
    @Schema(description = "Customer's mobile number", example = "4444444444")
    @NotEmpty(message = "Mobile number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
    private String mobileNumber;
    
    @Schema(description = "Loans's number of the customer.", example = "123654765811")
    @NotEmpty(message = "Loan number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "Loan number must have 12 digits.")
    private String loanNumber;
    
    @Schema(description = "Loan's type in BankApp.", example = "Home Loan")
    @NotEmpty(message = "Loan type cannot be null or empty.")
    private String loanType;
    
    @Schema(description = "Total loan's amount.", example = "1000")
    @Positive(message = "Total loan amount should be greater than zero.")
    private int totalLoan;

    @Schema(description = "Total loan's amount already paid.", example = "750")
    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero.")
    private int amountPaid;

    @Schema(description = "Total outstanding amount against a loan", example = "250")
    @PositiveOrZero(message = "Total outstanding amount paid should be equal or greater than zero.")
    private int outstandingAmount;
}

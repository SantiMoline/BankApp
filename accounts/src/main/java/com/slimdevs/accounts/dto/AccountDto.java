package com.slimdevs.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "Account", description = "Schema to hold Account information ")
@Data //Contiene la anotaciones: getter, setter, requieredArgsConstructor, toString, equalsAndHashCode, Value.
public class AccountDto {
   
    @Schema(description = "Account's number of BankApp.", example = "1236547658")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
    @NotEmpty(message = "Account number cannot be null or empty.")
    private Long accountNumber;

    @Schema(description = "Account type in Bank App.", example = "Savings")
    @NotEmpty(message = "Account type cannot be null or empty.")
    private String accountType;

    @Schema(description = "BankApp branch address", example =  "123 New York")
    @NotEmpty(message = "Branch address cannot be null or empty.")
    private String branchAddress;
}

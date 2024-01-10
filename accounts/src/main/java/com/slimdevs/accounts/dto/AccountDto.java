package com.slimdevs.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data //Contiene la anotaciones: getter, setter, requieredArgsConstructor, toString, equalsAndHashCode, Value.
public class AccountDto {
   
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
    @NotEmpty(message = "Account number cannot be null or empty.")
    private Long accountNumber;

    @NotEmpty(message = "Account type cannot be null or empty.")
    private String accountType;

    @NotEmpty(message = "Branch address cannot be null or empty.")
    private String branchAddress;
}

package com.slimdevs.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold Customer and Account's details information.")
public class CustomerDto {

    @Schema(description = "Customer's name", example = "Lewis Hamilton")
    @NotEmpty(message = "Name cannot be null or empty.")
    @Size(min = 5, max = 30, message = "The length of the customer's name must be between 5 and 30 characters.")
    private String name;
    
    @Schema(description = "Customer's email", example = "champ7on@gmail.com")
    @NotEmpty(message = "Email cannot be null or empty.")
    @Email(message = "Invalid email format.")
    private String email;

    @Schema(description = "Customer's mobile number", example = "4444444444")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must have 10 digits.")
    private String mobileNumber;

    @Schema(description = "Customer's account's details.")
    private AccountDto accountDto; 
}

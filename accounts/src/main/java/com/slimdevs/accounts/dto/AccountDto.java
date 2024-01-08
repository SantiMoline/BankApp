package com.slimdevs.accounts.dto;

import lombok.Data;

@Data //Contiene la anotaciones: getter, setter, requieredArgsConstructor, toString, equalsAndHashCode, Value.
public class AccountDto {
   
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}

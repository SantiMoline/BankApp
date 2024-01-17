package com.slimdevs.loans.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Loan extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int loadId;

    private String mobileNumber;

    private String loanNumber;

    private String loanType;

    private int totalLoan;

    private int amountPaid;

    private int outstandingAmount;

}

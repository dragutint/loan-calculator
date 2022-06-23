package com.dragutin.loancalculator.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCalculation extends ApiObject {
    private Double loanAmount;

    private Double interestRate;

    private Integer loanTerm;

    private Double monthlyPayment;

    private Double totalInterestPaid;
}

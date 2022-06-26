package com.dragutin.loancalculator.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCalculation extends ApiObject {
    private Double loanAmount;

    private Double interestRate;

    private Integer loanTerm;

    private Double monthlyPayment;

    private Double totalInterestPaid;

    private List<ApiMonthlyPayment> monthlyPayments;
}

package com.dragutin.loancalculator.api.domain;

import lombok.Data;

@Data
public class ApiLoanRequest extends ApiObject {
    private Double loanAmount;

    private Double interestRate;

    private Integer loanTermMonths;
}

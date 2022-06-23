package com.dragutin.loancalculator.bl.service;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;

@UtilityClass
public class LoanCalculatorUtility {

    public Double monthlyPayment(Double loanAmount, Double interestRate, Integer loanTermMonths) {
        double interestRatePerMonth = interestRate / 100 / 12;

        double parenthesisRepeatable = Math.pow(1 + interestRatePerMonth, loanTermMonths);

        double dividend = loanAmount * interestRatePerMonth * parenthesisRepeatable;
        double divisor = parenthesisRepeatable - 1;

        return  round(dividend / divisor);
    }

    public Double totalInterestPaid(Double loanAmount, Double monthlyPayment, Integer loanTermMonths) {
        return round(monthlyPayment * loanTermMonths - loanAmount);
    }

    private Double round(Double value) {
        return new BigDecimal(Double.toString(value)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}

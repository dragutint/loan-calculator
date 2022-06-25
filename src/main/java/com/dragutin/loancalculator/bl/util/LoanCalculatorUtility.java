package com.dragutin.loancalculator.bl.util;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.math.util.MathRoundingUtility;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class LoanCalculatorUtility {
    public Double fixedMonthlyPayment(Double loanAmount, Double interestRate, Integer loanTermMonths) {
        if(Objects.isNull(loanAmount) || Objects.isNull(interestRate) || Objects.isNull(loanTermMonths))
            throw new InvalidCalculationParameterException();

        double interestRatePerMonth = interestRate / 100 / 12;

        double parenthesisRepeatable = Math.pow(1 + interestRatePerMonth, loanTermMonths);

        double dividend = loanAmount * interestRatePerMonth * parenthesisRepeatable;
        double divisor = parenthesisRepeatable - 1;

        return  MathRoundingUtility.twoDecimals(dividend / divisor);
    }

    public Double totalInterestPaid(Double loanAmount, Double monthlyPayment, Integer loanTermMonths) {
        if(Objects.isNull(loanAmount) || Objects.isNull(monthlyPayment) || Objects.isNull(loanTermMonths))
            throw new InvalidCalculationParameterException();

        return MathRoundingUtility.twoDecimals(monthlyPayment * loanTermMonths - loanAmount);
    }
}

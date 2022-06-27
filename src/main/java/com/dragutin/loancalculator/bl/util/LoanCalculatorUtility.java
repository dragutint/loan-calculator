package com.dragutin.loancalculator.bl.util;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.math.util.MathRoundingUtility;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class LoanCalculatorUtility {
    public Double fixedPeriodPayment(Calculation calculation) {
        if(Objects.isNull(calculation.getLoanAmount()) || Objects.isNull(calculation.getInterestRate()) || Objects.isNull(calculation.getNumberOfPayments()))
            throw new InvalidCalculationParameterException();

        double interestRatePerPeriod = calculation.getInterestRate() / 100 / calculation.getPaymentFrequency().getPaymentsPerYear();

        double parenthesisRepeatable = Math.pow(1 + interestRatePerPeriod, calculation.getNumberOfPayments());

        double dividend = calculation.getLoanAmount() * interestRatePerPeriod * parenthesisRepeatable;
        double divisor = parenthesisRepeatable - 1;

        return  MathRoundingUtility.twoDecimals(dividend / divisor);
    }

    public Double totalInterestPaid(Calculation calculation) {
        if(Objects.isNull(calculation) || Objects.isNull(calculation.getLoanAmount()) || Objects.isNull(calculation.getFixedPeriodPayment()) || Objects.isNull(calculation.getNumberOfPayments()))
            throw new InvalidCalculationParameterException();

        return MathRoundingUtility.twoDecimals(calculation.getFixedPeriodPayment() * calculation.getNumberOfPayments() - calculation.getLoanAmount());
    }
}

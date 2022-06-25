package com.dragutin.loancalculator.bl.util;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.domain.MonthlyPayment;
import com.dragutin.loancalculator.math.util.MathRoundingUtility;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class MonthlyPaymentCalculatorUtility {

    public MonthlyPayment amortizationMonthlyPayment(Double fixedMonthlyPaymentAmount, Double interestRate, Double remainingBalance) {
        if(Objects.isNull(fixedMonthlyPaymentAmount) || Objects.isNull(interestRate) || Objects.isNull(remainingBalance))
            throw new InvalidCalculationParameterException();

        MonthlyPayment monthlyPayment = new MonthlyPayment();

        monthlyPayment.setInterestAmount(MathRoundingUtility.twoDecimals(interestRate / 100 / 12 * remainingBalance));

        if(fixedMonthlyPaymentAmount - remainingBalance > 0)
            monthlyPayment.setPrincipalAmount(remainingBalance);
        else
            monthlyPayment.setPrincipalAmount(MathRoundingUtility.twoDecimals(fixedMonthlyPaymentAmount - monthlyPayment.getInterestAmount()));

        monthlyPayment.setBalanceOwed(MathRoundingUtility.twoDecimals(remainingBalance - monthlyPayment.getPrincipalAmount()));

        return monthlyPayment;
    }
}

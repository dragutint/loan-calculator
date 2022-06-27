package com.dragutin.loancalculator.bl.util;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.domain.PeriodPayment;
import com.dragutin.loancalculator.math.util.MathRoundingUtility;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class PaymentPerPeriodCalculatorUtility {

    public PeriodPayment amortizationPeriodPayment(Calculation calculation, Double remainingBalance) {
        if(Objects.isNull(calculation) || Objects.isNull(calculation.getFixedPeriodPayment()) || Objects.isNull(calculation.getInterestRate()) || Objects.isNull(remainingBalance) || Objects.isNull(calculation.getPaymentFrequency()))
            throw new InvalidCalculationParameterException();

        PeriodPayment periodPayment = new PeriodPayment();

        periodPayment.setInterestAmount(MathRoundingUtility.twoDecimals(calculation.getInterestRate() / 100 / calculation.getPaymentFrequency().getPaymentsPerYear() * remainingBalance));

        if(calculation.getFixedPeriodPayment() - remainingBalance > 0)
            periodPayment.setPrincipalAmount(remainingBalance);
        else
            periodPayment.setPrincipalAmount(MathRoundingUtility.twoDecimals(calculation.getFixedPeriodPayment() - periodPayment.getInterestAmount()));

        periodPayment.setBalanceOwed(MathRoundingUtility.twoDecimals(remainingBalance - periodPayment.getPrincipalAmount()));

        return periodPayment;
    }
}

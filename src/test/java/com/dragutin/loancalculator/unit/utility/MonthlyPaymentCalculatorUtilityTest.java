package com.dragutin.loancalculator.unit.utility;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.bl.util.MonthlyPaymentCalculatorUtility;
import com.dragutin.loancalculator.domain.MonthlyPayment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MonthlyPaymentCalculatorUtilityTest {

    @Test
    public void amortizationMonthlyPayment_simpleCase() {
        MonthlyPayment monthlyPayment = MonthlyPaymentCalculatorUtility.amortizationMonthlyPayment(430.33 , 6.00, 5000.00);
        Assertions.assertEquals(405.33, monthlyPayment.getPrincipalAmount());
        Assertions.assertEquals(25.00, monthlyPayment.getInterestAmount());
        Assertions.assertEquals(4594.67, monthlyPayment.getBalanceOwed());
    }

    @Test
    public void amortizationMonthlyPayment_lastPaymentDifferent() {
        MonthlyPayment monthlyPayment = MonthlyPaymentCalculatorUtility.amortizationMonthlyPayment(430.33 , 6.00, 428.21);
        Assertions.assertEquals(428.21, monthlyPayment.getPrincipalAmount());
        Assertions.assertEquals(2.14, monthlyPayment.getInterestAmount());
        Assertions.assertEquals(0.00, monthlyPayment.getBalanceOwed());
    }

    @Test
    public void amortizationMonthlyPayment_nullParameters() {
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> MonthlyPaymentCalculatorUtility.amortizationMonthlyPayment(null , null, null));
    }
}

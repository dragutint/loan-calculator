package com.dragutin.loancalculator.unit.utility;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.bl.util.PaymentPerPeriodCalculatorUtility;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.domain.PeriodPayment;
import com.dragutin.loancalculator.domain.PaymentFrequencyEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentPerPeriodCalculatorUtilityTest {

    @Test
    public void amortizationPeriodPayment_simpleCase() {
        Calculation calculation = new Calculation();
        calculation.setFixedPeriodPayment(430.33);
        calculation.setInterestRate(6.00);
        calculation.setPaymentFrequency(PaymentFrequencyEnum.MONTHLY);

        PeriodPayment periodPayment = PaymentPerPeriodCalculatorUtility.amortizationPeriodPayment(calculation, 5000.00);
        assertEquals(405.33, periodPayment.getPrincipalAmount());
        assertEquals(25.00, periodPayment.getInterestAmount());
        assertEquals(4594.67, periodPayment.getBalanceOwed());
    }

    @Test
    public void amortizationPeriodPayment_lastPaymentDifferent() {
        Calculation calculation = new Calculation();
        calculation.setFixedPeriodPayment(430.33);
        calculation.setInterestRate(6.00);
        calculation.setPaymentFrequency(PaymentFrequencyEnum.MONTHLY);

        PeriodPayment periodPayment = PaymentPerPeriodCalculatorUtility.amortizationPeriodPayment(calculation, 428.21);
        assertEquals(428.21, periodPayment.getPrincipalAmount());
        assertEquals(2.14, periodPayment.getInterestAmount());
        assertEquals(0.00, periodPayment.getBalanceOwed());
    }

    @Test
    public void amortizationPeriodPayment_nullParameters() {
        assertThrows(InvalidCalculationParameterException.class, () -> PaymentPerPeriodCalculatorUtility.amortizationPeriodPayment(new Calculation(null , null, null, null), null));
    }
}

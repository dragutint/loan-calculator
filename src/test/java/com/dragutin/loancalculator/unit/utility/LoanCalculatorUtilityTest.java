package com.dragutin.loancalculator.unit.utility;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.bl.util.LoanCalculatorUtility;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.domain.PaymentFrequencyEnum;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoanCalculatorUtilityTest {

    @Test
    public void periodPayment (){
        Double periodPayment = LoanCalculatorUtility.fixedPeriodPayment(new Calculation(20000.00, 5.00, 60, PaymentFrequencyEnum.MONTHLY));
        assertEquals(377.42, periodPayment);
    }

    @Test
    public void periodPayment_nullParameters(){
        assertThrows(InvalidCalculationParameterException.class, () -> LoanCalculatorUtility.fixedPeriodPayment(new Calculation(null, null, null, null)));
    }

    @Test
    public void totalInterestPaid() {
        Calculation calculation = new Calculation();
        calculation.setLoanAmount(20000.00);
        calculation.setFixedPeriodPayment(377.42);
        calculation.setNumberOfPayments(60);

        Double totalInterestPaid = LoanCalculatorUtility.totalInterestPaid(calculation);
        assertEquals(2645.2, totalInterestPaid);
    }

    @Test
    public void totalInterestPaid_nullParameters(){
        assertThrows(InvalidCalculationParameterException.class, () -> LoanCalculatorUtility.totalInterestPaid(new Calculation(null, null, null, null)));
    }
}

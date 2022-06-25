package com.dragutin.loancalculator.unit.utility;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.bl.util.LoanCalculatorUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoanCalculatorUtilityTest {

    @Test
    public void monthlyPayment (){
        Double monthlyPayment = LoanCalculatorUtility.fixedMonthlyPayment(20000.00, 5.00, 60);
        Assertions.assertEquals(377.42, monthlyPayment);
    }

    @Test
    public void monthlyPayment_nullParameters(){
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> LoanCalculatorUtility.fixedMonthlyPayment(null, null, null));
    }

    @Test
    public void totalInterestPaid() {
        Double totalInterestPaid = LoanCalculatorUtility.totalInterestPaid(20000.00, 377.42, 60);
        Assertions.assertEquals(2645.2, totalInterestPaid);
    }

    @Test
    public void totalInterestPaid_nullParameters(){
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> LoanCalculatorUtility.totalInterestPaid(null, null, null));
    }
}

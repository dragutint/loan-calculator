package com.dragutin.loancalculator.unit.service;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.bl.repository.MonthlyPaymentRepository;
import com.dragutin.loancalculator.bl.service.CalculationServiceImpl;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.bl.messages.ErrorMessages;
import com.dragutin.loancalculator.bl.repository.CalculationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculationServiceTest {
    @Mock
    public CalculationRepository calculationRepository;
    @Mock
    public MonthlyPaymentRepository monthlyPaymentRepository;
    @InjectMocks
    public CalculationServiceImpl calculationService;

    @Test
    public void calculateSuccessful() {
        Calculation calculation = calculationService.calculate(20000.00, 5.00, 60);
        Assertions.assertEquals(377.42, calculation.getFixedMonthlyPayment());
        Assertions.assertEquals(2645.2, calculation.getTotalInterestPaid());
    }

    @Test
    public void calculateLoanAmountPrerequisitesFail() {
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(null, 5.00, 60), ErrorMessages.Loan.AMOUNT_NULL);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(0.00, 5.00, 60), ErrorMessages.Loan.AMOUNT_NOT_POSITIVE);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(-10.00, 5.00, 60), ErrorMessages.Loan.AMOUNT_NOT_POSITIVE);
    }

    @Test
    public void calculateLoanTermPrerequisitesFail() {
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(20000.00, 5.00, null), ErrorMessages.Loan.TERM_NULL);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(20000.00, 5.00, 0), ErrorMessages.Loan.TERM_NOT_POSITIVE);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(20000.00, 5.00, -1), ErrorMessages.Loan.TERM_NOT_POSITIVE);
    }

    @Test
    public void calculateInterestRatePrerequisitesFail() {
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(20000.00, null, 60), ErrorMessages.InterestRate.NULL);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(20000.00, 0.00, 60), ErrorMessages.InterestRate.NOT_POSITIVE);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(20000.00, -5.00, 60), ErrorMessages.InterestRate.NOT_POSITIVE);
    }

}

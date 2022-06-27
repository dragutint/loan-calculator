package com.dragutin.loancalculator.unit.service;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.bl.repository.PeriodPaymentRepository;
import com.dragutin.loancalculator.bl.service.CalculationServiceImpl;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.bl.messages.ErrorMessages;
import com.dragutin.loancalculator.bl.repository.CalculationRepository;
import com.dragutin.loancalculator.domain.PaymentFrequencyEnum;
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
    public PeriodPaymentRepository periodPaymentRepository;
    @InjectMocks
    public CalculationServiceImpl calculationService;

    @Test
    public void calculateSuccessful() {
        Calculation calculation = new Calculation(20000.00, 5.00, 60, PaymentFrequencyEnum.MONTHLY);
        calculation = calculationService.calculate(calculation);
        Assertions.assertEquals(377.42, calculation.getFixedPeriodPayment());
        Assertions.assertEquals(2645.2, calculation.getTotalInterestPaid());
    }

    @Test
    public void calculateLoanAmountPrerequisitesFail() {
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(null, 5.00, 60, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.Loan.AMOUNT_NULL);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(0.00, 5.00, 60, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.Loan.AMOUNT_NOT_POSITIVE);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(-10.00, 5.00, 60, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.Loan.AMOUNT_NOT_POSITIVE);
    }

    @Test
    public void calculateNumberOfPaymentsPrerequisitesFail() {
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(20000.00, 5.00, null, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.NumberOfPayments.NULL);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(20000.00, 5.00, 0, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.NumberOfPayments.NOT_POSITIVE);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(20000.00, 5.00, -1, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.NumberOfPayments.NOT_POSITIVE);
    }

    @Test
    public void calculateInterestRatePrerequisitesFail() {
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(20000.00, null, 60, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.InterestRate.NULL);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(20000.00, 0.00, 60, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.InterestRate.NOT_POSITIVE);
        Assertions.assertThrows(InvalidCalculationParameterException.class, () -> calculationService.calculate(new Calculation(20000.00, -5.00, 60, PaymentFrequencyEnum.MONTHLY)), ErrorMessages.InterestRate.NOT_POSITIVE);
    }

}

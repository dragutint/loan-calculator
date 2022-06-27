package com.dragutin.loancalculator.bl.service;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.bl.messages.ErrorMessages;
import com.dragutin.loancalculator.bl.repository.CalculationRepository;
import com.dragutin.loancalculator.bl.util.LoanCalculatorUtility;
import com.dragutin.loancalculator.bl.util.PaymentPerPeriodCalculatorUtility;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.domain.PeriodPayment;
import com.dragutin.loancalculator.domain.PeriodPaymentKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Log4j2
@Service
public class CalculationServiceImpl implements CalculationService {
    private final CalculationRepository calculationRepository;

    public CalculationServiceImpl(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    @Override
    public Calculation calculate(Calculation calculation) {
        if(Objects.isNull(calculation))
            throw new IllegalArgumentException();

        log.debug(
                "Got loan calculation request, amount: {}, interest rate: {}, number of payments: {}, payment frequency: {}",
                calculation.getLoanAmount(),
                calculation.getInterestRate(),
                calculation.getNumberOfPayments(),
                calculation.getPaymentFrequency()
        );

        prerequisites(calculation);

        calculation.setFixedPeriodPayment(LoanCalculatorUtility.fixedPeriodPayment(calculation));
        calculation.setTotalInterestPaid(LoanCalculatorUtility.totalInterestPaid(calculation));
        calculatePeriodPayments(calculation);

        calculationRepository.save(calculation);

        log.debug("Loan calculation processed: {}", calculation);

        return calculation;
    }

    private void calculatePeriodPayments(Calculation calculation) {
        Double previousBalanceOwed = calculation.getLoanAmount();
        for(int i = 0; i < calculation.getNumberOfPayments(); i++) {
            PeriodPayment mp = PaymentPerPeriodCalculatorUtility.amortizationPeriodPayment(calculation, previousBalanceOwed);

            PeriodPaymentKey key = new PeriodPaymentKey(calculation, i+1);
            mp.setId(key);

            calculation.addPeriodPayment(mp);
            previousBalanceOwed = mp.getBalanceOwed();
        }
    }

    private void prerequisites(Calculation calculation) {
        if(Objects.isNull(calculation))
            throw new InvalidCalculationParameterException();

        if(Objects.isNull(calculation.getLoanAmount()))
            throw new InvalidCalculationParameterException(ErrorMessages.Loan.AMOUNT_NULL);
        if(calculation.getLoanAmount() <= 0)
            throw new InvalidCalculationParameterException(ErrorMessages.Loan.AMOUNT_NOT_POSITIVE);

        if(Objects.isNull(calculation.getInterestRate()))
            throw new InvalidCalculationParameterException(ErrorMessages.InterestRate.NULL);
        if(calculation.getInterestRate() <= 0)
            throw new InvalidCalculationParameterException(ErrorMessages.InterestRate.NOT_POSITIVE);

        if(Objects.isNull(calculation.getNumberOfPayments()))
            throw new InvalidCalculationParameterException(ErrorMessages.NumberOfPayments.NULL);
        if(calculation.getNumberOfPayments() <= 0)
            throw new InvalidCalculationParameterException(ErrorMessages.NumberOfPayments.NOT_POSITIVE);

        if(Objects.isNull(calculation.getPaymentFrequency()))
            throw new InvalidCalculationParameterException(ErrorMessages.PaymentFrequency.NULL);
    }
}

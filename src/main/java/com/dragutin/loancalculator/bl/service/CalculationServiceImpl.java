package com.dragutin.loancalculator.bl.service;

import com.dragutin.loancalculator.bl.exception.InvalidCalculationParameterException;
import com.dragutin.loancalculator.bl.messages.ErrorMessages;
import com.dragutin.loancalculator.bl.repository.CalculationRepository;
import com.dragutin.loancalculator.bl.util.LoanCalculatorUtility;
import com.dragutin.loancalculator.bl.util.MonthlyPaymentCalculatorUtility;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.domain.MonthlyPayment;
import com.dragutin.loancalculator.domain.MonthlyPaymentKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CalculationServiceImpl implements CalculationService {
    private final CalculationRepository calculationRepository;

    public CalculationServiceImpl(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    @Override
    public Calculation calculate(Double loanAmount, Double interestRate, Integer loanTermMonths) {
        log.debug("Loan calculation request, amount: {}, interest rate: {}, loan term months: {}", loanAmount, interestRate, loanTermMonths);

        Calculation calculation = initializeCalculation(loanAmount, interestRate, loanTermMonths);
        prerequisites(calculation);

        calculation.setFixedMonthlyPayment(LoanCalculatorUtility.fixedMonthlyPayment(loanAmount, interestRate, loanTermMonths));
        calculation.setTotalInterestPaid(LoanCalculatorUtility.totalInterestPaid(loanAmount, calculation.getFixedMonthlyPayment(), loanTermMonths));

        log.debug("Loan calculation processed: {}", calculation);

        calculateMonthlyPayments(calculation);

        calculationRepository.save(calculation);

        return calculation;
    }

    private void calculateMonthlyPayments(Calculation calculation) {
        Double previousBalanceOwed = calculation.getLoanAmount();
        for(int i = 0; i < calculation.getLoanTerm(); i++) {
            MonthlyPayment mp = MonthlyPaymentCalculatorUtility.amortizationMonthlyPayment(
                    calculation.getFixedMonthlyPayment(),
                    calculation.getInterestRate(),
                    previousBalanceOwed);

            MonthlyPaymentKey key = new MonthlyPaymentKey(calculation, i+1);
            mp.setId(key);

            calculation.addMonthlyPayment(mp);
            previousBalanceOwed = mp.getBalanceOwed();
        }
    }

    private void prerequisites(Calculation calculation) {
        if(calculation.getLoanAmount() == null)
            throw new InvalidCalculationParameterException(ErrorMessages.Loan.AMOUNT_NULL);
        if(calculation.getLoanAmount() <= 0)
            throw new InvalidCalculationParameterException(ErrorMessages.Loan.AMOUNT_NOT_POSITIVE);

        if(calculation.getInterestRate() == null)
            throw new InvalidCalculationParameterException(ErrorMessages.InterestRate.NULL);
        if(calculation.getInterestRate() <= 0)
            throw new InvalidCalculationParameterException(ErrorMessages.InterestRate.NOT_POSITIVE);

        if(calculation.getLoanTerm() == null)
            throw new InvalidCalculationParameterException(ErrorMessages.Loan.TERM_NULL);
        if(calculation.getLoanTerm() <= 0)
            throw new InvalidCalculationParameterException(ErrorMessages.Loan.TERM_NOT_POSITIVE);
    }

    private Calculation initializeCalculation(Double loanAmount, Double interestRate, Integer loanTermMonths) {
        Calculation calculation = new Calculation();
        calculation.setLoanAmount(loanAmount);
        calculation.setInterestRate(interestRate);
        calculation.setLoanTerm(loanTermMonths);
        return calculation;
    }
}

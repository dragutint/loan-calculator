package com.dragutin.loancalculator.bl.service;

import com.dragutin.loancalculator.bl.messages.ErrorMessages;
import com.dragutin.loancalculator.bl.repository.CalculationRepository;
import com.dragutin.loancalculator.domain.Calculation;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CalculationService {
    private final CalculationRepository calculationRepository;

    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public Calculation calculate(Double loanAmount, Double interestRate, Integer loanTermMonths) {
        log.debug("Loan calculation request, amount: {}, interest rate: {}, loan term months: {}", loanAmount, interestRate, loanTermMonths);

        Calculation calculation = initializeCalculation(loanAmount, interestRate, loanTermMonths);
        prerequisites(calculation);

        calculation.setMonthlyPayment(LoanCalculatorUtility.monthlyPayment(loanAmount, interestRate, loanTermMonths));
        calculation.setTotalInterestPaid(LoanCalculatorUtility.totalInterestPaid(loanAmount, calculation.getMonthlyPayment(), loanTermMonths));

        log.debug("Loan calculation processed: {}", calculation);

        calculationRepository.save(calculation);

        return calculation;
    }

    private void prerequisites(Calculation calculation) {
        if(calculation.getLoanAmount() == null)
            throw new IllegalArgumentException(ErrorMessages.Loan.AMOUNT_NULL);
        if(calculation.getLoanAmount() <= 0)
            throw new IllegalArgumentException(ErrorMessages.Loan.AMOUNT_NOT_POSITIVE);

        if(calculation.getInterestRate() == null)
            throw new IllegalArgumentException(ErrorMessages.InterestRate.NULL);
        if(calculation.getInterestRate() <= 0)
            throw new IllegalArgumentException(ErrorMessages.InterestRate.NOT_POSITIVE);

        if(calculation.getLoanTerm() == null)
            throw new IllegalArgumentException(ErrorMessages.Loan.TERM_NULL);
        if(calculation.getLoanTerm() <= 0)
            throw new IllegalArgumentException(ErrorMessages.Loan.TERM_NOT_POSITIVE);
    }

    private Calculation initializeCalculation(Double loanAmount, Double interestRate, Integer loanTermMonths) {
        Calculation calculation = new Calculation();
        calculation.setLoanAmount(loanAmount);
        calculation.setInterestRate(interestRate);
        calculation.setLoanTerm(loanTermMonths);
        return calculation;
    }
}

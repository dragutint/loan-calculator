package com.dragutin.loancalculator.bl.service;

import com.dragutin.loancalculator.domain.Calculation;

public interface CalculationService {
    Calculation calculate(Double loanAmount, Double interestRate, Integer loanTermMonths);
}

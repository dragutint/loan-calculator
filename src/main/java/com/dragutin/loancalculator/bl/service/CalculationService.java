package com.dragutin.loancalculator.bl.service;

import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.domain.PaymentFrequencyEnum;

public interface CalculationService {
    Calculation calculate(Calculation requestedCalculation);
}

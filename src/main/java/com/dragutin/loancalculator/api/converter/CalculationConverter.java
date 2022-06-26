package com.dragutin.loancalculator.api.converter;

import com.dragutin.loancalculator.api.domain.ApiCalculation;
import com.dragutin.loancalculator.domain.Calculation;
import org.springframework.stereotype.Component;

@Component
public class CalculationConverter extends AbstractConverter<ApiCalculation, Calculation> {

    private final MonthlyPaymentConverter monthlyPaymentConverter;

    public CalculationConverter(MonthlyPaymentConverter monthlyPaymentConverter) {
        this.monthlyPaymentConverter = monthlyPaymentConverter;
    }

    @Override
    public ApiCalculation toDTO(Calculation domainObj) {
        return new ApiCalculation(
                domainObj.getLoanAmount(),
                domainObj.getInterestRate(),
                domainObj.getLoanTerm(),
                domainObj.getFixedMonthlyPayment(),
                domainObj.getTotalInterestPaid(),
                monthlyPaymentConverter.toDTO(domainObj.getMonthlyPayments())
        );
    }
}

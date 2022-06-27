package com.dragutin.loancalculator.api.converter;

import com.dragutin.loancalculator.api.domain.ApiCalculation;
import com.dragutin.loancalculator.domain.Calculation;
import org.springframework.stereotype.Component;

@Component
public class CalculationConverter extends AbstractConverter<ApiCalculation, Calculation> {

    private final PeriodPaymentConverter periodPaymentConverter;

    public CalculationConverter(PeriodPaymentConverter periodPaymentConverter) {
        this.periodPaymentConverter = periodPaymentConverter;
    }

    @Override
    public ApiCalculation toDTO(Calculation domainObj) {
        return new ApiCalculation(
                domainObj.getLoanAmount(),
                domainObj.getInterestRate(),
                domainObj.getNumberOfPayments(),
                domainObj.getPaymentFrequency(),
                domainObj.getFixedPeriodPayment(),
                domainObj.getTotalInterestPaid(),
                periodPaymentConverter.toDTO(domainObj.getPeriodPayments())
        );
    }
}

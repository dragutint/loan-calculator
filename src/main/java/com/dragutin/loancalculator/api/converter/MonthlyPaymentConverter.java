package com.dragutin.loancalculator.api.converter;

import com.dragutin.loancalculator.api.domain.ApiMonthlyPayment;
import com.dragutin.loancalculator.domain.MonthlyPayment;
import org.springframework.stereotype.Component;

@Component
public class MonthlyPaymentConverter extends AbstractConverter<ApiMonthlyPayment, MonthlyPayment> {

    @Override
    public ApiMonthlyPayment toDTO(MonthlyPayment domainObj) {
        return new ApiMonthlyPayment(
                domainObj.getId().getPaymentNumber(),
                domainObj.getPaymentAmount(),
                domainObj.getPrincipalAmount(),
                domainObj.getInterestAmount(),
                domainObj.getBalanceOwed()
        );
    }
}

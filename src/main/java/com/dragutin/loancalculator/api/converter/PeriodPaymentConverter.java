package com.dragutin.loancalculator.api.converter;

import com.dragutin.loancalculator.api.domain.ApiPeriodPayment;
import com.dragutin.loancalculator.domain.PeriodPayment;
import org.springframework.stereotype.Component;

@Component
public class PeriodPaymentConverter extends AbstractConverter<ApiPeriodPayment, PeriodPayment> {

    @Override
    public ApiPeriodPayment toDTO(PeriodPayment domainObj) {
        return new ApiPeriodPayment(
                domainObj.getId().getPaymentNumber(),
                domainObj.getPaymentAmount(),
                domainObj.getPrincipalAmount(),
                domainObj.getInterestAmount(),
                domainObj.getBalanceOwed()
        );
    }
}

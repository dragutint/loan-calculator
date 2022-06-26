package com.dragutin.loancalculator.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiMonthlyPayment extends ApiObject {
    private Integer paymentNumber;

    private Double paymentAmount;

    private Double principalAmount;

    private Double interestAmount;

    private Double balanceOwed;
}

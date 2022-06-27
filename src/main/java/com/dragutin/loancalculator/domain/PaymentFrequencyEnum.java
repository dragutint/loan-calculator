package com.dragutin.loancalculator.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentFrequencyEnum {
    DAILY(365),
    WEEKLY(52),
    BIWEEKLY(26),
    SEMI_MONTHLY(24),
    MONTHLY(12),
    BIMONTHLY(6),
    QUARTERLY(4),
    SEMI_ANNUAL(2),
    ANNUAL(1),
    ;

    private final int paymentsPerYear;

}

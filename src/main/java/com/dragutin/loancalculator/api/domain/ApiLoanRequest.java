package com.dragutin.loancalculator.api.domain;

import com.dragutin.loancalculator.bl.messages.ErrorMessages;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ApiLoanRequest extends ApiObject {

    @NotNull(message = ErrorMessages.Loan.AMOUNT_NULL)
    @Positive(message = ErrorMessages.Loan.AMOUNT_NOT_POSITIVE)
    private Double loanAmount;

    @NotNull(message = ErrorMessages.InterestRate.NULL)
    @Positive(message = ErrorMessages.InterestRate.NOT_POSITIVE)
    private Double interestRate;

    @NotNull(message = ErrorMessages.Loan.TERM_NULL)
    @Positive(message = ErrorMessages.Loan.TERM_NOT_POSITIVE)
    private Integer loanTermMonths;
}

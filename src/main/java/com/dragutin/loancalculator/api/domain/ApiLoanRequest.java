package com.dragutin.loancalculator.api.domain;

import com.dragutin.loancalculator.api.domain.validator.ValueOfEnum;
import com.dragutin.loancalculator.bl.messages.ErrorMessages;
import com.dragutin.loancalculator.domain.PaymentFrequencyEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@ApiModel(description = "Input parameters for loan request")
public class ApiLoanRequest extends ApiObject {

    @ApiModelProperty(notes = "Requested loan amount", example = "5000.00")
    @NotNull(message = ErrorMessages.Loan.AMOUNT_NULL)
    @Positive(message = ErrorMessages.Loan.AMOUNT_NOT_POSITIVE)
    private Double loanAmount;

    @ApiModelProperty(notes = "Loan interest rate", example = "6.00")
    @NotNull(message = ErrorMessages.InterestRate.NULL)
    @Positive(message = ErrorMessages.InterestRate.NOT_POSITIVE)
    private Double interestRate;

    @ApiModelProperty(notes = "Number of payments", example = "12.00")
    @NotNull(message = ErrorMessages.NumberOfPayments.NULL)
    @Positive(message = ErrorMessages.NumberOfPayments.NOT_POSITIVE)
    private Integer numberOfPayments;

    @ApiModelProperty(notes = "Payment frequency", example = "MONTHLY")
    @NotNull(message = ErrorMessages.PaymentFrequency.NULL)
    @ValueOfEnum(enumClass = PaymentFrequencyEnum.class, message = ErrorMessages.PaymentFrequency.INVALID)
    private String paymentFrequency;
}

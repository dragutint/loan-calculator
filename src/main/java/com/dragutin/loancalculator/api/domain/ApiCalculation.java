package com.dragutin.loancalculator.api.domain;

import com.dragutin.loancalculator.domain.PaymentFrequencyEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Calculation", description = "Calculation object with input parameters and calculation")
public class ApiCalculation extends ApiObject {
    @ApiModelProperty(notes = "Requested loan amount", example = "5000.00")
    private Double loanAmount;

    @ApiModelProperty(notes = "Loan interest rate", example = "6.00")
    private Double interestRate;

    @ApiModelProperty(notes = "Number of payments based on payment frequency", example = "12")
    private Integer numberOfPayments;

    @ApiModelProperty(notes = "Payment frequency", example = "MONTHLY")
    private PaymentFrequencyEnum paymentFrequency;

    @ApiModelProperty(notes = "The amount of loan period payment with principal and interest", example = "430.33")
    private Double periodPayment;

    @ApiModelProperty(notes = "Total interest paid for whole loan amount", example = "163.99")
    private Double totalInterestPaid;

    @ApiModelProperty(notes = "Loan amortization schedule")
    private List<ApiPeriodPayment> periodPayments;
}

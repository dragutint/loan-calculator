package com.dragutin.loancalculator.api.domain;

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

    @ApiModelProperty(notes = "Loan term in months", example = "12")
    private Integer loanTerm;

    @ApiModelProperty(notes = "The amount of loan monthly payment with principal and interest", example = "430.33")
    private Double monthlyPayment;

    @ApiModelProperty(notes = "Total interest paid for whole loan amount", example = "163.99")
    private Double totalInterestPaid;

    @ApiModelProperty(notes = "Loan amortization schedule")
    private List<ApiMonthlyPayment> monthlyPayments;
}

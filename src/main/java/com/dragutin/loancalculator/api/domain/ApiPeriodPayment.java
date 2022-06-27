package com.dragutin.loancalculator.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "One period payment in amortization schedule")
public class ApiPeriodPayment extends ApiObject {
    @ApiModelProperty(notes = "Payment number ordinal", example = "1")
    private Integer paymentNumber;

    @ApiModelProperty(notes = "Whole amount for this paying. Sum of principal and interest amount", example = "430.33")
    private Double paymentAmount;

    @ApiModelProperty(notes = "Principal amount for this paying, without interest", example = "405.33")
    private Double principalAmount;

    @ApiModelProperty(notes = "Interest amount for this paying, without principal", example = "25.00")
    private Double interestAmount;

    @ApiModelProperty(notes = "Remaining loan balance", example = "4594.67")
    private Double balanceOwed;
}

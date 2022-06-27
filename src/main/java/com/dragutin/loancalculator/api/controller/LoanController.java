package com.dragutin.loancalculator.api.controller;

import com.dragutin.loancalculator.api.domain.ApiCalculation;
import com.dragutin.loancalculator.api.domain.ApiLoanRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;

@Api
public interface LoanController {

    @ApiOperation(
            value = "Loan calculation",
            response = ApiCalculation.class,
            notes = "Method can be used for calculating loan payments for some frequency payment period with amortization schedule"
    )
    ResponseEntity<ApiCalculation> calculate(@ApiParam ApiLoanRequest loanRequest);
}

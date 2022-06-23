package com.dragutin.loancalculator.api.controller;

import com.dragutin.loancalculator.api.domain.ApiCalculation;
import com.dragutin.loancalculator.api.domain.ApiLoanRequest;
import org.springframework.http.ResponseEntity;

public interface LoanController {

    ResponseEntity<ApiCalculation> calculate(ApiLoanRequest loanRequest);
}

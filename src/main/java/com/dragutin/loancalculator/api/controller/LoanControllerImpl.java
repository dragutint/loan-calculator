package com.dragutin.loancalculator.api.controller;

import com.dragutin.loancalculator.api.converter.CalculationConverter;
import com.dragutin.loancalculator.api.domain.ApiCalculation;
import com.dragutin.loancalculator.api.domain.ApiLoanRequest;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.bl.service.CalculationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping( "/api/loan" )
public class LoanControllerImpl implements LoanController {

    private final CalculationService calculationService;
    private final CalculationConverter calculationConverter;

    public LoanControllerImpl(CalculationService calculationService, CalculationConverter calculationConverter) {
        this.calculationService = calculationService;
        this.calculationConverter = calculationConverter;
    }

    @Override
    @GetMapping("/calculate")
    public ResponseEntity<ApiCalculation> calculate(@RequestBody ApiLoanRequest loanRequest) {
        Calculation calculation = calculationService.calculate(loanRequest.getLoanAmount(), loanRequest.getInterestRate(), loanRequest.getLoanTermMonths());

        return ResponseEntity
                .ok()
                .body(calculationConverter.toDTO(calculation));
    }
}

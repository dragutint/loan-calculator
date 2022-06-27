package com.dragutin.loancalculator.api.controller;

import com.dragutin.loancalculator.api.converter.CalculationConverter;
import com.dragutin.loancalculator.api.domain.ApiCalculation;
import com.dragutin.loancalculator.api.domain.ApiLoanRequest;
import com.dragutin.loancalculator.domain.Calculation;
import com.dragutin.loancalculator.bl.service.CalculationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PostMapping("/calculate")
    public ResponseEntity<ApiCalculation> calculate(@Valid @RequestBody ApiLoanRequest loanRequest) {
        Calculation calculationRequest = new Calculation(loanRequest.getLoanAmount(), loanRequest.getInterestRate(), loanRequest.getNumberOfPayments(), loanRequest.getPaymentFrequency());
        Calculation calculationResponse = calculationService.calculate(calculationRequest);

        return ResponseEntity
                .ok()
                .body(calculationConverter.toDTO(calculationResponse));
    }
}

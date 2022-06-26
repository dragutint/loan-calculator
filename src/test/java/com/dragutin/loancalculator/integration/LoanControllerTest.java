package com.dragutin.loancalculator.integration;

import com.dragutin.loancalculator.LoanCalculatorApplication;
import com.dragutin.loancalculator.api.domain.ApiCalculation;
import com.dragutin.loancalculator.api.domain.ApiError;
import com.dragutin.loancalculator.api.domain.ApiLoanRequest;
import com.dragutin.loancalculator.api.domain.ApiMonthlyPayment;
import com.dragutin.loancalculator.bl.messages.ErrorMessages;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest(classes = LoanCalculatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoanControllerTest extends AbstractIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @Transactional
    public void test_simpleCase() {
        ResponseEntity<ApiCalculation> response = restTemplate.postForEntity(createURL("/api/loan/calculate"), prepareBody(5000.00, 6.00, 3), ApiCalculation.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        ApiCalculation calculation = response.getBody();

        assertNotNull(calculation);
        assertEquals(1683.36, calculation.getMonthlyPayment());
        assertEquals(50.08, calculation.getTotalInterestPaid());

        Assertions.assertThat(calculation.getMonthlyPayments())
                .hasSize(calculation.getLoanTerm())
                .containsExactly(
                        new ApiMonthlyPayment(1, 1683.36, 1658.36, 25.00, 3341.64),
                        new ApiMonthlyPayment(2, 1683.36, 1666.65, 16.71, 1674.99),
                        new ApiMonthlyPayment(3, 1683.36, 1674.99, 8.37, 0.00)
                );
    }

    @Test
    @Transactional
    public void test_allNullParameters() {
        ApiLoanRequest loanRequest = prepareBody(null, null, null);
        ResponseEntity<ApiError> response = restTemplate.postForEntity(createURL("/api/loan/calculate"), loanRequest, ApiError.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        ApiError error = response.getBody();

        assertNotNull(error);
        assertEquals(ApiError.ApiValidationError.NAME, error.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());

        Assertions.assertThat(error.getValidationErrors())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new ApiError.ApiValidationError("apiLoanRequest", "loanAmount", loanRequest.getLoanAmount(), ErrorMessages.Loan.AMOUNT_NULL),
                        new ApiError.ApiValidationError("apiLoanRequest", "interestRate", loanRequest.getInterestRate(), ErrorMessages.InterestRate.NULL),
                        new ApiError.ApiValidationError("apiLoanRequest", "loanTermMonths", loanRequest.getLoanTermMonths(), ErrorMessages.Loan.TERM_NULL)
                );
    }



    @Test
    @Transactional
    public void test_allNegativeParameters() {
        ApiLoanRequest loanRequest = prepareBody(-2.00, -2.00, -2);
        ResponseEntity<ApiError> response = restTemplate.postForEntity(createURL("/api/loan/calculate"), loanRequest, ApiError.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        ApiError error = response.getBody();

        assertNotNull(error);
        assertEquals(ApiError.ApiValidationError.NAME, error.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, error.getStatus());

        Assertions.assertThat(error.getValidationErrors())
                .hasSize(3)
                .containsExactlyInAnyOrder(
                        new ApiError.ApiValidationError("apiLoanRequest", "loanAmount", loanRequest.getLoanAmount(), ErrorMessages.Loan.AMOUNT_NOT_POSITIVE),
                        new ApiError.ApiValidationError("apiLoanRequest", "interestRate", loanRequest.getInterestRate(), ErrorMessages.InterestRate.NOT_POSITIVE),
                        new ApiError.ApiValidationError("apiLoanRequest", "loanTermMonths", loanRequest.getLoanTermMonths(), ErrorMessages.Loan.TERM_NOT_POSITIVE)
                );
    }

    private ApiLoanRequest prepareBody(Double loanAmount, Double interestRate, Integer loanTermMonths) {
        ApiLoanRequest request = new ApiLoanRequest();
        request.setLoanAmount(loanAmount);
        request.setInterestRate(interestRate);
        request.setLoanTermMonths(loanTermMonths);
        return request;
    }

    private String createURL(String route) {
        return "http://localhost:" + port + route;
    }
}

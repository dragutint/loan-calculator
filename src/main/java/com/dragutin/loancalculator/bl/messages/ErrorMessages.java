package com.dragutin.loancalculator.bl.messages;

public class ErrorMessages {

    public static class Loan {
        public static final String AMOUNT_NULL = "Loan amount is not provided";
        public static final String AMOUNT_NOT_POSITIVE = "Loan amount must be greater than 0";

        public static final String TERM_NULL = "Loan term is not provided";
        public static final String TERM_NOT_POSITIVE = "Loan term must be greater than 0";
    }

    public static class InterestRate {
        public static final String NULL = "Interest rate is not provided";
        public static final String NOT_POSITIVE = "Interest rate must be greater than 0";
    }
}

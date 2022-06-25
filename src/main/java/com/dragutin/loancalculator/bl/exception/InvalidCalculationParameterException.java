package com.dragutin.loancalculator.bl.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidCalculationParameterException extends IllegalArgumentException{

    public InvalidCalculationParameterException(String message){
        super(message);
    }
}

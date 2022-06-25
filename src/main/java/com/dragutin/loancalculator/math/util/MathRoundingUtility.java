package com.dragutin.loancalculator.math.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@UtilityClass
public class MathRoundingUtility {

    public Double twoDecimals(Double value) {
        if(Objects.isNull(value))
            throw new IllegalArgumentException();

        return new BigDecimal(Double.toString(value)).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}

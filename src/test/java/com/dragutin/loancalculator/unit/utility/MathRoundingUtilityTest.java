package com.dragutin.loancalculator.unit.utility;

import com.dragutin.loancalculator.math.util.MathRoundingUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MathRoundingUtilityTest {

    @Test
    public void twoDecimals_downDecimal () {
        Double result = MathRoundingUtility.twoDecimals(225.12359);
        Assertions.assertEquals(225.12, result);
    }

    @Test
    public void twoDecimals_evenDecimal () {
        Double result = MathRoundingUtility.twoDecimals(225.12559);
        Assertions.assertEquals(225.13, result);
    }

    @Test
    public void twoDecimals_upDecimal () {
        Double result = MathRoundingUtility.twoDecimals(225.12623);
        Assertions.assertEquals(225.13, result);
    }

    @Test
    public void twoDecimals_nullParameter () {
        Assertions.assertThrows(IllegalArgumentException.class, () -> MathRoundingUtility.twoDecimals(null));
    }
}

package com.badoo.testapp.helper;

import java.math.BigDecimal;

/**
 * A convenience class for formatting numbers for user presentation
 */
public class NumberFormater {
    static final int DECIMAL_PLACES = 2; // Number of decimal places we wish to show

    /**
     * Rounds a number to 2 decimal points
     *
     * @param d Raw number
     * @return rounded readable number
     */
    public static BigDecimal round(float d) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(DECIMAL_PLACES, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
}

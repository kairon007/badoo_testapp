package com.badoo.testapp.test;

import android.test.AndroidTestCase;

import com.badoo.testapp.helper.NumberFormater;


public class NumberFormaterTest extends AndroidTestCase {

    public void testZeroAtEnd() throws Throwable{
        assertEquals("2.30", String.valueOf(NumberFormater.round(2.3f)));
    }

    public void testMultiZeroAtEnd() throws Throwable{
        assertEquals("2.00", String.valueOf(NumberFormater.round(2f)));
    }

    public void testRoundDown() throws Throwable{
        assertEquals("2.33", String.valueOf(NumberFormater.round(2.334f)));
    }

    public void testRoundUp() throws Throwable{
        assertEquals("2.34", String.valueOf(NumberFormater.round(2.336f)));
    }

    public void testMidRoundUp() throws Throwable{
        assertEquals("2.34", String.valueOf(NumberFormater.round(2.335f)));
    }

}

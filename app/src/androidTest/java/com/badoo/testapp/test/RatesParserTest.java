package com.badoo.testapp.test;

import android.test.AndroidTestCase;

import com.badoo.testapp.helper.RatesParser;
import com.badoo.testapp.model.Rate;

import java.util.ArrayList;
import java.util.List;




public class RatesParserTest extends AndroidTestCase {

    public void testEmptyRates() throws Throwable{
        RatesParser rp = new RatesParser(null);
        assertEquals(null, rp.doConversion());
    }

    public void testSingleRate() throws Throwable {
        List<Rate> rates = new ArrayList<Rate>();
        rates.add(new Rate("USD", "GBP", 10));

        RatesParser rp = new RatesParser(rates);
        assertEquals(Float.valueOf(10), rp.doConversion().get("USD"));
    }

    public void testTwoCascadedRates() throws Throwable {
        List<Rate> rates = new ArrayList<Rate>();
        rates.add(new Rate("CND", "USD", 5));
        rates.add(new Rate("USD", "GBP", 10));

        RatesParser rp = new RatesParser(rates);
        assertEquals(Float.valueOf(50), rp.doConversion().get("CND"));
    }

    public void testThreeCascadedRates() throws Throwable {
        List<Rate> rates = new ArrayList<Rate>();
        rates.add(new Rate("ZND", "CND", 0.5f));
        rates.add(new Rate("CND", "USD", 5));
        rates.add(new Rate("USD", "GBP", 10));

        RatesParser rp = new RatesParser(rates);
        assertEquals(Float.valueOf(25), rp.doConversion().get("ZND"));
    }

    /**
     * This is because of the assumption that given a forward conversion, it's backward conversion
     * is not assumed! So, to get a possible conversion, direction is also important!
     * @throws Throwable
     */
    public void testReverseCascadedRates() throws Throwable {
        List<Rate> rates = new ArrayList<Rate>();
        rates.add(new Rate("USD", "CND", 5));
        rates.add(new Rate("USD", "GBP", 10));

        RatesParser rp = new RatesParser(rates);
        assertNull(rp.doConversion().get("CND"));
    }


}

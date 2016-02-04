package com.razorthink.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by shams on 2/3/16.
 */
@RunWith(PowerMockRunner.class)
public class TestNeumericalValues {

    @Test
    public void assertIntegers() {
        Integer int1 = 100;
        assertEquals(Integer.valueOf(100), int1);

        int pint = 200;
        assertEquals(pint, 200);

        Double d = 200.0;
        assertEquals(d, Double.valueOf(200.0));
    }

    @Test
    public void assertDoubles() {

        Double d = 200.0;
        assertEquals(d, Double.valueOf(200.0));

        double db = 200;
        assertEquals(db, 200, 0.0);
    }

    @Test
    public void assertFloat() {

        Float d = 200.4f;
        assertEquals(d, Float.valueOf(200.4f));

        float ff = 200.04f;
        assertEquals(ff, 200.04f, 0.0);
    }
}

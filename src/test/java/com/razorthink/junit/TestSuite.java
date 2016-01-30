package com.razorthink.junit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(value = JUnit4.class)
public class TestSuite {

    @Test
    public void testAssert(){
        Assert.assertTrue(true);
    }
}


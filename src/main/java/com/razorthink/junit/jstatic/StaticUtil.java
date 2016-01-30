package com.razorthink.junit.jstatic;


import com.razorthink.junit.beans.TestPojo;

/**
 * Created by shams on 1/29/16.
 */
public class StaticUtil {

    public static String firstStaticMethod(String name) {
        return name;
    }

    public static long firstStaticMethod(long value) {
        return value;
    }

    public static String secondStaticMethod(String name) {
        return name;
    }

    public static Long thirdStaticMethod(String name) {
        return IdGenerator.generateNewId();
    }

    public static String urlParser(String name) throws IllegalStateException {

        if (null == name) {
            throw new IllegalStateException("null value passed");
        }
        return "https://" + name;
    }

    public static String returnsNull(String name) {
        return null;
    }

    public static String testPrivate(String name) {
        return privateMethod(name);
    }

    private static String privateMethod(String name) {
        return name;
    }

    public static TestPojo testArgumentAndReturnObject(TestPojo test) {
        return test;
    }

    public static TestPojo testPrivate(TestPojo test) {
        return privateMethod(test);
    }

    private static TestPojo privateMethod(TestPojo test) {
        return new TestPojo(test.getMessage());
    }
}

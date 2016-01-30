package com.razorthink.junit.jstatic;

/**
 * Created by shams on 1/29/16.
 */
public class IdGenerator {
    public static long generateNewId() {
        return System.currentTimeMillis();
    }
}

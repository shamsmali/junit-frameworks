package com.razorthink.junit.thread;

/**
 * Created by shams on 2/19/16.
 */
public class SomeImpl implements SimpleInterface {

    public String someImpl(String message) {
        try {

            Thread.sleep(10000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return message;
    }
}

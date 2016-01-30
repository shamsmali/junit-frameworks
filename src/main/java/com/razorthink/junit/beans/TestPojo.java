package com.razorthink.junit.beans;

/**
 * Created by shams on 1/29/16.
 */
public class TestPojo {

    private String message;

    public TestPojo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestPojo testPojo = (TestPojo) o;

        return message != null ? message.equals(testPojo.message) : testPojo.message == null;

    }

    @Override
    public int hashCode() {
        return message != null ? message.hashCode() : 0;
    }
}

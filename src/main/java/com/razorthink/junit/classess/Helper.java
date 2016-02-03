package com.razorthink.junit.classess;

/**
 * Created by shams on 2/2/16.
 */
public class Helper {
    private String message;

    public Helper() {

    }

    public String doSomething() {
        return message;
    }

    public Helper(String message) {
        this.message = message;
    }

    private String privateMethod(){
        return "test private";
    }

    public String callToPrivate(){
        return privateMethod();
    }

    public String getMessage() {
        return message;
    }

    public String getMessage(String msg) {
        return msg;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

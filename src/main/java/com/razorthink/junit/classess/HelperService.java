package com.razorthink.junit.classess;

import com.razorthink.junit.beans.H2DBManager;
import com.razorthink.junit.beans.SimpleDAO;
import com.razorthink.junit.beans.TestPojo;
import com.razorthink.junit.jstatic.StaticServiceRegister;
import com.razorthink.junit.jstatic.StaticUtil;

import java.io.IOException;
import java.util.List;

/**
 * Created by shams on 2/2/16.
 */
public class HelperService {

    private Helper helper = new Helper();
    private SimpleDAO simpleDAO;

    public HelperService() {
        this.simpleDAO = new SimpleDAO();
    }

    public String extractDetails() {
        Helper helper = new Helper("asd");
        return helper.doSomething();
    }

    public String callsStaticAndInstanceBasedClassess() {
        Helper helper = new Helper("asd");
        String helperResult = helper.doSomething();
        long ssResult = new StaticServiceRegister().registerService("hello");
        return helperResult + ssResult;
    }

    public TestPojo callsStaticAndInstanceBasedClassessWithReturnType() {
        Helper helper = new Helper("asd");
        String helperResult = helper.doSomething();
        TestPojo ssResult = new StaticServiceRegister().registerService(new TestPojo("hello"));
        someMethod(ssResult);
        return ssResult;
    }

    public String someMethod(TestPojo testPojo) {
        return "someMethod";
    }

    public String extractDetailsGlobal() {

        return helper.getMessage("hello");
    }

    public String parseArray(List<String> data) {
        return data.get(1);
    }

    public String parseArrayWithException(List<String> data) {
        if (data.size() <= 0) {
            throw new IllegalStateException("Array is empty");
        }
        return data.get(1);
    }

    public void throwsException() throws IOException {
        try {
            throw new IOException();
        } catch (IOException e) {
            //e.printStackTrace(System.err);
            throw e;
        }
    }

    public boolean saveMessage(final String message) {
        return simpleDAO.saveMessage("hello shams");
    }

    public void retriveMessages() {

    }

}

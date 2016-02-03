package com.razorthink.junit.jstatic;

import com.razorthink.junit.beans.TestPojo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shams on 1/29/16.
 */
public class StaticServiceRegister {

    private Map<Long, Object> serviceRegistrations = new HashMap<Long, Object>();

    public long registerService(String service) {
        final long id = IdGenerator.generateNewId();
        serviceRegistrations.put(id, service);
        return id;
    }

    public TestPojo registerService(TestPojo testPojo) {
        final long id = IdGenerator.generateNewId();
        serviceRegistrations.put(id, testPojo);
        return testPojo;
    }


}

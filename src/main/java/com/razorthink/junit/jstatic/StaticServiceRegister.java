package com.razorthink.junit.jstatic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shams on 1/29/16.
 */
public class StaticServiceRegister {

    private Map<Long, Object> serviceRegistrations = new HashMap<Long, Object>();

    public long registerService(Object service) {
        final long id = IdGenerator.generateNewId();
        serviceRegistrations.put(id, service);
        return id;
    }


}

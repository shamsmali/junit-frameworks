package com.razorthink.junit.classess;

/**
 * Created by shams on 2/3/16.
 */
public abstract class AbstractMethodMocking {

    public String getValue() {
        return getIt();
    }

    protected abstract String getIt();

}
package com.razorthink.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(value = Suite.class)
@SuiteClasses({TestStatic.class, TestClasses.class, TestNeumericalValues.class, TestSimpleDAO.class})
public class TestSuite {


}


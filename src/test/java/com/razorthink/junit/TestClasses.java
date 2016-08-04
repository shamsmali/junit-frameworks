package com.razorthink.junit;

import com.razorthink.junit.beans.TestPojo;
import com.razorthink.junit.classess.AbstractMethodMocking;
import com.razorthink.junit.classess.Helper;
import com.razorthink.junit.classess.HelperService;
import com.razorthink.junit.jstatic.StaticServiceRegister;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by shams on 2/2/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Helper.class, HelperService.class, HelperService.class})
public class TestClasses {


    /**
     * Test to mock constructor
     */
    @Test
    public void testMockConstructor() throws Exception {
        HelperService helperService = new HelperService();

        Helper helper = new Helper("shams");
        whenNew(Helper.class).withArguments("asd").thenReturn(helper);
        String result = helperService.extractDetails();
        assertEquals(result, helper.getMessage());
    }

    /**
     * Test to private global class instance
     */
    @Test
    public void testMockGlobalVariableConstructor() throws Exception {
        HelperService helperService = new HelperService();
        Helper helper = mock(Helper.class);

        Whitebox.setInternalState(helperService, "helper", helper, HelperService.class);

        when(helper.getMessage("hello")).thenReturn("shams");

        String result = helperService.extractDetailsGlobal();
        assertEquals("shams", result);
    }

    /**
     * Test to mock class instance and method
     */
    @Test
    public void testMockInstanceAndMethod() throws Exception {

        Helper helper = spy(new Helper("shams"));
        // in case you don't want the method to be called
        doReturn("hello").when(helper).getMessage();
        String actual = helper.getMessage();
        assertEquals(actual, "hello");
    }

    /**
     * Test to mock class instance and method
     */
    @Test
    public void testMockInstanceAndPrivateMethod() throws Exception {

        Helper helper = spy(new Helper("shams"));
        when(helper, "privateMethod").thenReturn("hello");
        String actual = helper.callToPrivate();
        assertEquals(actual, "hello");
    }

    /**
     * Test to mock class instance with multiple class calls
     */
    @Test
    public void testMockInstanceAndMultipleCallsWithinMethod() throws Exception {

        HelperService helperService = new HelperService();
        Helper helper = mock(Helper.class);
        StaticServiceRegister staticServiceRegister = mock(StaticServiceRegister.class);

        whenNew(Helper.class).withArguments("asd").thenReturn(helper);
        whenNew(StaticServiceRegister.class).withNoArguments().thenReturn(staticServiceRegister);

        when(helper.doSomething()).thenReturn("doSomethingCalled");
        when(staticServiceRegister.registerService("hello")).thenReturn(10l);

        String result = helperService.callsStaticAndInstanceBasedClassess();

        assertEquals("doSomethingCalled10", result);
    }

    /**
     * Test to mock class instance and method return type with multiple class calls
     */
    @Test
    public void testMockInstanceAndMultipleCallsAndReturnTypeWithinMethod() throws Exception {

        HelperService helperService = new HelperService();
        Helper helper = mock(Helper.class);
        StaticServiceRegister staticServiceRegister = mock(StaticServiceRegister.class);

        whenNew(Helper.class).withArguments("asd").thenReturn(helper);
        whenNew(StaticServiceRegister.class).withNoArguments().thenReturn(staticServiceRegister);

        when(helper.doSomething()).thenReturn("doSomethingCalled");
        when(staticServiceRegister.registerService(new TestPojo("hello"))).thenReturn(new TestPojo("good mocking"));

        TestPojo result = helperService.callsStaticAndInstanceBasedClassessWithReturnType();

        assertEquals(result, new TestPojo("good mocking"));
    }


    @Test
    public void mocksAbstractClasses() throws Exception {
        assertNotNull(mock(AbstractMethodMocking.class));
    }

    @Test
    public void canSpyOnAnonymousClasses() throws Exception {
        AbstractMethodMocking tested = new AbstractMethodMocking() {
            @Override
            protected String getIt() {
                return null;
            }
        };

        Assert.assertNull(tested.getValue());
        AbstractMethodMocking spy = spy(tested);
        when(spy.getValue()).thenReturn("something");

        assertEquals("something", spy.getValue());
    }

}

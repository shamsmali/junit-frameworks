package com.razorthink.junit;

import com.razorthink.junit.beans.TestPojo;
import com.razorthink.junit.jstatic.IdGenerator;
import com.razorthink.junit.jstatic.StaticServiceRegister;
import com.razorthink.junit.jstatic.StaticUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.powermock.api.mockito.PowerMockito.*;


/**
 * Created by shams on 1/29/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({IdGenerator.class, StaticUtil.class})
public class TestStatic {

    private long expectedId = 42;

    /*
        Example of mocking static methods. StaticServiceRegister.registerService internally
        calls generateNewId() and we are mocking generateNewId() to always return 42
     */
    @Test
    public void testStaticRegisterService() throws Exception {


        // This is the way to tell PowerMock to mock all static methods of a
        // given class
        mockStatic(IdGenerator.class);

        expectedId = 42;

        // We create a new instance of test class under test as usually.
        StaticServiceRegister tested = new StaticServiceRegister();


        when(IdGenerator.generateNewId()).thenReturn(expectedId);

        long actualId = tested.registerService(new Object());

        // Assert that the ID is correct
        assertEquals(expectedId, actualId);

    }


    /*  Example of mocking static methods. StaticUtil.thirdStaticMethod
        calls IdGenerator.generateNewId and we are mocking
    */
    @Test
    public void testMethodThatCallsStaticMethod() {
        // mock all the static methods in a class called "Static"

        mockStatic(StaticUtil.class);


        // use Mockito to set up your expectation
        when(StaticUtil.firstStaticMethod("a")).thenReturn("b");
        when(StaticUtil.secondStaticMethod("shams")).thenReturn("mali");
        when(StaticUtil.thirdStaticMethod("shams")).thenReturn(expectedId);


        String actual = StaticUtil.firstStaticMethod("a");
        assertEquals(actual, "b");

        PowerMockito.verifyStatic(Mockito.times(1));
        StaticUtil.firstStaticMethod("a");

        long actualId = StaticUtil.thirdStaticMethod("shams");
        assertEquals(expectedId, actualId);

    }

    /*
        Testing static method throwing exception and catch it
     */
    @Test(expected = IllegalStateException.class)
    public void testMockStaticWithoutThatThrowsException() throws Exception {
        StaticUtil.urlParser(null);
    }

    /*
        Testing static method by mocking throw exception and catch it
     */
    @Test(expected = IllegalStateException.class)
    public void testMockStaticThatThrowsException() throws Exception {
        mockStatic(StaticUtil.class);
        when(StaticUtil.urlParser("shams.dodl")).thenThrow(new IllegalStateException());
        StaticUtil.urlParser("shams.dodl");
    }

    /*
        Testing static method by counting number times it is called
     */
    @Test
    public void testMockStaticCorrectTimes() throws Exception {
        mockStatic(StaticUtil.class);
        assertNull(StaticUtil.returnsNull("hello"));

        // remove one static call here and test case will fail
        assertNull(StaticUtil.returnsNull("hello"));

        // Verification is done in two steps using static methods.
        verifyStatic(Mockito.times(2));
        StaticUtil.returnsNull("hello");
    }

    /*
        Testing static method by mocking return type. Here we have used custom object type
     */
    @Test
    public void testMockStaticArgument() throws Exception {
        mockStatic(StaticUtil.class);
        TestPojo testPojo = new TestPojo("shams sdhf ksdjfh kjhsdf mali");

        when(StaticUtil.testArgumentAndReturnObject(testPojo)).thenReturn(new TestPojo("shams mali"));
        TestPojo result = StaticUtil.testArgumentAndReturnObject(testPojo);

        assertEquals(new TestPojo("shams mali"), result);
    }

    /*
        Testing static method by mocking argument type.
     */
    @Test
    public void spyingUsingArgumentCaptor() throws Exception {
        // Given
        mockStatic(StaticUtil.class);
        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        // uncomment to test negative scenario
        // StaticUtil.firstStaticMethod("sdkfhdsjkfh");
        StaticUtil.firstStaticMethod("something");

        verifyStatic();
        StaticUtil.firstStaticMethod(captor.capture());

        assertEquals("something", captor.getValue());

        final ArgumentCaptor<Long> captorLong = ArgumentCaptor.forClass(Long.class);

        StaticUtil.firstStaticMethod(20);

        verifyStatic();
        StaticUtil.firstStaticMethod(captorLong.capture());

        assertEquals(Long.valueOf(20l), captorLong.getValue());
    }

    /*
        Testing private static method.
     */
    @Test
    public void spyPrivateStaticMethod() throws Exception {
        // Given
        PowerMockito.spy(StaticUtil.class);
        PowerMockito.doReturn("shams").when(StaticUtil.class, "privateMethod", Matchers.anyString());

        String result = StaticUtil.testPrivate("shams mali");
        assertEquals("shams", result);
    }

    /*
        Testing private static method with custom argument and return type
      */
    @Test
    public void spyPrivateStaticWithCustomObjectsMethod() throws Exception {
        // Given
        PowerMockito.spy(StaticUtil.class);

        TestPojo t = new TestPojo("shams mali");

        Method m = Whitebox.getMethod(StaticUtil.class, "privateMethod", TestPojo.class);
        PowerMockito.doReturn(t).when(StaticUtil.class, m).withArguments(Matchers.any(TestPojo.class));

        TestPojo result = StaticUtil.testPrivate(new TestPojo("sdfh ksdhkl"));
        assertEquals(new TestPojo("shams mali"), result);
    }

}

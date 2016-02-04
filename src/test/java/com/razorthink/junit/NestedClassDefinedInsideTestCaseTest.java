package com.razorthink.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Created by shams on 2/3/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( { NestedClassDefinedInsideTestCaseTest.class })
public class NestedClassDefinedInsideTestCaseTest {

    @Test
    public void mocksNestedPrivateClassDefinedInsideTestCase() throws Exception {
        NestedPrivateClassDefinedInsideTestCase tested = mock(NestedPrivateClassDefinedInsideTestCase.class);
        when(tested.getValue()).thenReturn("something");

        assertEquals("something", tested.getValue());
    }

    @Test
    public void mocksNestedPrivateFinalClassDefinedInsideTestCase() throws Exception {
        NestedPrivateFinalClassDefinedInsideTestCase tested = mock(NestedPrivateFinalClassDefinedInsideTestCase.class);
        when(tested.getValue()).thenReturn("something");

        assertEquals("something", tested.getValue());
    }

    private class NestedPrivateClassDefinedInsideTestCase {
        public String getValue() {
            return "value";
        }
    }

    private final class NestedPrivateFinalClassDefinedInsideTestCase {
        public String getValue() {
            return "value";
        }
    }
}

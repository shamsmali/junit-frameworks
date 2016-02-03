package com.razorthink.junit;

import com.razorthink.junit.classess.HelperService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * Created by shams on 2/3/16.
 */
@RunWith(PowerMockRunner.class)
public class TestExceptionHandling {

    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testUncheckedException(){
        thrown.expect(IndexOutOfBoundsException.class);
        HelperService helperService = new HelperService();
        final String result = helperService.parseArray(new ArrayList<String>());
        assertNull(result);
    }

    @Test
    public void testCheckedException(){
        thrown.expect(IllegalStateException.class);
        HelperService helperService = new HelperService();
        final String result = helperService.parseArrayWithException(new ArrayList<String>());
        assertNull(result);
    }
}

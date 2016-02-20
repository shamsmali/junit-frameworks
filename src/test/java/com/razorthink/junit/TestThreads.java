package com.razorthink.junit;

import com.razorthink.junit.thread.SimpleInterface;
import com.razorthink.junit.thread.SomeImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by shams on 2/19/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SomeImpl.class})
public class TestThreads {


    @Test
    public void testThreadSeep () throws InterruptedException {
        SimpleInterface simpleInterface = new SomeImpl();
        Instant start = Instant.now();

        PowerMockito.mockStatic(Thread.class);

        Thread.sleep(Mockito.anyLong());

        String res = simpleInterface.someImpl("google");

        Assert.assertEquals("google", res);

        Instant einde = Instant.now();

        long seconds = Duration.between(start, einde).getSeconds();

        Assert.assertTrue(seconds < 10);

    }
}

package com.razorthink.junit;

/**
 * Created by shams on 2/3/16.
 */

import com.razorthink.junit.beans.BaseDAO;
import com.razorthink.junit.beans.SimpleDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;

/**
 * Created by shams on 2/2/16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({SimpleDAO.class, BaseDAO.class })
public class TestSimpleDAO {

    @Test
    public void testCreateTable(){
        SimpleDAO simpleDAO = new SimpleDAO();
        simpleDAO.createTable();
        simpleDAO.saveMessage("hello dude shams mali shams mali");
        HashSet<String> messages =  simpleDAO.getDataFromData();
        Assert.assertNotNull(messages);
    }

    @Test
    public void testMockCreateTable(){

        SimpleDAO simpleDAO = PowerMockito.mock(SimpleDAO.class);
        PowerMockito.when(simpleDAO.createTable()).thenReturn(true);
        boolean result = simpleDAO.createTable();
        Assert.assertTrue(result);
    }

    @Test
    public void testMockBaseCreateTable(){
        SimpleDAO simpleDAO = new SimpleDAO();
        BaseDAO baseDAO = PowerMockito.mock(BaseDAO.class);
        PowerMockito.when(baseDAO.executeQuery(Matchers.anyString())).thenReturn(true);
        boolean result = simpleDAO.createTable();
        Assert.assertTrue(result);
    }
}

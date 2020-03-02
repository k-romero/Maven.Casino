package io.zipcoder.casino.utilitiestest;

import io.zipcoder.casino.utilities.Menu;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MenuTest {
    @Test
    public void menuGetNameTest(){
        Menu item1 = Menu.PLAYERINFO;
        String actual = item1.getName();
        String expected = "Player Information";

        Assert.assertEquals(actual,expected);
    }

    @Test
    public void getShortKeyTest(){
        Menu item1 = Menu.PLAYERINFO;
        List<String> actual = item1.getShortKeys();
        Assert.assertTrue(actual.contains("p"));
        Assert.assertTrue(actual.contains("player"));

    }
}

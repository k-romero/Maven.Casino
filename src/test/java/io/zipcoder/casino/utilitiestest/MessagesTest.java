package io.zipcoder.casino.utilitiestest;

import io.zipcoder.casino.utilities.Messages;
import org.junit.Assert;
import org.junit.Test;

public class MessagesTest {

    //Making sure file and package structure works correctly
    @Test
    public void testTest(){
        Messages newclass = new Messages();

        int actual = newclass.test();
        Assert.assertEquals(1,actual);
    }
}

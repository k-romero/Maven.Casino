package io.zipcoder.casino.dealertest;

import io.zipcoder.casino.dealer.DiceDealer;
import org.junit.Assert;
import org.junit.Test;

public class DealerTest {
    @Test
    public void  getName(){
        DiceDealer diceDealer = new DiceDealer();
        diceDealer.setName("kasper");
        String actual = diceDealer.getName();
        Assert.assertEquals("kasper",actual);

    }

    @Test
    public void setName(){
        DiceDealer diceDealer = new DiceDealer();
        diceDealer.setName("kasper");

        Assert.assertEquals("kasper",diceDealer.getName() );
    }
    @Test
    public void isAngry(){
        DiceDealer diceDealer = new DiceDealer();
        diceDealer.setAngry(Boolean.TRUE);
        Assert.assertTrue(diceDealer.getAngry());
    }

}

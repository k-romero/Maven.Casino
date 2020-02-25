package io.zipcoder.casino.dealertest;

import io.zipcoder.casino.dealer.CardDealer;
import io.zipcoder.casino.dealer.Dealer;
import io.zipcoder.casino.dealer.DiceDealer;
import org.junit.Assert;
import org.junit.Test;

public class DiceDealerTest {
    @Test
    public void isInstanceOf(){
        Dealer diceDealer = new CardDealer();
        Assert.assertTrue(diceDealer instanceof  Dealer);


    }
}

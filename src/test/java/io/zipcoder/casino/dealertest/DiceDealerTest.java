package io.zipcoder.casino.dealertest;

import io.zipcoder.casino.dealer.CardDealer;
import io.zipcoder.casino.dealer.Dealer;
import io.zipcoder.casino.dealer.DiceDealer;
import io.zipcoder.casino.tools.Dice;
import org.junit.Assert;
import org.junit.Test;

public class DiceDealerTest {
    @Test
    public void isInstanceOf() {
        Dealer diceDealer = new DiceDealer(2);
        Assert.assertTrue(diceDealer instanceof Dealer);
    }

    @Test
    public void sumOfDice() {
        DiceDealer diceDealer = new DiceDealer(2);
        diceDealer.rollDice();
        Integer actual = diceDealer.getValue();
        Assert.assertTrue(actual >= 2 && actual <= 12);

    }
}

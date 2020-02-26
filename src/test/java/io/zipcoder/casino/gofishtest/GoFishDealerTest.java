package io.zipcoder.casino.gofishtest;

import io.zipcoder.casino.dealer.CardDealer;
import io.zipcoder.casino.dealer.Dealer;
import io.zipcoder.casino.gofish.GoFishDealer;
import io.zipcoder.casino.gofish.GoFishHand;
import org.junit.Assert;
import org.junit.Test;

public class GoFishDealerTest {

    @Test
    public void isInstanceOf(){
        GoFishDealer goFishDealer = new GoFishDealer();
        Assert.assertTrue(goFishDealer instanceof  CardDealer);
    }

    @Test
    public void checkDealerHasDeck(){
        //Given
        GoFishDealer goFishDealer = new GoFishDealer();

        //When
        int expected = 52;
        int actual = goFishDealer.getDealerDeck().checkSize();

        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void checkDealerHasGoFishHandTest(){
        GoFishDealer goFishDealer = new GoFishDealer();
        Assert.assertTrue(goFishDealer.getGoFishHand() instanceof GoFishHand);
    }

}

package io.zipcoder.casino.dealertest;

import io.zipcoder.casino.dealer.CardDealer;
import io.zipcoder.casino.dealer.Dealer;
import io.zipcoder.casino.dealer.DiceDealer;
import io.zipcoder.casino.tools.Deck;
import org.junit.Assert;
import org.junit.Test;

public class CardDealerTest {

    @Test
    public void isInstanceOf(){
       Dealer cardDealer = new CardDealer();
        Assert.assertTrue(cardDealer instanceof  Dealer);
    }

    @Test
    public void checkDealerHasDeck(){
        //Given
        CardDealer cardDealer = new CardDealer();

        //When
        int expected = 52;
        int actual = cardDealer.getDealerDeck().checkSize();

        //Then
        Assert.assertEquals(expected,actual);
    }

}

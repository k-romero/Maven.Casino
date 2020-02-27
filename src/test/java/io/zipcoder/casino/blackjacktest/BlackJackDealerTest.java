package io.zipcoder.casino.blackjacktest;

import io.zipcoder.casino.blackjack.BlackJackDealer;
import io.zipcoder.casino.blackjack.BlackJackHand;
import io.zipcoder.casino.dealer.CardDealer;
import org.junit.Assert;
import org.junit.Test;

public class BlackJackDealerTest {

    @Test
    public void isInstanceOf(){
        BlackJackDealer blackJackDealer = new BlackJackDealer();
        Assert.assertTrue(blackJackDealer instanceof CardDealer);
    }

    @Test
    public void checkDealerHasDeck(){
        //Given
        BlackJackDealer blackJackDealer = new BlackJackDealer();

        //When
        int expected = 52;
        int actual = blackJackDealer.getDealerDeck().checkSize();

        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void checkDealerHasBlackJackHandTest(){
        BlackJackDealer blackJackDealer = new BlackJackDealer();
        Assert.assertTrue(blackJackDealer.getBlackJackHand() instanceof BlackJackHand);
    }


}

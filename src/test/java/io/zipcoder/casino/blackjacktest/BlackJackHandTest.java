package io.zipcoder.casino.blackjacktest;

import io.zipcoder.casino.blackJack.BlackJackHand;
import io.zipcoder.casino.blackJack.BlackJackPlayer;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Hand;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BlackJackHandTest {

    @Test
    public void TestConstructor(){
        // Given
        //BlackJackHand hand = new BlackJackHand();
        // When


        // Then
    }

    @Test
    public void TestGetSumOfHand(){
        // Given
        BlackJackHand hand = new BlackJackHand();

        // When
        hand.addCardToHand(new Card(Suit.CLUB, Face.FIVE ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.JACK ));
        Integer expected = 15;
        Integer actual = hand.getSumOfHand(false);

        // Then
        Assert.assertEquals(expected, actual);

    }

}

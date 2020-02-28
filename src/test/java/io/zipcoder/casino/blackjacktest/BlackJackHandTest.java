package io.zipcoder.casino.blackjacktest;

import io.zipcoder.casino.blackjack.BlackJackHand;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

public class BlackJackHandTest {

    @Test
    public void TestConstructor(){
        // Given
        BlackJackHand hand = new BlackJackHand();
        // When
        Integer expected = 0;
        Integer actual = hand.getNumOfCards();
        // Then
        Assert.assertEquals(expected, actual);
    }

   /* @Test
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

    @Test
    public void TestGetSumOfHandWithAce(){
        // Given
        BlackJackHand hand = new BlackJackHand();

        // When
        hand.addCardToHand(new Card(Suit.CLUB, Face.FIVE ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.ACE ));
        Integer expected = 16;
        Integer actual = hand.getSumOfHand(true);

        // Then
        Assert.assertEquals(expected, actual);

    }*/

}

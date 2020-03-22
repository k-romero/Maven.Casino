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


    @Test
    public void calculateHandWithOneAceTest(){
        // Given
        BlackJackHand hand = new BlackJackHand();

        // When
        hand.addCardToHand(new Card(Suit.CLUB, Face.ACE ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.SEVEN ));
        Integer expected = 18;
        Integer actual = hand.calculateHand(hand);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculateHandWithTwoAcesTest(){
        // Given
        BlackJackHand hand = new BlackJackHand();

        // When
        hand.addCardToHand(new Card(Suit.CLUB, Face.TWO ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.ACE ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.ACE ));
        Integer expected = 14;
        Integer actual = hand.calculateHand(hand);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculateHandWithMultipleAcesTest(){
        // Given
        BlackJackHand hand = new BlackJackHand();

        // When
        hand.addCardToHand(new Card(Suit.CLUB, Face.TWO ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.ACE ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.ACE ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.ACE ));
        hand.addCardToHand(new Card(Suit.CLUB, Face.ACE ));
        Integer expected = 16;
        Integer actual = hand.calculateHand(hand);

        // Then
        Assert.assertEquals(expected, actual);
    }

}

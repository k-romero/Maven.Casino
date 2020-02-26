package io.zipcoder.casino.toolstest;

import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Hand;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

public class HandTest {


    @Test
    public void addCardToHandTest(){
        Hand testHand = new Hand(){};
        Card testCard = new Card(Suit.CLUB, Face.EIGHT );

        testHand.addCardToHand(testCard);
        int expected = 1;
        int actual = testHand.getNumOfCards();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void removeCardFromHandTest(){
        Hand testHand = new Hand(){};
        Card testCard = new Card(Suit.CLUB, Face.EIGHT );

        testHand.addCardToHand(testCard);
        testHand.addCardToHand(testCard);
        testHand.removeCardFromHand(Suit.CLUB, Face.EIGHT);
        int expected = 1;
        int actual = testHand.getNumOfCards();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void displayHands(){
        Hand testHand = new Hand(){};
        Card testCard = new Card(Suit.CLUB, Face.EIGHT );
        Card testCard1 = new Card(Suit.SPADE, Face.ACE );
        testHand.addCardToHand(testCard);
        testHand.addCardToHand(testCard1);

        testHand.displayHands();

    }
}

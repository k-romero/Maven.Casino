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
    public void discardCardsFromHandTest(){
        Hand testHand = new Hand(){};
        testHand.addCardToHand(new Card(Suit.CLUB, Face.EIGHT));
        testHand.addCardToHand(new Card(Suit.CLUB, Face.NINE));

        testHand.discardCardsFromHand(Suit.CLUB, Face.EIGHT);
        int expected = 1;
        int actual = testHand.getNumOfCards();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void removeCardTest(){
        Hand testHand = new Hand(){};
        testHand.addCardToHand(new Card(Suit.CLUB, Face.EIGHT));
        testHand.addCardToHand(new Card(Suit.CLUB, Face.NINE));

        Card c = testHand.removeCard(Suit.CLUB, Face.EIGHT);
        int expected = 1;
        int actual = testHand.getNumOfCards();
        Assert.assertEquals(expected,actual);
        Assert.assertEquals(1,testHand.getNumOfCards());
        Assert.assertEquals(8,c.getFace().getValue());
        Assert.assertEquals("Club",c.getSuit().getSuitName());
        testHand.displayHands();
    }

    @Test
    public void removeCardFailTest(){
        Hand testHand = new Hand(){};
        testHand.addCardToHand(new Card(Suit.CLUB, Face.EIGHT));
        testHand.addCardToHand(new Card(Suit.CLUB, Face.NINE));

        Card c = testHand.removeCard(Suit.HEART, Face.EIGHT);

        Assert.assertNull(c);
    }


    @Test
    public void removeCardFailTest2(){
        Hand testHand = new Hand(){};
        Card c = testHand.removeCard(Suit.CLUB, Face.EIGHT);
        Assert.assertNull(c);
    }

    @Test
    public void displayHandsTest(){
        Hand testHand = new Hand(){};
        Card testCard = new Card(Suit.CLUB, Face.EIGHT );
        Card testCard1 = new Card(Suit.SPADE, Face.ACE );
        testHand.addCardToHand(testCard);
        testHand.addCardToHand(testCard1);

        testHand.displayHands();
    }

    @Test
    public void displayHandsWithSymbolTest(){
        Hand testHand = new Hand(){};
        Card testCard = new Card(Suit.HEART, Face.EIGHT );
        Card testCard1 = new Card(Suit.DIAMOND, Face.ACE );
        testHand.addCardToHand(testCard);
        testHand.addCardToHand(testCard1);

        testHand.displayHandsWithSymbol();

    }
}

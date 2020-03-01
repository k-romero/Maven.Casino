package io.zipcoder.casino.gofishtest;

import io.zipcoder.casino.gofish.GoFishHand;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GoFishHandTest {
    @Test
    public void TestConstructor(){
        GoFishHand myHand = new GoFishHand();
        Integer expected = 0;
        Integer actual = myHand.getNumOfCards();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void doesMyHandHaveTest(){
        GoFishHand myHand = new GoFishHand();
        myHand.addCardToHand(new Card(Suit.SPADE, Face.NINE ));
        myHand.addCardToHand(new Card(Suit.HEART, Face.FOUR ));

        Assert.assertTrue(myHand.doesMyHandHave(Face.NINE));
    }

    @Test
    public void doesMyHandHaveNOTTest(){
        GoFishHand myHand = new GoFishHand();
        myHand.addCardToHand(new Card(Suit.SPADE, Face.SIX ));
        myHand.addCardToHand(new Card(Suit.HEART, Face.FOUR ));

        Assert.assertFalse(myHand.doesMyHandHave(Face.NINE));
    }

    @Test
    public void howManyDoIHaveTest(){
        GoFishHand myHand = new GoFishHand();
        myHand.addCardToHand(new Card(Suit.SPADE, Face.NINE ));
        myHand.addCardToHand(new Card(Suit.HEART, Face.FOUR ));
        myHand.addCardToHand(new Card(Suit.HEART, Face.FIVE ));
        myHand.addCardToHand(new Card(Suit.HEART, Face.QUEEN ));
        myHand.addCardToHand(new Card(Suit.DIAMOND, Face.FOUR ));

        Assert.assertEquals(2, (int)myHand.howManyDoIHave(Face.FOUR));
    }

    @Test
    public void listEveryFaceIHaveTest(){
        GoFishHand myHand = new GoFishHand();
        myHand.addCardToHand(new Card(Suit.SPADE, Face.NINE ));
        myHand.addCardToHand(new Card(Suit.HEART, Face.FOUR ));
        myHand.addCardToHand(new Card(Suit.HEART, Face.FIVE ));
        myHand.addCardToHand(new Card(Suit.HEART, Face.QUEEN ));
        myHand.addCardToHand(new Card(Suit.DIAMOND, Face.FOUR ));

        Face[] expected = {Face.FOUR, Face.FIVE, Face.NINE, Face.QUEEN};
        ArrayList<Face> actual = myHand.listEveryFaceIHave();

        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i] ,actual.get(i));
        }
        Assert.assertEquals(expected.length, actual.size());

    }

    @Test
    public void giveCardsToTest(){

        GoFishHand hand1 = new GoFishHand();
        hand1.addCardToHand(new Card(Suit.SPADE, Face.NINE ));
        hand1.addCardToHand(new Card(Suit.HEART, Face.FOUR ));
        hand1.addCardToHand(new Card(Suit.HEART, Face.FIVE ));
        hand1.addCardToHand(new Card(Suit.HEART, Face.QUEEN ));
        hand1.addCardToHand(new Card(Suit.DIAMOND, Face.FOUR ));
        hand1.addCardToHand(new Card(Suit.CLUB, Face.FOUR ));

        GoFishHand hand2 = new GoFishHand();
        hand2.addCardToHand(new Card(Suit.CLUB, Face.ACE ));

        hand1.giveCardsTo(Face.FOUR,hand2);

        Assert.assertEquals(4, hand2.getNumOfCards());
        Assert.assertEquals(3, hand1.getNumOfCards());
        hand1.displayHands();
    }

    @Test
    public void increaseTallyTest(){
        GoFishHand hand1 = new GoFishHand();
        hand1.increaseTally();
        hand1.increaseTally();

        Assert.assertEquals(2,(int)hand1.getTallyMatches());
    }



}

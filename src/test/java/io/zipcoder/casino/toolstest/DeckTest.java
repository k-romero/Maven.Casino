package io.zipcoder.casino.toolstest;


import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class DeckTest {

    @Test
    public void peekTest(){
        Deck deck = new Deck();
        Card expected = new Card(Suit.DIAMOND, Face.KING);
        Card actual = deck.peek();
        Assert.assertEquals(expected.toString(),actual.toString());
    }

    @Test
    public void removeTest(){
        Deck deck = new Deck();
        deck.remove();
        Card expected = new Card(Suit.DIAMOND, Face.QUEEN);
        Card actual = deck.peek();
        Assert.assertEquals(expected.toString(),actual.toString());
    }

    @Test
    public void shuffleTest(){
        Deck deck = new Deck();
        String expected = deck.toString();
        deck.shuffle();
        String actual = deck.toString();
        Assert.assertNotEquals(expected,actual);
    }

    @Test
    public void getCheckSize(){
        Deck deck = new Deck();
        int actual = deck.checkSize() - 3;
        deck.remove();
        deck.remove();
        deck.remove();
        int expected = deck.checkSize();
        Assert.assertEquals(expected,actual);

    }

    @Test
    public void getDeck(){
        Deck deck = new Deck();
        int expected = 52;
        int actual = deck.getDeck().size();
        Assert.assertEquals(expected,actual);
    }


}

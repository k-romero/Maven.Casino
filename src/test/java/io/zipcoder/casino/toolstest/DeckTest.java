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



}

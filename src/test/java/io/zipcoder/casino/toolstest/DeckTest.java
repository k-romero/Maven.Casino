package io.zipcoder.casino.toolstest;


import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

public class DeckTest {

    @Test
    public void peekTest(){
        Deck deck = new Deck();
        Card expected = new Card(Suit.DIAMOND, Face.KING);
        Card actual = deck.peekDeck();
        Assert.assertEquals(expected.toString(),actual.toString());
    }

    @Test
    public void removeTest(){
        Deck deck = new Deck();
        deck.popACard();
        Card expected = new Card(Suit.DIAMOND, Face.QUEEN);
        Card actual = deck.peekDeck();
        Assert.assertEquals(expected.toString(),actual.toString());
    }

    @Test
    public void takeCardTest(){
        Deck deck = new Deck();
        Card taken = deck.takeCard();
        Card expected = new Card(Suit.DIAMOND, Face.QUEEN);
        Card actual = deck.peekDeck();
        Assert.assertEquals(expected.toString(),actual.toString());
        Assert.assertEquals(new Card(Suit.DIAMOND, Face.KING).toString(),taken.toString());

    }

    @Test
    public void shuffleTest(){
        Deck deck = new Deck();
        String expected = deck.toString();
        deck.shuffleDeck();
        String actual = deck.toString();
        Assert.assertNotEquals(expected,actual);
    }

    @Test
    public void getCheckSize(){
        Deck deck = new Deck();
        int actual = deck.checkSize() - 3;
        deck.popACard();
        deck.popACard();
        deck.popACard();
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

    @Test
    public void takeExceptionTest(){
        Deck deck = new Deck();
        for (int i = 0; i < 52; i++) {
            deck.popACard();
        }

        Card actual = deck.takeCard();
        Assert.assertNull(actual);
    }


}

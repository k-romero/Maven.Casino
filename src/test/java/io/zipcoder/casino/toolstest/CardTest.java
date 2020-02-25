package io.zipcoder.casino.toolstest;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

public class CardTest {
    @Test
    public void CardGetCardSuitTest(){
        Card c = new Card(Suit.DIAMOND, Face.FIVE);
        Suit expectedSuit = Suit.DIAMOND;
        Suit actualSuit = c.getSuit();
        Assert.assertEquals(expectedSuit,actualSuit);
    }

    @Test
    public void CardGetCardFaceTest(){
        Card c = new Card(Suit.DIAMOND, Face.FIVE);
        Face expectedFace = Face.FIVE;
        Face actualFace = c.getFace();
        Assert.assertEquals(expectedFace,actualFace);
    }

    @Test
    public void CardToStringTest(){
        Card c = new Card(Suit.DIAMOND, Face.QUEEN);
        String expectedString = "DiamondQ";
        String actualString = c.toString();
        Assert.assertEquals(expectedString,actualString);
    }

    @Test
    public void CardGetSuitSymbol(){
        Card c = new Card(Suit.DIAMOND, Face.QUEEN);
        Character expectedSymbol = '\u2666';
        Character actualSymbol = c.getSuit().getSuitSymbol();
        Assert.assertEquals(expectedSymbol,actualSymbol);
    }
}

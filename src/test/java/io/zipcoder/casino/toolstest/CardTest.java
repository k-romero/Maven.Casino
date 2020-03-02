package io.zipcoder.casino.toolstest;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Color;
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
    public void CardToStringREDTest(){
        Card c = new Card(Suit.DIAMOND, Face.QUEEN);
        String expectedString = Color.ANSI_RED+"Q Diamond"+Color.ANSI_RESET;
        String actualString = c.toString();
        Assert.assertEquals(expectedString,actualString);
    }

    @Test
    public void CardToStringGREYTest(){
        Card c = new Card(Suit.SPADE, Face.QUEEN);
        String expectedString = Color.ANSI_GREY+"Q Spade"+Color.ANSI_RESET;
        String actualString = c.toString();
        Assert.assertEquals(expectedString,actualString);
    }


    @Test
    public void CardToStringWithSymbolREDTest(){
        Card c = new Card(Suit.DIAMOND, Face.QUEEN);
        String expectedString = Color.ANSI_RED+"Q "+Suit.DIAMOND.getSuitSymbol()+Color.ANSI_RESET;
        String actualString = c.toStringWithSymbol();
        Assert.assertEquals(expectedString,actualString);
    }

    @Test
    public void CardToStringWithSymbolGREYTest(){
        Card c = new Card(Suit.SPADE, Face.QUEEN);
        String expectedString = Color.ANSI_GREY+"Q "+Suit.SPADE.getSuitSymbol()+Color.ANSI_RESET;
        String actualString = c.toStringWithSymbol();
        Assert.assertEquals(expectedString,actualString);
    }

    @Test
    public void CardGetSuitSymbol(){
        Card c = new Card(Suit.DIAMOND, Face.QUEEN);
        Character expectedSymbol = '\u2666';
        Character actualSymbol = c.getSuit().getSuitSymbol();
        Assert.assertEquals(expectedSymbol,actualSymbol);
    }

    @Test
    public void cardCompareTest(){
        Card c1 = new Card(Suit.DIAMOND, Face.QUEEN);
        Card c2 = new Card(Suit.DIAMOND, Face.FIVE);
        Assert.assertTrue(c1.compareTo(c2)>0);
    }

    @Test
    public void faceGetNameStringTest(){
        Face f = Face.QUEEN;
        Assert.assertEquals("QUEEN", f.getNameString());
    }

    @Test
    public void getAltValueTest(){
        Face f = Face.ACE;
        Assert.assertEquals(11, f.getAltValue());
    }


    @Test
    public void stringToFaceTest(){
        Assert.assertEquals(Face.KING,Face.toFace("king"));
        Assert.assertEquals(Face.KING,Face.toFace("k"));
        Assert.assertEquals(Face.TWO,Face.toFace("2"));
        Assert.assertEquals(Face.TWO,Face.toFace("TwO"));

    }

}

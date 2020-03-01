package io.zipcoder.casino.tools;

public class Card implements Comparable<Card> {
    private Face face;
    private Suit suit;

    public Card(Suit suit, Face face) {
        this.face = face;
        this.suit = suit;
    }

    public Suit getSuit(){
        return suit;
    }
    public Face getFace(){
        return face;
    }

    @Override
    public String toString() {
        String s = "";
        if(suit == Suit.CLUB || suit == Suit.SPADE)
            s = Color.ANSI_GREY + face.getFaceString() + " " + suit.getSuitName()+Color.ANSI_RESET;
        else
            s = Color.ANSI_RED + face.getFaceString() + " " + suit.getSuitName()+Color.ANSI_RESET;
        return s;
    }


    public String toStringWithSymbol() {
        String s = "";
        if(suit == Suit.CLUB || suit == Suit.SPADE)
            s = Color.ANSI_GREY + face.getFaceString() + " " + suit.getSuitSymbol()+Color.ANSI_RESET;
        else
            s = Color.ANSI_RED + face.getFaceString() + " " + suit.getSuitSymbol()+Color.ANSI_RESET;
        return s;
    }

    @Override
    public int compareTo(Card card) {
        return getFace().getValue() - card.getFace().getValue();
    }
}


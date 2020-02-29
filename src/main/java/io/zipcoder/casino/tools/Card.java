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
        return (face.getFaceString() + " " + suit.getSuitName());
    }


    public String toStringWithSymbol() {
        return (face.getFaceString() + " " + suit.getSuitSymbol());
    }

    @Override
    public int compareTo(Card card) {
        return getFace().getValue() - card.getFace().getValue();
    }
}


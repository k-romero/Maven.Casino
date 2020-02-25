package io.zipcoder.casino.tools;

public class Card {
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
}


package io.zipcoder.casino.tools;

public enum Suit {

    CLUB("Club",'\u2663'),
    SPADE("Spade",'\u2660'),
    HEART("Heart",'\u2665'),
    DIAMOND("Diamond",'\u2666');

    private String suitName;
    private char suitIcon;

    private Suit(String suitName, char symbol){
        this.suitName = suitName;
        this.suitIcon = symbol;
    }

    public String getSuitName(){ return suitName;}
    public char getSuitSymbol(){
        return suitIcon;
    }

}

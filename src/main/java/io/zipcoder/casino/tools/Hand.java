package io.zipcoder.casino.tools;

import java.util.ArrayList;

public abstract class Hand {

    public ArrayList<Card> hand;
    int numberOfCardsInHand = 0;

    public Hand(){
        this.hand= new ArrayList<Card>();
    }

    public void addCardToHand(Card cardToAdd){
        hand.add(cardToAdd);
    }

    public void removeCardFromHand(Card cardToRemove){
        hand.remove(cardToRemove);
    }

    public void displayHands(){

        System.out.println(hand);

    }

    public int getNumOfCards(){
        numberOfCardsInHand = hand.size();

        return numberOfCardsInHand;
    }
}

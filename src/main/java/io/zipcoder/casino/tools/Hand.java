package io.zipcoder.casino.tools;

import java.util.ArrayList;

public abstract class Hand {

    public ArrayList<Card> cardsOnHand;
    int numberOfCardsInHand = 0;

    public Hand(){
        this.cardsOnHand= new ArrayList<>();
    }

    public void addCardToHand(Card cardToAdd){
        cardsOnHand.add(cardToAdd);
    }

    public void removeCardFromHand(Card cardToRemove){
        cardsOnHand.remove(cardToRemove);
    }

    public void displayHands(){
        for (Card c :cardsOnHand) {
            System.out.println(c.toString());
        }
    }

    public int getNumOfCards(){
        numberOfCardsInHand = cardsOnHand.size();

        return numberOfCardsInHand;
    }
}

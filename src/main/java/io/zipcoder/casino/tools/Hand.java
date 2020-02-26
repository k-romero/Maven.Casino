package io.zipcoder.casino.tools;

import java.util.ArrayList;

public abstract class Hand {

    private ArrayList<Card> cardsOnHand;
    private Integer numberOfCardsInHand = 0;

    public Hand(){
        this.cardsOnHand= new ArrayList<>();
    }

    public void addCardToHand(Card cardToAdd){
        cardsOnHand.add(cardToAdd);
    }

    public void removeCardFromHand(Suit s, Face f){
        int i = 0;
        for(Card c :cardsOnHand){
            if(c.getFace()==f && c.getSuit()==s){
                cardsOnHand.remove(i);
            }
            i++;
        }
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

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }
}

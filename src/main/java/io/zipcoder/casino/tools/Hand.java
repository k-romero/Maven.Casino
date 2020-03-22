package io.zipcoder.casino.tools;

import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public abstract class Hand {

    private ArrayList<Card> cardsOnHand;

    public Hand(){
        this.cardsOnHand= new ArrayList<>();
    }

    public void addCardToHand(Card cardToAdd){
        cardsOnHand.add(cardToAdd);
    }

    //this will discard any card with given suit and face
    public void discardCardsFromHand(Suit s, Face f){
        cardsOnHand.removeIf((card)->card.getFace()==f && card.getSuit()==s);
    }

    //this remove will actually return the information of the card that is removed
    public Card removeCard(Suit s, Face f){
        for(Iterator<Card> i = cardsOnHand.iterator();i.hasNext();){
            Card currCard = i.next();
            if(currCard.getSuit()==s && currCard.getFace()==f){
                i.remove();
                return currCard;
            }
        }
        return null;
    }


    public void displayHands(){
        Console con = new Console(System.in, System.out);
        for (Card c :cardsOnHand) {
            con.println("[ "+c.toString()+" ]");
        }
    }

    public void displayHandsWithSymbol(){
        Console con = new Console(System.in, System.out);
        for (Card c :cardsOnHand) {
            con.print("[ "+c.toStringWithSymbol()+" ]");
        }
    }
    public int getNumOfCards(){
        return cardsOnHand.size();
    }

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public void sortCardsOnHand(){
        Collections.sort(cardsOnHand);
    }
}

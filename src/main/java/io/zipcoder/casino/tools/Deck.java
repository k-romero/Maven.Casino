package io.zipcoder.casino.tools;

import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Stack;

public class Deck {

    Stack<Card> deck = new Stack<Card>();

    //default constructor that will only create one deck for now
    public Deck() {
        //loop thru every suit
        for(Suit s : Suit.values()){
            //loop thru every face
            for(Face f : Face.values()){
                deck.push(new Card(s,f));
            }
        }
    }

    public Card peekDeck() {
        return deck.peek();
    }

    public Card popACard(){
        return deck.pop();
    }

    public int checkSize(){
        return deck.size();
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public Stack<Card> getDeck(){
        return deck;
    }
    public Card takeCard(){
        try {
            return popACard();
        }catch (EmptyStackException e ){
            return null;
        }
    }

    @Override
    public String toString(){
        String s = "";
        for( Card c:deck){
            s += c.toString();
        }
        return s;
    }
}

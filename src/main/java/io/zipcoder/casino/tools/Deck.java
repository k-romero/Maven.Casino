package io.zipcoder.casino.tools;

import java.util.Collections;
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

    public Card peek() {
        return deck.peek();
    }
    public void remove(){
      deck.pop();
    }
    public void shuffle(){
        Collections.shuffle(deck);
    }
    public Stack getDeck(){
        return deck;
    }
}

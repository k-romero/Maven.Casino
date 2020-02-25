package io.zipcoder.casino.tools;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    Stack<Card> deck = new Stack<Card>();

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

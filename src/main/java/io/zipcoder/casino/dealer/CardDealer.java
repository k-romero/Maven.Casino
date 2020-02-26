package io.zipcoder.casino.dealer;

import io.zipcoder.casino.tools.Deck;


public class CardDealer extends Dealer {
    Deck dealerDeck;

    public CardDealer() {
        this.dealerDeck = new Deck();
    }


    public Deck getDealerDeck(){
        return this.dealerDeck;
    }
}

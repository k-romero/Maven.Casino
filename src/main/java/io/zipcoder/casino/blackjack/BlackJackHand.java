package io.zipcoder.casino.blackJack;

import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Hand;


import java.util.ArrayList;

public class BlackJackHand extends Hand {

    public BlackJackHand() {
        //super.cardsOnHand = new ArrayList<>();

    }

    public Integer getSumOfHand(boolean aceIs11){
        int sum = 0;
        for(Card c: super.getCardsOnHand()){
            if(c.getFace().getValue() > 10){
                sum += 10;
            } else if(c.getFace() == Face.ACE && aceIs11) {
                sum += 11;
            }
            else {
                sum += c.getFace().getValue();
            }
        }

        return sum;
    }

}

package io.zipcoder.casino.blackjack;

import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Hand;

public class BlackJackHand extends Hand {

    private int handValue;

    public BlackJackHand() {
        //super.cardsOnHand = new ArrayList<>();

    }

    public int calculateHand(Hand hand){
        handValue = 0;
        Boolean containsAce = false;
        for (Card c : hand.getCardsOnHand()){
            int value = c.getFace().getAltValue();
            handValue += value;
        }
        for(Card c : hand.getCardsOnHand()){
            if(c.getFace() == Face.ACE){
                containsAce = true;
            }
            if(containsAce && handValue < 12){
                handValue += 10;
            }
        }
        return handValue;
    }

    public int  calculateCards (Hand hand){
        Integer sum=0;
        for( Card c : super.getCardsOnHand()){
            if(c.getFace().getValue() > 10){
                sum += 10;}
            else
                sum+= c.getFace().getValue();
        }
        return sum;
    }



}

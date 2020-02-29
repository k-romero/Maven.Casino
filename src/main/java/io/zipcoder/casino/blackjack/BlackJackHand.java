package io.zipcoder.casino.blackjack;

import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Hand;

public class BlackJackHand extends Hand {

    public BlackJackHand() {
        //super.cardsOnHand = new ArrayList<>();

    }

   /* public Integer sumOfHand(Hand hand){

        int sum = 0;
        for(Card c: super.getCardsOnHand()){
            if(c.getFace().getValue() > 10){
                sum += 10;
            } else if(c.getFace() == Face.ACE && sum < 21 ) {
                sum += c.getFace().getAltValue();
            }
            else {
                sum += c.getFace().getValue();
            }
        }

        return sum;
    }*/


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

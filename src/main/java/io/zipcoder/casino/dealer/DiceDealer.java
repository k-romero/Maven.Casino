package io.zipcoder.casino.dealer;

import io.zipcoder.casino.tools.Dice;

public  class DiceDealer extends Dealer {
    Integer value;
    Dice dice;

    public DiceDealer( int numberOfDice){
        this.dice= new Dice(numberOfDice);
        this.value=dice.tossAndSum();

    }
    public Integer getValue() {
        return value;
    }

}

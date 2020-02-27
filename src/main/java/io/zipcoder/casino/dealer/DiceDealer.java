package io.zipcoder.casino.dealer;

import io.zipcoder.casino.tools.Dice;

public  class DiceDealer extends Dealer {
    Integer diceValue;
    Dice dice;

    public DiceDealer( int numberOfDice){
        this.dice= new Dice(numberOfDice);
        this.diceValue = 0;
    }

    public Integer getValue() {
        return diceValue;
    }

    public void rollDice(){
        this.diceValue = dice.tossAndSum();
    }


}

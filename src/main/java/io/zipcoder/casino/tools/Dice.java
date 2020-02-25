package io.zipcoder.casino.tools;

import java.util.Random;

public class Dice {

    Integer numberOfDice;

    public Dice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public Integer getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public Integer rollDice() {
        Random roll = new Random();
        return roll.nextInt(6) + 1;
    }


    public Integer tossAndSum() {

        Integer sum = 0;

        for (int i = 1; i <= numberOfDice; i++){
            sum += rollDice();
        }

        return sum;

    }

    public Integer tossAndSumWithSeed(Integer seed) {

        Random roll = new Random(seed);
        Integer sum = 0;

        for (int i = 1; i <= numberOfDice; i++){
            sum += roll.nextInt(6) + 1;
        }

        return sum;

    }



}

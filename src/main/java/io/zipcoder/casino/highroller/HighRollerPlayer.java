package io.zipcoder.casino.highroller;

import io.zipcoder.casino.player.GamblingPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Dice;

public class HighRollerPlayer implements GamblingPlayer {

    Integer diceValue;
    Dice dice;
    Player player1;

    public HighRollerPlayer(Player p1) {
        Dice myDice = new Dice(2);
        this.diceValue = 0;
        this.dice = myDice;
        this.player1 = p1;
    }


    public String getName() {
        return player1.getName();
    }

    public Integer getFund() {
        return player1.getPlayerFunds();
    }

    public void addFund(int newFund) {
        Integer someFund = player1.getPlayerFunds();
        Integer updatedFund = someFund + newFund;
        player1.setPlayerFunds(updatedFund);

    }

    public Integer getRanking() {
        return 0;
    }

    public Integer getNumberOfWins() {
        return 0;
    }

    public Integer rollDice() {
        this.diceValue = dice.tossAndSum();
        return diceValue;

    }

    public Integer getDiceValue() {
        return diceValue;
    }
}

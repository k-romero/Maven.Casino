package io.zipcoder.casino.highroller;

import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Dice;

public class HighRollerPlayer {

    Integer value;
    Dice dice;
    Player player1;

    public HighRollerPlayer(Player p1) {
        Dice myDice = new Dice(2);
        this.value = value;
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
}

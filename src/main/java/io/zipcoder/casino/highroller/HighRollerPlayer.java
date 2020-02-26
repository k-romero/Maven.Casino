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

    public Player getPlayerData() {
        return player1;
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

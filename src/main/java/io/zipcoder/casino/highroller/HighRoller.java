package io.zipcoder.casino.highroller;

import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.game.GameControl;
import io.zipcoder.casino.player.Player;

public class HighRoller implements GameControl, GamblingGame {

    Integer player1Bet = 0;


    public void start(Player p1) {

    }

    public void end(Player p1) {

    }

    public void placeBet(Player p1, int amount) {
        player1Bet = amount;


    }

    public void payout(Player p1, int amount) {

    }

}

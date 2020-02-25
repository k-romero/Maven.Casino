package io.zipcoder.casino.game;

import io.zipcoder.casino.player.Player;

public interface GamblingGame {
    void placeBet(Player p1, int amount);
    void payout(Player p1, int amount);
}


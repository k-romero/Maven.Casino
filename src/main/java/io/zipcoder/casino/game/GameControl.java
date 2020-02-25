package io.zipcoder.casino.game;

import io.zipcoder.casino.player.Player;

public interface GameControl {

    void start(Player p1);
    void end(Player p1);

}

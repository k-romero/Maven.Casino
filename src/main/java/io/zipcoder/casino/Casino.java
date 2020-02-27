package io.zipcoder.casino;


import io.zipcoder.casino.gofish.GoFish;
import io.zipcoder.casino.player.Player;

public class Casino {
    public static void main(String[] args) {
        // write your tests before you start fucking with this
        // GO TEAM

        Player p1 = new Player(10, "JJ", 1000, true) ;
        GoFish g = new GoFish();
        g.start(p1);
    }
}

package io.zipcoder.casino.game;

import io.zipcoder.casino.player.Player;

public abstract class Game  {
    Player player;
    Player house;
    int playBet;


    public int compare(int sum1, int sum2){
        if(sum1 > sum2){
            return 1;
        }
        else if (sum1 < sum2){
            return -1;
        }
        else
            return 0;
    }

}

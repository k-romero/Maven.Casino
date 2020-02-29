package io.zipcoder.casino.highrollertest;

import io.zipcoder.casino.highroller.HighRoller;
import io.zipcoder.casino.highroller.HighRollerPlayer;
import io.zipcoder.casino.player.Player;
import org.junit.Assert;
import org.junit.Test;

public class HighRollerTest {

    @Test
    public void checkDealerRollsDice() {
        HighRoller game = new HighRoller();

        Integer beforeRoll = game.displayDealerRoll();
        game.dealerRollsDice();
        Integer afterRoll = game.displayDealerRoll();

        Assert.assertNotEquals(beforeRoll,afterRoll);
    }

    @Test
    public void checkArt(){
        HighRoller game = new HighRoller();
        System.out.println(game.highRollerImage());
    }




}

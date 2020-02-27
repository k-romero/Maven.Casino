package io.zipcoder.casino.highrollertest;

import io.zipcoder.casino.highroller.HighRoller;
import io.zipcoder.casino.highroller.HighRollerPlayer;
import io.zipcoder.casino.player.Player;
import org.junit.Assert;
import org.junit.Test;

public class HighRollerTest {

    @Test
    public void checkToSeeIfHighRollerPlayer() {
        //Given
        Player jack = new Player(1234, "Jack", 1000, true );

        HighRoller game = new HighRoller();

        //Uncomment this below line to pass test
//        game.start(jack);

        Assert.assertTrue(game.currentPlayerIsHighRoller() instanceof HighRollerPlayer);
    }


    @Test
    public void checkDealerRollsDice() {
        HighRoller game = new HighRoller();

        Integer beforeRoll = game.displayDealerRoll();
        game.dealerRollsDice();
        Integer afterRoll = game.displayDealerRoll();

        Assert.assertNotEquals(beforeRoll,afterRoll);
    }




}

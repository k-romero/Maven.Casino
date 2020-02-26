package io.zipcoder.casino.highrollertest;

import io.zipcoder.casino.highroller.HighRollerPlayer;
import io.zipcoder.casino.player.Player;
import org.junit.Assert;
import org.junit.Test;

public class HighRollerPlayerTest {

    @Test
    public void constructorTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);
        String playerName = "Jack";
        Assert.assertEquals( playerName, highRollerPlayer.getName());
    }

    @Test
    public void checkFundsTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);
        Integer expectedFund = 1000;
        Integer getFund = highRollerPlayer.getFund();
        Assert.assertEquals( expectedFund, getFund);
    }

    @Test
    public void updateFundTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);
        Integer expectedFund = 1500;
        highRollerPlayer.addFund(500);
        Integer updatedFund = highRollerPlayer.getFund();
        Assert.assertEquals( expectedFund, updatedFund);
    }
}

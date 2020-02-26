package io.zipcoder.casino.highrollertest;

import io.zipcoder.casino.highroller.HighRollerPlayer;
import io.zipcoder.casino.player.Player;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLOutput;

public class HighRollerPlayerTest {

    @Test
    public void constructorTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);
        String playerName = "Jack";
        Assert.assertEquals( playerName, highRollerPlayer.getPlayerData().getName());
    }

    @Test
    public void checkFundsTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);
        Integer expectedFund = 1000;
        Integer getFund = highRollerPlayer.getPlayerData().getPlayerFunds();
        Assert.assertEquals( expectedFund, getFund);
    }

    @Test
    public void updateFundTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);
        Integer expectedFund = 1500;
        highRollerPlayer.getPlayerData().addPlayerFunds(500);
        Integer updatedFund = highRollerPlayer.getPlayerData().getPlayerFunds();
        Assert.assertEquals( expectedFund, updatedFund);
    }
    @Test
    public void checkRollValueDiceTest() {
        //Given
        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);
        // expected

        Integer output = highRollerPlayer.rollDice();
        Assert.assertTrue(output >= 2 && output <= 12 );
    }
    @Test
    public void checkLastDiceValueTest() {
        //Given
        Player corey = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer coreyHighRoller = new HighRollerPlayer(corey);
        // expected

        Integer expected = coreyHighRoller.rollDice();
       // coreyHighRoller.rollDice();
        Integer actual = coreyHighRoller.getDiceValue();
        Assert.assertEquals(expected,actual);
    }

}

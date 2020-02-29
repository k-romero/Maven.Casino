package io.zipcoder.casino.crapstest;
import io.zipcoder.casino.highroller.HighRollerPlayer;
import io.zipcoder.casino.player.Player;
import org.junit.Assert;
import org.junit.Test;


public class CrapsPlayer {

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

    @Test
    public void playerDataTest() {
        //Given
        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);

        // expected
        String name = highRollerPlayer.getPlayerData().getName();
        Assert.assertEquals("Jack",name);

    }

    @Test
    public void placeBetTest() {
        //Given
        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);

        // expected
        highRollerPlayer.placeBet(50);

        int expected = 950;
        int actual = highRollerPlayer.getPlayerData().getPlayerFunds();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void payOutTest() {
        //Given
        Player player = new Player(1234, "Jack", 1000, true );
        HighRollerPlayer highRollerPlayer = new HighRollerPlayer(player);

        // expected
        highRollerPlayer.payOut(50);

        int expected = 1050;
        int actual = highRollerPlayer.getPlayerData().getPlayerFunds();
        Assert.assertEquals(expected,actual);
    }


}

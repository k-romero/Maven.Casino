package io.zipcoder.casino.crapstest;
import io.zipcoder.casino.craps.CrapsPlayer;
import io.zipcoder.casino.player.Player;
import org.junit.Assert;
import org.junit.Test;

public class CrapsTest {
    @Test
    public void constructorTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        CrapsPlayer crapsPlayer = new CrapsPlayer(player);
        String playerName = "Jack";
        Assert.assertEquals( playerName, crapsPlayer.getPlayerData().getName());
    }

    @Test
    public void checkFundsTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        CrapsPlayer crapsPlayer = new CrapsPlayer(player);
        Integer expectedFund = 1000;
        Integer getFund = crapsPlayer.getPlayerData().getPlayerFunds();
        Assert.assertEquals( expectedFund, getFund);
    }

    @Test
    public void updateFundTest() {

        Player player = new Player(1234, "Jack", 1000, true );
        CrapsPlayer crapsPlayer = new CrapsPlayer(player);
        Integer expectedFund = 1500;
        crapsPlayer.getPlayerData().addPlayerFunds(500);
        Integer updatedFund = crapsPlayer.getPlayerData().getPlayerFunds();
        Assert.assertEquals( expectedFund, updatedFund);
    }
    @Test
    public void checkRollValueDiceTest() {
        //Given
        Player player = new Player(1234, "Jack", 1000, true );
        CrapsPlayer crapsPlayer = new CrapsPlayer(player);
        // expected

        Integer output = crapsPlayer.rollDice();
        Assert.assertTrue(output >= 2 && output <= 12 );
    }
    @Test
    public void checkLastDiceValueTest() {
        //Given
        Player corey = new Player(1234, "Jack", 1000, true );
        CrapsPlayer crapsPlayer = new CrapsPlayer(corey);
        // expected

        Integer expected =crapsPlayer.rollDice();
        // coreyHighRoller.rollDice();
        Integer actual =crapsPlayer.getDiceValue();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void playerDataTest() {
        //Given
        Player player = new Player(1234, "Jack", 1000, true );
        CrapsPlayer crapsPlayer = new CrapsPlayer(player);

        // expected
        String name = crapsPlayer.getPlayerData().getName();
        Assert.assertEquals("Jack",name);

    }

    @Test
    public void placeBetTest() {
        //Given
        Player player = new Player(1234, "Jack", 1000, true );
        CrapsPlayer crapsPlayer = new CrapsPlayer(player);

        // expected
        crapsPlayer.placeBet(50);

        int expected = 950;
        int actual = crapsPlayer.getPlayerData().getPlayerFunds();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void payOutTest() {
        //Given
        Player player = new Player(1234, "Jack", 1000, true );
        CrapsPlayer crapsPlayer = new CrapsPlayer(player);

        // expected
        crapsPlayer.payOut(50);

        int expected = 1050;
        int actual = crapsPlayer.getPlayerData().getPlayerFunds();
        Assert.assertEquals(expected,actual);
    }
}

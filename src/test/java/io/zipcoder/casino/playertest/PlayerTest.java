package io.zipcoder.casino.playertest;

import io.zipcoder.casino.player.Player;
import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void constructorTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        Assert.assertTrue(playerTest instanceof Player);
    }

    @Test
    public void getIdTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        int expected = 1000;
        int actual = playerTest.getId();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setIdTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        playerTest.setId(1230);
        int expected = 1230;
        int actual = playerTest.getId();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getNameTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        String expected = "Player Test";
        String actual = playerTest.getName();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setNameTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        playerTest.setName("Team J");
        String expected = "Team J";
        String actual = playerTest.getName();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getPlayerFundsTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        int expected = 200;
        int actual = playerTest.getPlayerFunds();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void setPlayerFundsTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        playerTest.setPlayerFunds(4500);
        int expected = 4500;
        int actual = playerTest.getPlayerFunds();

        Assert.assertEquals(expected,actual);
    }



    @Test
    public void checkDrunkTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);


        Assert.assertTrue(playerTest.isDrunk());
    }

    @Test
    public void setDrunkTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        playerTest.setDrunk(false);

        Assert.assertFalse(playerTest.isDrunk());
    }

    @Test
    public void giveDrinkTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        for (int i = 0; i < 3; i++) {
            playerTest.giveDrink();
        }
        int expected = 3;
        int actual = playerTest.getDrinks();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void isDrunkAfterDrinkTest(){
        int id = 1000;
        String name = "Player Test";
        int playerFunds = 200;
        boolean isDrunk = true;

        Player playerTest = new Player(id,name,playerFunds,isDrunk);

        for (int i = 0; i < 3; i++) {
            playerTest.giveDrink();
        }
        int expected = 3;
        int actual = playerTest.getDrinks();

        Assert.assertTrue(playerTest.isDrunk());
    }



}

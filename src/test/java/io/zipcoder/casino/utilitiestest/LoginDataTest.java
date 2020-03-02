package io.zipcoder.casino.utilitiestest;

import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.utilities.LoginData;
import org.junit.Assert;
import org.junit.Test;

public class LoginDataTest {


    @Test
    public void constructorTest(){
        LoginData ld = new LoginData();
        Assert.assertEquals(0,ld.getPlayerDataBase().size());
    }

    @Test
    public void addPlayerDataTest(){
        LoginData ld = new LoginData();
        ld.addPlayerData(new Player("p1"));
        Assert.assertEquals(1,ld.getPlayerDataBase().size());
    }

    @Test
    public void updatePlayerDataTest(){
        LoginData ld = new LoginData();
        ld.addPlayerData(new Player(1,"p1",100,false));
        Player updatedP1 = new Player(1,"player",400,false);
        ld.updatePlayerData(updatedP1);
        Assert.assertEquals(updatedP1,ld.getPlayerDataBase().get(0));

    }

    @Test
    public void getPlayerDataBase(){
        LoginData ld = new LoginData();
        Player expectedP1 = new Player("p1");
        Player expectedP2 = new Player("p2");
        ld.addPlayerData(expectedP1);
        ld.addPlayerData(expectedP2);

        Assert.assertEquals(2,ld.getPlayerDataBase().size());
        Assert.assertEquals(expectedP1,ld.getPlayerDataBase().get(0));
        Assert.assertEquals(expectedP2,ld.getPlayerDataBase().get(1));
    }
}

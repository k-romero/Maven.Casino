package io.zipcoder.casino.gofishtest;

import io.zipcoder.casino.game.Game;
import io.zipcoder.casino.gofish.GoFish;
import org.junit.Assert;
import org.junit.Test;

public class GoFIshTest {

    @Test
    public void setUpNPCNameTest(){
        GoFish game = new GoFish();
        Assert.assertTrue(game.setUpNPCName().contains("Leila"));
    }

    @Test
    public void getStartingCardTest(){
        GoFish game = new GoFish();
        int numOfNPC = 3;
        Assert.assertEquals(5,game.getStartingCard(numOfNPC));
    }

    @Test
    public void getStartingCardTest2(){
        GoFish game = new GoFish();
        int numOfNPC = 1;
        Assert.assertEquals(7,game.getStartingCard(numOfNPC));
    }

    @Test
    public void showEveryoneNumOfCardTest(){
        GoFish game = new GoFish();
        int numOfNPC = 1;
        game.showEveryoneNumOfCard();
    }
}

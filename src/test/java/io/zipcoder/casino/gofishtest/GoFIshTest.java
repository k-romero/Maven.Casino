package io.zipcoder.casino.gofishtest;

import io.zipcoder.casino.gofish.GoFish;
import io.zipcoder.casino.gofish.GoFishPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Suit;
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
        game.addNPCtoPlayerList(3);
        game.showEveryoneNumOfCard();
    }

    @Test
    public void hasNoCardOnHandTest(){
        GoFish game = new GoFish();
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);
        Assert.assertTrue(game.hasNoCardOnHand(apollo));
    }


    @Test
    public void check4Test(){
        GoFish game = new GoFish();
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);
        apollo.getGoFishHand().addCardToHand(new Card(Suit.DIAMOND, Face.FOUR));
        apollo.getGoFishHand().addCardToHand(new Card(Suit.SPADE, Face.FOUR));
        apollo.getGoFishHand().addCardToHand(new Card(Suit.HEART, Face.FOUR));
        apollo.getGoFishHand().addCardToHand(new Card(Suit.CLUB, Face.FOUR));
        Assert.assertEquals(Face.FOUR, game.check4(apollo.getGoFishHand()));
    }

    @Test
    public void getNextPlayerTest(){
        GoFish game = new GoFish();
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);

        game.addNPCtoPlayerList(3);
        Assert.assertNotNull(game.getNextPlayer(apollo));
        Assert.assertNotEquals(game.getNextPlayer(apollo), apollo);
    }


    @Test
    public void addNPCtoPlayerListTest(){
        GoFish game = new GoFish();
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);

        game.addNPCtoPlayerList(1);
        Assert.assertNotNull(game.getNextPlayer(apollo));
        Assert.assertNotEquals(game.getNextPlayer(apollo), apollo);
    }


    @Test
    public void getGoFishImageTest(){
        GoFish game = new GoFish();
        Assert.assertNotNull(game.goFishImage());
    }
}

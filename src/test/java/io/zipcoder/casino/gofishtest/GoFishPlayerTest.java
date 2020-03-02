package io.zipcoder.casino.gofishtest;

import io.zipcoder.casino.gofish.GoFishHand;
import io.zipcoder.casino.gofish.GoFishPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Hand;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

public class GoFishPlayerTest {

    @Test
    public void constructorTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer gfp = new GoFishPlayer(p);

        String actualName = gfp.getPlayerData().getName();
        String expectedName = "Apollo";

        Assert.assertEquals( expectedName, actualName );
    }

    @Test
    public void getPlayerData(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer gfp = new GoFishPlayer(p);

        String actualName = gfp.getPlayerData().getName();
        int actualId = gfp.getPlayerData().getId();
        String expectedName = "Apollo";
        int expectedId = 10;

        Assert.assertEquals( expectedName, actualName );
        Assert.assertEquals( expectedId, actualId );
    }

    @Test
    public void placeBetTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer gfp = new GoFishPlayer(p);
        gfp.placeBet(60);
        int actualMoney = p.getPlayerFunds();

        Assert.assertEquals(40,actualMoney);
    }

    @Test
    public void payOutTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer gfp = new GoFishPlayer(p);
        gfp.payOut(20);
        int actualMoney = p.getPlayerFunds();

        Assert.assertEquals(120,actualMoney);
    }

    @Test
    public void getGoFishHandTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer gfp = new GoFishPlayer(p);
        Card c = new Card(Suit.CLUB,Face.QUEEN);
        gfp.getGoFishHand().addCardToHand(c);

        Hand actualHand = gfp.getGoFishHand();
        Assert.assertEquals(1,actualHand.getCardsOnHand().size());
        Assert.assertEquals(c,actualHand.getCardsOnHand().get(0));

    }


    @Test
    public void askForFailTest(){
        Player p = new Player(10, "Apollo", 100, false);
        Player p2 = new Player(11, "Athena", 100, false);

        GoFishPlayer apollo = new GoFishPlayer(p);
        GoFishPlayer athena = new GoFishPlayer(p2);

        Card c = new Card(Suit.CLUB,Face.QUEEN);
        apollo.getGoFishHand().addCardToHand(c);

        Assert.assertFalse(athena.askFor(apollo,Face.KING));
    }


    @Test
    public void increaseFishTest(){
        Player p = new Player(10, "Apollo", 100, false);

        GoFishPlayer apollo = new GoFishPlayer(p);
        apollo.obtainFish();
        apollo.obtainFish();

        Assert.assertEquals(2,p.getNumOfFish());
    }

    @Test
    public void getRankingTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);
        Assert.assertEquals(Integer.valueOf(0),apollo.getRanking());
    }

    @Test
    public void getNumOfWinsTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);
        Assert.assertEquals(Integer.valueOf(0),apollo.getNumberOfWins());
    }

    @Test
    public void showUserTheHandTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);
        apollo.showUserTheHand();
    }

    @Test
    public void toStringTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);
        Assert.assertEquals("Apollo",apollo.toString());
    }

    @Test
    public void compareToTest() {
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);
        Player p2 = new Player(11, "Athena", 100, false);
        GoFishPlayer athena = new GoFishPlayer(p2);

        apollo.getGoFishHand().increaseTally();
        athena.getGoFishHand().increaseTally();
        athena.getGoFishHand().increaseTally();


        Assert.assertTrue( apollo.compareTo(athena)>0);
    }

}

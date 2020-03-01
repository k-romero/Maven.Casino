package io.zipcoder.casino.gofishtest;

import io.zipcoder.casino.gofish.GoFish;
import io.zipcoder.casino.gofish.GoFishNPC;
import io.zipcoder.casino.gofish.GoFishPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Suit;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class GoFishNPCTest {

    @Test
    public void TestConstructor(){
        Player p = new Player(10, "Maya", 100, false);
        GoFishPlayer npc = new GoFishNPC(p);
        Assert.assertEquals("Maya",npc.getPlayerData().getName());
    }

    @Test
    public void TestShowFish(){
        Player p = new Player(10, "Maya", 100, false);
        GoFishPlayer npc = new GoFishNPC(p);
        npc.showUserTheFish(new Card(Suit.SPADE,Face.FIVE));
        //make sure nothing happens
    }
    @Test
    public void TestShowHand(){
        Player p = new Player(10, "Maya", 100, false);
        GoFishPlayer npc = new GoFishNPC(p);
        npc.showUserTheHand();
        //make sure nothing happens
    }

    @Test
    public void TestPromptPlayer(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer apollo = new GoFishPlayer(p);
        Player p2 = new Player(11, "Athena", 100, false);
        GoFishPlayer athena = new GoFishPlayer(p2);

        ArrayList<GoFishPlayer> a = new ArrayList<>();
        a.add(apollo);
        a.add(athena);

        Player np = new Player(10, "Maya", 100, false);
        GoFishPlayer npc = new GoFishNPC(np);
        Assert.assertTrue(a.contains(npc.promptForPlayer(a)));
    }

    @Test
    public void TestPromptFace(){
        Player np = new Player(10, "Maya", 100, false);
        GoFishPlayer npc = new GoFishNPC(np);
        npc.getGoFishHand().addCardToHand(new Card(Suit.HEART, Face.ACE));
        npc.getGoFishHand().addCardToHand(new Card(Suit.HEART, Face.TWO));
        npc.getGoFishHand().addCardToHand(new Card(Suit.SPADE, Face.THREE));

        ArrayList<Face> a = new ArrayList<>();
        a.add(Face.ACE);
        a.add(Face.TWO);
        a.add(Face.THREE);
        Assert.assertTrue(a.contains(npc.promptForFace()));
    }

    @Test
    public void toStringTest(){
        Player p = new Player(10, "Apollo", 100, false);
        GoFishPlayer npc = new GoFishNPC(p);
        Assert.assertEquals("Apollo",npc.toString());
    }



}

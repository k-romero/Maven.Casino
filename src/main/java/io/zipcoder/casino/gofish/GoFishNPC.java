package io.zipcoder.casino.gofish;

import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GoFishNPC extends GoFishPlayer {
    Player p;
    Console console = new Console(System.in, System.out);
    public GoFishNPC(Player player) {
        super(player);
        p = player;
    }

    @Override
    public void showUserTheFish(Card fish){
    }

    @Override
    public void showUserTheHand(){

    }

    @Override
    public GoFishPlayer promptForPlayer(ArrayList<GoFishPlayer> players){
        GoFishPlayer randPlayer;
        //regenerate if the random player is self
        do{
            int currentIndex = ThreadLocalRandom.current().nextInt(0,players.size());
            randPlayer = players.get(currentIndex);
        }while( randPlayer.equals(this));
        console.println(p+" chooses to ask "+ randPlayer+".");
        return randPlayer;
    }

    @Override
    public Face promptForFace(){
        ArrayList<Face> faces = this.getGoFishHand().listEveryFaceIHave();
        int whichFace = ThreadLocalRandom.current().nextInt(0, faces.size());
        console.println(p+" asks for a "+faces.get(whichFace).getFaceString()+".");
        return faces.get(whichFace);
    }

    @Override
    public String toString(){
        return this.getPlayerData().getName();
    }
}

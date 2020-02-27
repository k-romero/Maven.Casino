package io.zipcoder.casino.gofish;

import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Face;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GoFishNPC extends GoFishPlayer {
    public GoFishNPC(Player player) {
        super(player);
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
        return randPlayer;
    }

    @Override
    public Face promptForFace(){
        ArrayList<Face> faces = this.getGoFishHand().listEveryFaceIHave();
        int whichFace = ThreadLocalRandom.current().nextInt(0, faces.size());
        return faces.get(whichFace);
    }


}

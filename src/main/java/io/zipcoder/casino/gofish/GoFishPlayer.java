package io.zipcoder.casino.gofish;
import io.zipcoder.casino.player.GamblingPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Face;

import java.util.ArrayList;

import static io.zipcoder.casino.game.CardGame.deal;

public class GoFishPlayer implements GamblingPlayer {
    private GoFishHand myHand;
    private Player player;

    public GoFishPlayer(Player player){
        this.myHand = new GoFishHand();
        this.player=player;
    }

    public GoFishHand getGoFishHand() {
        return myHand;
    }

    public Boolean askFor(GoFishPlayer they, Face f, Deck deck){
        if(they.getGoFishHand().doesMyHandHave(f)){
            they.getGoFishHand().giveCardsTo(f, myHand);
            return true;
        }
        return false;
    }

    public Player getPlayerData() {
        return player;
    }

    public void placeBet(int value){
        player.reducePlayerFunds(value);
    }

    public void payOut(int valueWon){
        player.addPlayerFunds(valueWon);
    }

    public Integer getRanking() {
        return 0;
    }

    public Integer getNumberOfWins() {
        return 0;
    }


    public GoFishPlayer promptForPlayer(ArrayList<GoFishPlayer> players){
        //
        //
        //@TODO
        //
        //
        return new GoFishPlayer(new Player(0,"",0,false));
    }

    public Face promptForFace(){
        //this.getGoFishHand().listEveryFaceIHave();
        //
        //@TODO
        //print all the faces(let user choose)
        //get back a face and return
        return this.getGoFishHand().getCardsOnHand().get(0).getFace();
    }


}

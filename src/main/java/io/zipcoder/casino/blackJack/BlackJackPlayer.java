package io.zipcoder.casino.blackJack;

import io.zipcoder.casino.player.GamblingPlayer;
import io.zipcoder.casino.player.Player;


public class BlackJackPlayer implements GamblingPlayer {
  BlackJackHand hand;
    Player player;



    BlackJackPlayer(Player player){
        this.hand = new BlackJackHand();
        this.player=player;
   }

    public Player getPlayerData() {
        return null;
    }

    public Integer getRanking() {
        return null;
    }


    public Integer getNumberOfWins() {
        return null;
    }


    public void  placeBet (int value){
        if(value>0){
            player.setPlayerFunds(player.getPlayerFunds()-value);
    }
    }
    public void payOut(int valueWon){
        player.setPlayerFunds(player.getPlayerFunds()+valueWon);

    }

}

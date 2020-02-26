package io.zipcoder.casino.gofish;
import io.zipcoder.casino.player.GamblingPlayer;
import io.zipcoder.casino.player.Player;


public class GoFishPlayer implements GamblingPlayer {
    GoFishHand hand;
    Player player;

    public GoFishPlayer(Player player){
        this.hand = new GoFishHand();
        this.player=player;
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

}

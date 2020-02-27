package io.zipcoder.casino.blackjack;
import io.zipcoder.casino.player.GamblingPlayer;
import io.zipcoder.casino.player.Player;


public class BlackJackPlayer implements GamblingPlayer {
    BlackJackHand hand;
    Player player;

    public BlackJackPlayer(Player player){
        this.hand = new BlackJackHand();
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

package io.zipcoder.casino.player;

public interface GamblingPlayer {
    Player getPlayerData();
    void placeBet(int price);
    void payOut(int money);
    Integer getRanking();
    Integer getNumberOfWins();
}


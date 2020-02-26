package io.zipcoder.casino.player;

public interface GamblingPlayer {
    Integer getRanking();
    Integer getNumberOfWins();
    Integer getFund();
    void addFund(int newFund);


}

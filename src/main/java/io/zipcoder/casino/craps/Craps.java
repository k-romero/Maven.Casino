package io.zipcoder.casino.craps;

import io.zipcoder.casino.game.DiceGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.utilities.Console;

import java.util.HashMap;

public class Craps extends DiceGame implements GamblingGame {

    Integer player1Bet = 0;
    CrapsPlayer currentPlayer;
    boolean pointSet;

    Console console = new Console(System.in, System.out);

    int pointValue;
    HashMap<String, Integer> bets;


    public void start(Player player) {




    }

    @Override
    public void end(Player p1) {

    }

    // Give Options for passLine and dontPassLine
    public void bet(){
        Integer bet = console.getIntegerInput("Place your bet!");
        if (bet < 0){
            console.println("No negatives bets!\nYou have " + currentPlayer.getPlayerData().getPlayerFunds() + " in your account.");
            bet();
        }else if (currentPlayer.getPlayerData().getPlayerFunds() < bet){
            console.println("You don't have enough money to place that bet!\nYou have " + currentPlayer.getPlayerData().getPlayerFunds() + " in your account.");
            bet();
        } else {
            player1Bet = bet;
            console.println(currentPlayer.getPlayerData().getName() + " is betting "+ player1Bet + ".");
        }
    }

    public void promptToContinue() {
        console.getStringInput("Press Enter to continue.");
    }

    public boolean continueGameOrEnd(){
        boolean decision = true;
        String result = console.getStringInput("Type '1' to place another bet or '2' to leave the table.");
        if(result.equals("1")){
            decision = true;
        } else if(result.equals("2")){
            decision = false;
        } else {
            console.println("Invalid input!");
            continueGameOrEnd();
        }
        return decision;
    }



    /*public String crapsImage(){
       String result =

       return result;

    }*/






}

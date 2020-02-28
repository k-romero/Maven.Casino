package io.zipcoder.casino.craps;

import io.zipcoder.casino.game.DiceGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.highroller.HighRollerPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;
import java.util.HashMap;

public class Craps extends DiceGame implements GamblingGame {

    Player startOverPlayer;
    CrapsPlayer currentPlayer;
    Integer player1Bet = 0;
    Integer player1ComeBet = 0;
    HashMap<String, Integer> bets;
    Integer lastDiceRoll;
    boolean pointSet;
    Integer pointValue;
    boolean gameInSession;
    ArrayList<Integer> sevenEleven = new ArrayList<Integer>(){{add(7);add(11);}};
    ArrayList<Integer> twoThreeTwelve = new ArrayList<Integer>(){{add(2);add(3);add(12);}};
    ArrayList<Integer> checkPointNumbers = new ArrayList<Integer>(){{add(4);add(5);add(6);add(8);add(9);add(10);}};


    Console console = new Console(System.in, System.out);

    public void start(Player player) {
        this.startOverPlayer = player;
        CrapsPlayer crapsPlayer = new CrapsPlayer(player);
        this.currentPlayer = crapsPlayer;
        this.bets = new HashMap<>();
        gameInSession = true;

        //Main Game Logic
        while(gameInSession){
            if(player.getPlayerFunds() > 0){
                console.println("\n" +
                        " ██████╗██████╗  █████╗ ██████╗ ███████╗    ████████╗ █████╗ ██████╗ ██╗     ███████╗\n" +
                        "██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔════╝    ╚══██╔══╝██╔══██╗██╔══██╗██║     ██╔════╝\n" +
                        "██║     ██████╔╝███████║██████╔╝███████╗       ██║   ███████║██████╔╝██║     █████╗  \n" +
                        "██║     ██╔══██╗██╔══██║██╔═══╝ ╚════██║       ██║   ██╔══██║██╔══██╗██║     ██╔══╝  \n" +
                        "╚██████╗██║  ██║██║  ██║██║     ███████║       ██║   ██║  ██║██████╔╝███████╗███████╗\n" +
                        " ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝     ╚══════╝       ╚═╝   ╚═╝  ╚═╝╚═════╝ ╚══════╝╚══════╝\n" +
                        "                                                                                     \n");
                bet();
                promptToContinue();
                firstBetLocation();
                rollDice();
                evalComeOutRoll();
                while(pointSet){
                    pointSetActions();
                }
            } else break;
        }
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
            currentPlayer.getPlayerData().reducePlayerFunds(bet);
            console.println(currentPlayer.getPlayerData().getName() + " is betting "+ player1Bet + ".");
        }
    }

    public void comeBet(){
        Integer comeBet = console.getIntegerInput("Place your new bet!");
        if (comeBet < 0){
            console.println("No negatives bets!\nYou have " + currentPlayer.getPlayerData().getPlayerFunds() + " in your account.");
            comeBet();
        }else if (currentPlayer.getPlayerData().getPlayerFunds() < comeBet){
            console.println("You don't have enough money to place that bet!\nYou have " + currentPlayer.getPlayerData().getPlayerFunds() + " in your account.");
            comeBet();
        } else {
            player1ComeBet = comeBet;
            console.println(currentPlayer.getPlayerData().getName() + " is betting "+ player1ComeBet + ".");
        }
    }

    public void firstBetLocation(){
       Integer result = console.getIntegerInput("Choose from the following:\n1 = Pass Line\n2 = Dont Pass Line");
        if (result == 1){
            bets.put("pass",player1Bet);
        } else if(result == 2){
            bets.put("dontPass",player1Bet);
        } else {
            console.println("Invalid input!");
            firstBetLocation();
        }
    }

    public void comeBetMenu(){
        Integer result = console.getIntegerInput("Choose from the following:\n1 = Come Bet\n2 = Dont Come Bet");
        if (result == 1){
            bets.put("come",player1ComeBet);
        } else if(result == 2){
            bets.put("dontCome",player1ComeBet);
        } else {
            console.println("Invalid input!");
            comeBet();
        }
    }

    public void rollDice(){
        promptToRollDice();
        lastDiceRoll = currentPlayer.rollDice();
        rollDiceMsg();
    }

    public void evalComeOutRoll(){
        if(sevenEleven.contains(lastDiceRoll)){
            passWinDontPassLose();
        } else if(twoThreeTwelve.contains(lastDiceRoll)){
            passLoseDontPassWin();
        } else setPoint();
    }

    public void evalAfterPointSet(){
        if(lastDiceRoll == 7){
            crapsRolled();
        } else if(lastDiceRoll.equals(pointValue) && checkPointNumbers.contains(lastDiceRoll)){
            evalPassOrDontPassAfterPointHits();
        }  else pointSetActions();
    }

    public void evalPassOrDontPassAfterPointHits(){
        if(bets.containsKey("dontpass")){
            loserLoser();
        } else {
            winnerWinner();
        }
    }

    public void setPoint(){
        pointSet = true;
        pointValue = lastDiceRoll;
        console.println("Point is set at " + pointValue + ".");
    }

    public void pointSetActions(){
        rollDice();
        evalAfterPointSet();
    }

    public void crapsRolled(){
        if(bets.containsKey("pass")){
            console.println("CRAPS!");
            loserLoser();
        } else {
            winnerWinner();
        }
    }

    public void passWinDontPassLose(){
        if(bets.containsKey("pass")){
            winnerWinner();
        }else {
            loserLoser();
        }
    }

    public void passLoseDontPassWin(){
        if(bets.containsKey("dontPass")){
            winnerWinner();
        }else {
            loserLoser();
        }
    }

    public void winnerWinner(){
        payOutMsg();
        currentPlayer.payOut(player1Bet*2);
        continueGameOrEnd();
    }

    public void loserLoser(){
        losePayMsg();
        this.pointSet = false;
        if(currentPlayer.getPlayerData().getPlayerFunds() == 0){
            outOfFundsMsg();
           end(startOverPlayer);
        }
        continueGameOrEnd();
    }


    public void promptToContinue() {
        console.getStringInput("Press Enter to continue.");
    }

    public void promptToRollDice() {
        console.getStringInput("Press Enter to roll dice.");
    }

    public void rollDiceMsg() {
        console.getStringInput("Shooter rolled " + lastDiceRoll + ".");
    }

    public void payOutMsg(){
        console.println("You win " + (player1Bet*2) + ".");
    }

    public void losePayMsg(){
        console.println("You lose " + (player1Bet) + ".");
    }

    public void displayAccountBalance(){
        console.println("You have " + startOverPlayer.getPlayerFunds() + " in your account.");
    }

    public void outOfFundsMsg(){
        console.println("You are broke!!! Get out of here!");
    }

    public void continueGameOrEnd(){
        displayAccountBalance();
        Integer result = console.getIntegerInput("Choose from the following:\n1 = Continue\n2 = Exit");
        if(result == 1){
            start(startOverPlayer);
        }else if(result == 2){
            end(startOverPlayer);
        }

    }


    public void end(Player p1) {
        this.gameInSession = false;
        this.pointSet = false;
    }

}

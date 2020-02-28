package io.zipcoder.casino.highroller;
import io.zipcoder.casino.dealer.DiceDealer;
import io.zipcoder.casino.game.DiceGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.utilities.Console;


public class HighRoller extends DiceGame implements GamblingGame{

    Integer player1Bet = 0;
    HighRollerPlayer currentPlayer;
    boolean gameInSession;
    DiceDealer dealer = new DiceDealer(2);

    Console console = new Console(System.in, System.out);



    public void start(Player p1) {

        HighRollerPlayer playerCurrentlyPlayingTheGame = new HighRollerPlayer(p1);
        this.currentPlayer = playerCurrentlyPlayingTheGame;

        gameInSession = true;
        console.println(highRollerImage());
        console.println(highRollerText());
        while(gameInSession){
            console.println("HighRoller coming in!");
            bet();
            playerCurrentlyPlayingTheGame.getPlayerData().reducePlayerFunds(player1Bet);
            promptToContinue();
            playerCurrentlyPlayingTheGame.rollDice();
            console.println("You rolled a " + playerCurrentlyPlayingTheGame.getDiceValue() + "!");
            promptToContinue();
            dealerRollsDice();
            console.println("The dealer rolled a " + displayDealerRoll() + "!");
            if(playerCurrentlyPlayingTheGame.getDiceValue() == displayDealerRoll()){
                console.println("You tied the dealer! You get back your initial bet of " + player1Bet + "!");
                playerCurrentlyPlayingTheGame.payOut(player1Bet);
            } else if(playerCurrentlyPlayingTheGame.getDiceValue() > displayDealerRoll()){
                console.println("You win! " + (player1Bet * 2) + " has been added to your account!");
                playerCurrentlyPlayingTheGame.payOut(player1Bet * 2);
            } else {
                console.println("You lost " + player1Bet + "! Better luck next time :(");
            }
            if(currentPlayer.getPlayerData().getPlayerFunds() == 0){
                console.println("SECURITY! Get this guy out of here!");
                this.gameInSession = false;
            }else {
                this.gameInSession = continueGameOrEnd();
            }
        }

    }

    public void end(Player p1) {
    }

    public HighRollerPlayer currentPlayerIsHighRoller(){
        return currentPlayer;
    }

    public void bet(){
        Integer bet = console.getIntegerInput("Place your bet!");
        if (bet < 0){
            console.println("No negatives!\nYou have " + currentPlayer.getPlayerData().getPlayerFunds() + " in your account.");
            bet();
        }else if (currentPlayer.getPlayerData().getPlayerFunds() < bet){
            console.println("You don't have enough money to place that bet!\nYou have " + currentPlayer.getPlayerData().getPlayerFunds() + " in your account.");
            bet();
        } else {
            player1Bet = bet;
            console.println("High Roller is betting " + player1Bet + ".");
        }
    }


    public void promptToContinue() {
        console.getStringInput("Press Enter to continue.");
    }

    public void dealerRollsDice(){
        this.dealer.rollDice();
    }

    public Integer displayDealerRoll(){
        return this.dealer.getValue();
    }

    public boolean continueGameOrEnd(){
        boolean decision = true;
        String result = console.getStringInput("Type 'continue' to place another bet or 'end' to leave the table.");
        if(result.equalsIgnoreCase("continue")){
            decision = true;
        } else if(result.equalsIgnoreCase("end")){
            decision = false;
        } else {
            console.println("Invalid input!");
            continueGameOrEnd();
        }
        return decision;
    }

    public String highRollerImage(){
        String result = "       .-------.    ______\n" +
                "      /   o   /|   /\\     \\\n" +
                "     /_______/o|  /o \\  o  \\\n" +
                "     | o     | | /   o\\_____\\\n" +
                "     |   o   |o/ \\o   /o    /\n" +
                "     |     o |/   \\ o/  o  /\n" +
                "     '-------'     \\/____o/";

        return result;
    }

    public String highRollerText(){
        String result = "██╗  ██╗██╗ ██████╗ ██╗  ██╗    ██████╗  ██████╗ ██╗     ██╗     ███████╗██████╗ \n" +
                "██║  ██║██║██╔════╝ ██║  ██║    ██╔══██╗██╔═══██╗██║     ██║     ██╔════╝██╔══██╗\n" +
                "███████║██║██║  ███╗███████║    ██████╔╝██║   ██║██║     ██║     █████╗  ██████╔╝\n" +
                "██╔══██║██║██║   ██║██╔══██║    ██╔══██╗██║   ██║██║     ██║     ██╔══╝  ██╔══██╗\n" +
                "██║  ██║██║╚██████╔╝██║  ██║    ██║  ██║╚██████╔╝███████╗███████╗███████╗██║  ██║\n" +
                "╚═╝  ╚═╝╚═╝ ╚═════╝ ╚═╝  ╚═╝    ╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚══════╝╚══════╝╚═╝  ╚═╝";
        return result;
    }


    //Only used for tests
    public void setPlayer1Bet(Integer player1Bet) {
        this.player1Bet = player1Bet;
    }
}

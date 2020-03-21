package io.zipcoder.casino.blackjack;


import io.zipcoder.casino.dealer.Dealer;
import io.zipcoder.casino.game.CardGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.game.GameControl;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;

import java.util.InputMismatchException;

public class BlackJack extends CardGame implements GamblingGame, GameControl {

    BlackJackPlayer blackJackPlayer;
    Deck deck = new Deck();
    BlackJackDealer blackJackDealer = new BlackJackDealer();
    BlackJackHand dealerHand = new BlackJackHand();
    BlackJackHand playerHand= new BlackJackHand();

    Console console = new Console(System.in,System.out);
    Integer playerValue;
    Integer dealerValue;
    int betPlaced = 0;

    boolean gameInProgress=true;
    boolean gameFinished =false;

    // TODO: FIX LOGIC ABSTRACT CODE
    @Override
    public void start(Player player) {
        this.blackJackPlayer = new BlackJackPlayer(player);

        while (gameInProgress){
            playerHand.getCardsOnHand().clear();
            dealerHand.getCardsOnHand().clear();
            console.print(blackJackImage());
            console.println("Welcome to Black Jack");
            placeBet();
            blackJackPlayer.getPlayerData().reducePlayerFunds(betPlaced);
            console.println( "Your new balance is: $"+ blackJackPlayer.getPlayerData().getPlayerFunds());
            deck.shuffleDeck();
            dealFirstHandOfBlackJack();
            promptPlayerAction(playerHand);
            playerValue = playerHand.calculateHand(playerHand);
            if (checkIfBust(playerValue)){
                playDealerHand(dealerHand);
                console.println("this is the value for dealer:  "+ String.valueOf(dealerValue));
                dealerHand.displayHands();
                console.println("this is the value for player:  "+ String.valueOf(playerValue));
                playerHand.displayHands();
                String wannaPlayAgain = console.getStringInput("You bust! wanna play again yes or No?");
                if (wannaPlayAgain.equals("yes")){
                    start(blackJackPlayer.player);
                } else if (wannaPlayAgain.equals("no")) {
                    break;
                }

            }

            playDealerHand(dealerHand);
            dealerValue=dealerHand.calculateHand(dealerHand);
            if (checkIfBust(dealerValue)){
                playDealerHand(dealerHand);
                console.println("this is the value for dealer:  "+String.valueOf(dealerValue));
                dealerHand.displayHands();
                console.println("this is the value for player:  "+String.valueOf(playerValue));
                playerHand.displayHands();
                blackJackPlayer.payOut(betPlaced*2);
                String wannaPlayAgain = console.getStringInput("Dealer bust!  You won your new balance is:  "+
                        blackJackPlayer.getPlayerData().getPlayerFunds()+"\n"+ "wanna play again yes or No?");
                if (wannaPlayAgain.equals("yes")){
                    start(blackJackPlayer.player);
                } else if (wannaPlayAgain.equals("no")) {
                    break;
                }

            }
            displayGameWinner();
        }


    }


    public void placeBet() {

        Integer bet = console.getIntegerInput("Place your bet!");
        if (bet < 0){
            console.println("No negatives!\nYou have " + blackJackPlayer.getPlayerData().getPlayerFunds() + " in your account.");
            placeBet();
        }else if (blackJackPlayer.getPlayerData().getPlayerFunds() < bet){
            console.println("You don't have enough money to place that bet!\nYou have " + blackJackPlayer.getPlayerData().getPlayerFunds() + " in your account.");
            placeBet();
        }else if(bet == 0){
            console.println("We are not play for fun here. You must bet more than $0!");
            placeBet();
        }
        else {
            betPlaced = bet;
            console.println(blackJackPlayer.getPlayerData().getName() + "bet $" + bet + ".");
        }

    }

    public void dealFirstHandOfBlackJack(){

        console.println("PlayerCard:");
        CardGame.deal(deck, playerHand);
        CardGame.deal(deck, dealerHand);
        CardGame.deal(deck, playerHand);
        playerHand.displayHandsWithSymbol();
        console.println("DealerCard:");
        dealerHand.displayHandsWithSymbol();
        CardGame.deal(deck,dealerHand);
        dealerValue = dealerHand.calculateHand(dealerHand);
        playerValue = playerHand.calculateHand(playerHand);

    }

    public void promptPlayerAction(BlackJackHand playerHand){
        int userInput = this.console.getIntegerInput("To STAND press --> 1\nTo HIT press --> 2");
        if (playerValue <= 21 && userInput == 2) {
            hit(playerHand);
        } else if (userInput == 1) {
            stand(playerHand);
        }
        checkIfBust(playerValue);
    }

    public boolean checkIfBust(int value){
        return value > 21;
    }


    public void playDealerHand (BlackJackHand dealerHand) {
        while (dealerValue < 17) {
            hit(dealerHand);
        }
        checkIfBust(dealerValue);
    }



    public boolean stand(BlackJackHand hand){
        if (hand.equals(dealerHand)) {

            return true;


        } else if (hand.equals(playerHand)){
            return true;


        } return false;
    }

    public void hit(BlackJackHand hand) {
        if (hand.equals(playerHand)){
            CardGame.deal(deck,playerHand);
            playerValue=playerHand.calculateHand(playerHand);
        } else if (hand.equals(dealerHand)){
            CardGame.deal(deck,dealerHand);
            dealerValue=dealerHand.calculateHand(dealerHand);
        }
    }



    //

    public int determineWin (int pval, int dval){
        if((playerValue<dealerValue)){

            String wannaPlayAgain = console.getStringInput("Dealer Won! wanna play again yes or No?");
            if (wannaPlayAgain.equals("yes")){
                start(blackJackPlayer.player);
            } else if (wannaPlayAgain.equals("no")) {
                gameInProgress = gameFinished;
            }
            return 0;

        }  if((playerValue>dealerValue)){

            blackJackPlayer.payOut(betPlaced*2);

            String wannaPlayAgain = console.getStringInput("Horayyyyy! Player Won!!!\n  Player new funds: "
                    +blackJackPlayer.getPlayerData().getPlayerFunds() + "\n"  +" wanna play again yes or No?");
            blackJackPlayer.getPlayerData().increaseWinning(Menu.BLACKJACK);
            if (wannaPlayAgain.equals("yes")){
                start(blackJackPlayer.player);
            } else if (wannaPlayAgain.equals("no")) {
                gameInProgress = gameFinished;

            }
            return 1;

        }  if((playerValue==dealerValue)){
            String wannaPlayAgain = console.getStringInput("TIE!    wanna play again yes or No?");
            if (wannaPlayAgain.equals("yes")){
                blackJackPlayer.payOut(betPlaced);
                start(blackJackPlayer.player);
            } if (wannaPlayAgain.equals("no")){
                gameInProgress=gameFinished;
                blackJackPlayer.payOut(betPlaced);

            }

            return -1;
        }
        return -1;
    }

    public void displayGameWinner(){
        console.println("Player total is: "+String.valueOf(playerValue));
        playerHand.displayHandsWithSymbol();
        console.println("Dealer total is:  "+String.valueOf(dealerValue));
        dealerHand.displayHandsWithSymbol();
        determineWin(playerValue,dealerValue);
    }


    public String blackJackImage (){
        String result="\n" +
                "  ____    _                  _            _                  _      \n" +
                " | __ )  | |   __ _    ___  | | __       | |   __ _    ___  | | __  \n" +
                " |  _ \\  | |  / _` |  / __| | |/ /    _  | |  / _` |  / __| | |/ /  \n" +
                " | |_) | | | | (_| | | (__  |   <    | |_| | | (_| | | (__  |   <   \n" +
                " |____/  |_|  \\__,_|  \\___| |_|\\_\\    \\___/   \\__,_|  \\___| |_|\\_\\  \n" +
                "                                                                    \n";
        return result;
    }





    @Override
    public void end(Player p1) {
        console.getStringInput("bye!");

    }


/*    public  void Stand(BlackJackHand playerHand){
        return playerHand.getSumOfHand()
    }*/


}










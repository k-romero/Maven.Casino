package io.zipcoder.casino.blackJack;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import io.zipcoder.casino.game.CardGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.game.GameControl;
import io.zipcoder.casino.player.GamblingPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.utilities.Console;

public class BlackJack extends CardGame implements GamblingGame, GameControl {
    Integer placeBet=0;
    BlackJackPlayer blackJackPlayer;
    Deck deck;
    BlackJackDealer blackJackDealer;
    BlackJackHand dealerHand;
    BlackJackHand playerHand;
    Console console;


    public BlackJack(BlackJackPlayer blackJackPlayer, Deck deck, BlackJackDealer blackJackDealer, BlackJackHand dealerHand, BlackJackHand playerHand) {
        this.blackJackPlayer = blackJackPlayer;
        this.deck = deck;
        this.blackJackDealer = blackJackDealer;
        this.dealerHand = dealerHand;
        this.playerHand =playerHand;
    }


    @Override
    public void start(Player player) {
        startBlackJack();
        playGame(startBlackJack());
        determineWin(playerHand,dealerHand);

    }
    public int  startBlackJack (){
        placeBet =console.getIntegerInput("Place a bet in order to play");

        blackJackPlayer.placeBet(placeBet);

        if (placeBet>=0) {
            CardGame.deal(deck, playerHand);
            CardGame.deal(deck, dealerHand);
            CardGame.deal(deck, playerHand);
            playerHand.displayHands();
            dealerHand.displayHands();
            CardGame.deal(deck, dealerHand);
            dealerHand.getSumOfHand(true);


        }

        return playerHand.getSumOfHand(true);

    }
    public  void playGame (int sumOfHand) {
        while (playerHand.getSumOfHand(true) < 22) {
            playPlayerHand(playerHand);
            playerHand.displayHands();
            if (checkIfPlayerBust(playerHand.getSumOfHand(true))) {

                String wannaPlayAgain = console.getStringInput("wanna play again yes or No?");
                if (wannaPlayAgain.equals("yes")){
                    startBlackJack();
                } else
                    break;
            }
        }
        playDealerHand(dealerHand);



    }
    public void playPlayerHand( BlackJackHand playerHand ) {
        int userInput = this.console.getIntegerInput("Would you like to hit press (1) or stand press (2)");

        if (playerHand.getSumOfHand(true) <= 21 && userInput == 1) {
            Hit(playerHand);
        } else if (userInput == 2) {
            stand(playerHand);
        }
    }

    public  void playDealerHand (BlackJackHand dealerHand) {
        while (dealerHand.getSumOfHand(true) < 17) {
            if (dealerHand.getSumOfHand(true) > 17) {
                stand(dealerHand);
            } else
                Hit(dealerHand);
        }
    }



    public void stand ( BlackJackHand hand){
        if (hand.equals(dealerHand)) {
            dealerHand.displayHands(
            );
        } else if (hand.equals(playerHand)){
            playerHand.displayHands();
        }

    }
    public void Hit(BlackJackHand playerHand) {
        CardGame.deal(deck,playerHand);
    }


    // 1 is to hit 2 is to stand


    public boolean checkIfPlayerBust (int handSum){
        if (playerHand.getSumOfHand(true)>21){
            return true;

        } else return false;

    }

    public int push() {
        if (playerHand.getSumOfHand(true)==dealerHand.getSumOfHand(true)){
            String wannaPlayAgain = console.getStringInput("wanna play again yes or No?");
            if (wannaPlayAgain.equals("yes")){
                startBlackJack();
            } else
                blackJackPlayer.payOut(placeBet);
            return -1;

        }
        return -1;

    }

    //

   public int determineWin (BlackJackHand playerHand, BlackJackHand dealerHand){
        if(playerHand.getSumOfHand(true)<dealerHand.getSumOfHand(true)){
            return 0;

        }  if(playerHand.getSumOfHand(true)>dealerHand.getSumOfHand(true)){
            return 1;
        }  if(playerHand.getSumOfHand(true)==dealerHand.getSumOfHand(true)){
             return  push();
        }
        return -1;


    }





        @Override
        public void end(Player p1) {


        }


    /*   public  void Stand(BlackJackHand playerHand){
        return playerHand.getSumOfHand()
    }*/


}










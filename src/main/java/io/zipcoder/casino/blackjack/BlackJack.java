package io.zipcoder.casino.blackjack;


import io.zipcoder.casino.game.CardGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.game.GameControl;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.utilities.Console;

public class BlackJack extends CardGame implements GamblingGame, GameControl {
    Player p2;
    BlackJackPlayer blackJackPlayer;
    Deck deck;
    BlackJackDealer blackJackDealer;
    BlackJackHand dealerHand;
    BlackJackHand playerHand;
    Console console = new Console(System.in,System.out);
    Integer playerValue;
    Integer dealerValue;
    int betPlaced = 0;
    boolean gameInProgress=true;
    boolean gameFinished =false;



    public BlackJack(BlackJackPlayer blackJackPlayer, Deck deck, BlackJackDealer blackJackDealer, BlackJackHand dealerHand, BlackJackHand playerHand) {
        this.blackJackPlayer = blackJackPlayer;
        this.deck = deck;
        this.blackJackDealer = blackJackDealer;
        this.dealerHand = dealerHand;
        this.playerHand =playerHand;
    }

    public BlackJack(Player p1){
        this.blackJackPlayer = new BlackJackPlayer(p1);
    }


    @Override
    public void start(Player player) {

        while (gameInProgress){
            playerHand.getCardsOnHand().clear();
            dealerHand.getCardsOnHand().clear();
            console.println("Welcome to Black Jack....");
            placeBet();
            blackJackPlayer.getPlayerData().reducePlayerFunds(betPlaced);
            deck.shuffleDeck();
            dealFirstSetOfBlackJet();
            playPlayerHand(playerHand);
            playerValue= playerHand.calculateCards(playerHand);
            if (!checkIfBustAndWantsToPlayAgain(playerValue)&&!stand(playerHand)){
                 gameInProgress=false;
                 break;
            } else
                playDealerHand(dealerHand);
                console.println("this is the value for dealer:  "+String.valueOf(dealerValue));
                dealerHand.displayHands();
                console.println("this is the value for player:  "+String.valueOf(playerValue));
                playerHand.displayHands();
                determineWin(playerValue,dealerValue);
        }


    }

    public void placeBet() {

        betPlaced = console.getIntegerInput("Place a bet in order to play");
        int playerFunds = blackJackPlayer.getPlayerData().getPlayerFunds();
        if (playerFunds < betPlaced) {
            console.println("Sorry you do not have enough funds!");
           placeBet();
        } else if (betPlaced < 0) {
            console.println("PLEASE DONT GIVE ME NEGATIVE MONEY!");
            placeBet();

        } else {
            console.println(" you have placed a bet in the amount of:  " + betPlaced);
        }

    }

    public void dealFirstSetOfBlackJet (){

            console.println("PlayerCard:");
            CardGame.deal(deck, playerHand);
            CardGame.deal(deck, playerHand);
            playerHand.displayHands();

            CardGame.deal(deck, dealerHand);
            console.println("DealerCard:");
            dealerHand.displayHands();
            CardGame.deal(deck,dealerHand);
            dealerValue=dealerHand.calculateCards(dealerHand);
            playerValue =playerHand.calculateCards(playerHand);
    }

    public void playPlayerHand( BlackJackHand playerHand ) {

        int userInput = this.console.getIntegerInput("Would you like to hit press (1) or stand press (2)");
        if (playerValue <= 21 && userInput == 1) {
            hit(playerHand);
        } else if (userInput == 2) {
            stand(playerHand);
        }
    }
    public boolean checkIfBustAndWantsToPlayAgain (int value){
        if ((playerValue >21)){
            String wannaPlayAgain = console.getStringInput("You bust! wanna play again yes or no?");
            if (wannaPlayAgain.equals("yes")){
                playerHand.getCardsOnHand().clear();
                dealerHand.getCardsOnHand().clear();
                start(p2);

                return true;
            }
            else if((dealerValue>21)) {
               determineWin(dealerValue,playerValue);

        } else if ( wannaPlayAgain.equals("no")) {
                gameInProgress = gameFinished;
                return gameFinished;
            }
        }
        return gameFinished;
    }


    public  void playDealerHand (BlackJackHand dealerHand) {
        while (dealerValue < 17) {
            if ((dealerValue > 17) && (dealerValue<21)){
                stand(dealerHand);
            } else
                hit(dealerHand);
        }
    }


    public boolean  stand ( BlackJackHand hand){
        if (hand.equals(dealerHand)) {
            return true;
        } else if (hand.equals(playerHand)){
        return true;
        }
        return false;
    }

    public void hit(BlackJackHand hand) {
        if (hand.equals(playerHand)){
            CardGame.deal(deck,playerHand);
            playerValue=playerHand.calculateCards(playerHand);
        } else if (hand.equals(dealerHand)){
            CardGame.deal(deck,dealerHand);
        dealerValue=dealerHand.calculateCards(dealerHand);
        }
    }

   public int determineWin (int pval, int dval){
        if((playerValue<dealerValue)){

            String wannaPlayAgain = console.getStringInput("Dealer Won! wanna play again yes or no?");
            if (wannaPlayAgain.equals("yes")){
                start(p2);
            } else if (wannaPlayAgain.equals("no")) {
                gameInProgress = gameFinished;
            }
            return 0;

        }  if((playerValue>dealerValue)){

           blackJackPlayer.payOut(betPlaced*2);
           String wannaPlayAgain = console.getStringInput("Player Won!   wanna play again yes or no?");
           if (wannaPlayAgain.equals("yes")){
               start(p2);
           } else if (wannaPlayAgain.equals("no")) {
               gameInProgress = gameFinished;

           }
           return 1;

        }  if((playerValue==dealerValue)){
           String wannaPlayAgain = console.getStringInput("TIE!    wanna play again yes or no?");
           if (wannaPlayAgain.equals("yes")){
               blackJackPlayer.payOut(betPlaced);
               start(p2);
           } if (wannaPlayAgain.equals("no")){
               gameInProgress=gameFinished;
               blackJackPlayer.payOut(betPlaced);

           }

           return -1;
        }
        return -1;


    }


    @Override
    public void end(Player p1) {
      console.getStringInput("bye!");

    }


/*    public  void Stand(BlackJackHand playerHand){
        return playerHand.getcalculateCards()
    }*/


}










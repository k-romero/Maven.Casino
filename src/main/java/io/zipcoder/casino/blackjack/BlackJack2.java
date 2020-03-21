package io.zipcoder.casino.blackjack;

import io.zipcoder.casino.game.CardGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.game.GameControl;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Hand;
import io.zipcoder.casino.utilities.Console;

public class BlackJack2 extends CardGame implements GamblingGame, GameControl {

   private int betPlaced;
   private Console console = new Console(System.in, System.out);
   private BlackJackPlayer guest;
   private Deck deck = new Deck();
   private BlackJackHand dealerHand = new BlackJackHand();
   private BlackJackHand playerHand= new BlackJackHand();
   private boolean gameInSession = true;
   private int playerHandValue;
   private int dealerHandValue;

    @Override
    public void start(Player p1) {
        while (gameInSession) {
            guest = new BlackJackPlayer(p1);
            console.print(blackJackImage());
            placeBet();
            deck.shuffleDeck();
            dealFirstHandOfBlackJack();
            promptPlayerForAction();
            playDealerHand();
            determineWinner(playerHandValue, dealerHandValue);
            playerFundsAvailable();
        }
    }

    public void playerFundsAvailable(){
        if(guest.getPlayerData().getPlayerFunds() == 0){
            console.println("SECURITY! Get this guy out of here!");
            this.gameInSession = false;
        }else {
            this.gameInSession = continueGameOrEnd();
        }
    }

    public void placeBet() {

        int bet = console.getIntegerInput("Place your bet!");
        if (bet < 0){
            console.println("No negatives!\nYou have " + guest.getPlayerData().getPlayerFunds() + " in your account.");
            placeBet();
        }else if (guest.getPlayerData().getPlayerFunds() < bet){
            console.println("You don't have enough money to place that bet!\nYou have " + guest.getPlayerData().getPlayerFunds() + " in your account.");
            placeBet();
        }else if(bet == 0){
            console.println("We are not play for fun here. You must bet more than $0!");
            placeBet();
        }
        else {
            betPlaced = bet;
            guest.getPlayerData().reducePlayerFunds(bet);
            console.println(guest.getPlayerData().getName() + "bet $" + bet + ". Your current funds are $" + guest.getPlayerData().getPlayerFunds());
        }


    }

    public void dealFirstHandOfBlackJack(){
        console.println("Player Cards: ");
        CardGame.deal(deck, playerHand);
        CardGame.deal(deck, dealerHand);
        CardGame.deal(deck, playerHand);
        console.println("Your total is " + getHandValue(playerHand));
        playerHandValue = getHandValue(playerHand);
        playerHand.displayHandsWithSymbol();
        console.println("Dealer Cards:");
        dealerHand.displayHandsWithSymbol();
        CardGame.deal(deck,dealerHand);
        dealerHandValue = getHandValue(dealerHand);
    }

    public int getHandValue(Hand hand){
        int handValue = 0;
        Boolean containsAce = false;
        for (Card c : hand.getCardsOnHand()){
            int value = c.getFace().getAltValue();
            handValue += value;
        }
        for(Card c : hand.getCardsOnHand()){
            if(c.getFace() == Face.ACE){
                containsAce = true;
            }
            if(containsAce && handValue < 12){
                handValue += 10;
            }
        }
        return handValue;
    }

    public void promptPlayerForAction(){

        int userInput = this.console.getIntegerInput("To STAND press --> 1\nTo HIT press --> 2");
        if (playerHandValue < 21 && userInput == 2) {
            hit(playerHand);
            checkForPlayerBJ(playerHandValue);
            playerHandValue = getHandValue(playerHand);
        } else if (userInput == 1) {
            stand(playerHand);
        }

    }

    public void playDealerHand(){
        dealerHand.displayHandsWithSymbol();
        while(dealerHandValue < 17 && checkHandForBust(dealerHand) ){
            hit(dealerHand);
            dealerHandValue = getHandValue(dealerHand);
        }
    }

    public void hit(BlackJackHand hand){
        CardGame.deal(deck, hand);
    }

    public void stand(BlackJackHand hand) {
        hand.displayHandsWithSymbol();
    }

    public boolean checkHandForBust(BlackJackHand hand){
        return getHandValue(hand) > 21;
    }

    public boolean checkForPlayerBJ(int value){
        if(value == 21) {
            guest.payOut(betPlaced * 2);
            console.print("BlackJack!! You won! Your current funds are $" + guest.getPlayerData().getPlayerFunds());
            continueGameOrEnd();
        }
        return value == 21;
    }

    public int determineWinner(int pval, int dval){
        if(playerWins(pval, dval)){
            return 1;
        } else if(dealerWins(pval, dval)) {
            return -1;
        } else {
            guest.payOut(betPlaced);
            console.print("It's a Push. $" + betPlaced + " will be put back into your funds.\n" + "Your current funds are $" + guest.getPlayerData().getPlayerFunds());
            continueGameOrEnd();
            return 0;
        }
    }

    public boolean playerWins(int p, int d){
        if(p > d){
            guest.payOut(betPlaced);
            console.print("Congrats you won $" + betPlaced + ". Your current funds are $" + guest.getPlayerData().getPlayerFunds());
            continueGameOrEnd();
            return true;
        }
        return false;
    }

    public boolean dealerWins(int p, int d){
        if(p < d){
            console.print("Sorry pal you lost!. Your current funds are $" + guest.getPlayerData().getPlayerFunds());
            continueGameOrEnd();
            return true;
        }
        return false;
    }


    public boolean continueGameOrEnd(){
        boolean decision = true;
        String result = console.getStringInput("Type 'continue' to place another bet or 'end' to leave the table.");
        if(result.equalsIgnoreCase("continue")){
            playerHand.getCardsOnHand().clear();
            dealerHand.getCardsOnHand().clear();
            decision = true;
        } else if(result.equalsIgnoreCase("end")){
            decision = false;
        } else {
            console.println("Invalid input!");
            return continueGameOrEnd();
        }
        return decision;
    }


    public String blackJackImage (){
        String result="\n" +
                "  ____    _                  _            _                  _      \n" +
                " | __ )  | |   __ _    ___  | | __       | |   __ _    ___  | | __  \n" +
                " |  _ \\  | |  / _` |  / __| | |/ /    _  | |  / _` |  / __| | |/ /  \n" +
                " | |_) | | | | (_| | | (__  |   <    | |_| | | (_| | | (__  |   <   \n" +
                " |____/  |_|  \\__,_|  \\___| |_|\\_\\    \\___/   \\__,_|  \\___| |_|\\_\\  \n" +
                "                                                                    \n\n\n" +
                "Welcome to BlackJack!";
        return result;
    }

    @Override
    public void end(Player p1) {
    }
}

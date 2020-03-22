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
            printFinalResults();
            determineWinner(playerHand, dealerHand);
            continueGameOrEnd();
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
            console.println(guest.getPlayerData().getName() + "bet $" + bet + ". Your current funds are $" + guest.getPlayerData().getPlayerFunds() + "!");
        }
    }

    // Deals Initial Hands to Players
    public void dealFirstHandOfBlackJack(){
        console.println("Player Hand: ");
        CardGame.deal(deck, playerHand);
        CardGame.deal(deck, dealerHand);
        CardGame.deal(deck, playerHand);
        console.println("Your total is " + getHandValue(playerHand));
        playerHandValue = getHandValue(playerHand);
        playerHand.displayHandsWithSymbol();
        console.println("\nDealer Hand: ");
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
        if(checkHandForBust(playerHand)){
            printLosingMessage();
        } else {
            while (!checkHandForBust(playerHand)) {
                int userInput = this.console.getIntegerInput("\nTo STAND press (1)\nTo HIT press (2)");
                if (playerHandValue < 21 && userInput == 2) {
                    hit(playerHand);
                    playerHandValue = getHandValue(playerHand);
                    console.print("\nPlayer total is: " + playerHandValue + "\n");
                } else if (userInput == 1) {
                    console.print("\nPlayer total is: " + playerHandValue + "\n");
                    stand(playerHand);
                    break;
                }
            }
        }
    }

    public void playDealerHand() {
        console.print("\nDealer total is: " + dealerHandValue + "\n");
        dealerHand.displayHandsWithSymbol();
        console.print("\n");
        while (dealerHandValue < 17) {
            if(checkHandForBust(dealerHand)){
                guest.payOut(betPlaced);
                printWinningMessage();
                break;
            }
            hit(dealerHand);
            console.print("\nDealer total is: " + dealerHandValue + "\n");
            dealerHand.displayHandsWithSymbol();
            dealerHandValue = getHandValue(dealerHand);
        }
    }

    public void hit(BlackJackHand hand){
        CardGame.deal(deck, hand);
        hand.displayHandsWithSymbol();
        console.print("\n");
    }

    public void stand(BlackJackHand hand){
        hand.displayHandsWithSymbol();
        console.print("\n");
    }

    public boolean checkHandForBust(BlackJackHand hand){
        return getHandValue(hand) > 21;
    }

    public boolean checkForBlackJack(BlackJackHand hand){
        if(getHandValue(hand) == 21) {
            return true;
        }
        return false;
    }

    public void determineWinner(BlackJackHand player, BlackJackHand dealer){
        determineBlackJack(player, dealer);

        if (getHandValue(player) > getHandValue(dealer)) {
            printWinningMessage();
        } else if (getHandValue(player) < getHandValue(dealer)) {
            printLosingMessage();
        } else {
            printPushMessage();
        }

    }

    public void determineBlackJack(BlackJackHand player, BlackJackHand dealer){
        if(checkForBlackJack(player)){
            printPlayerBlackJackMessage();
        }
        if(checkForBlackJack(dealer)){
            printDealerHasBlackJack();
        }
    }

    public void printFinalResults(){
        console.print("\n**********************************************************\n");
        console.print("Player total is: " + playerHandValue + "\nPlayer Hand: ");
        playerHand.displayHandsWithSymbol();
        console.print("\n**********************************************************\n");
        console.print("Dealer total is: " + dealerHandValue + "\nDealer Hand: ");
        dealerHand.displayHandsWithSymbol();
        console.print("\n**********************************************************\n");
    }


    public void printPlayerBlackJackMessage(){
        guest.payOut(betPlaced * 3);
        console.print("\nBLACKJACK! You won $" + betPlaced * 3 + "You now have $" + guest.getPlayerData().getPlayerFunds() + " in your account!\n");
    }

    public void printDealerHasBlackJack(){
        console.print("\nDealer has BlackJack! You now have $" + guest.getPlayerData().getPlayerFunds() + " in your account!\n");
    }

    public void printWinningMessage(){
        guest.payOut(betPlaced * 2);
        console.print("\nCongrats you won $"+ betPlaced + " "+ "You now have $" + guest.getPlayerData().getPlayerFunds() + " in your account!\n");
    }

    public void printLosingMessage(){
        console.print("\nSorry pal you lost. You still have $" + guest.getPlayerData().getPlayerFunds() + " in your account!\n");
    }

    public void printPushMessage() {
        guest.payOut(betPlaced);
        console.print("\nIt's a Push. $" + betPlaced + " will be put back into your funds.\n" + "Your current funds are $" + guest.getPlayerData().getPlayerFunds() + "!\n");
    }

    public boolean continueGameOrEnd(){
        boolean decision;
        String result = console.getStringInput("\nType 'continue' to place another bet or 'end' to leave the table.\n");
        if(result.equalsIgnoreCase("continue")){
            playerHand.getCardsOnHand().clear();
            dealerHand.getCardsOnHand().clear();
            decision = true;
        } else if(result.equalsIgnoreCase("end")){
            decision = false;
        } else {
            console.println("\nInvalid input!\n");
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
                "Welcome to BlackJack!\n";
        return result;
    }

    @Override
    public void end(Player p1) {
    }

}

package io.zipcoder.casino.gofish;

import io.zipcoder.casino.game.CardGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class GoFish extends CardGame implements GamblingGame {

    ArrayList<GoFishPlayer> players = new ArrayList<>();
    Deck mainDeck = new Deck();
    Console console = new Console(System.in, System.out);

    @Override
    public void start(Player p1) {
        console.println("Welcome to Gofish!");

        //set up deck and main player
        mainDeck.shuffleDeck();
        GoFishPlayer you = new GoFishPlayer(p1);
        players.add(you);

        //get number of npc
        Integer numOfNPC = getNumOfNPC(p1);

        //generate npc
        addNPCtoPlayerList(numOfNPC);

        //initial deal
        initialDeal(numOfNPC);

        //show player their hand
        players.get(0).showUserTheHand();

        //randomly select starting player
        GoFishPlayer currentPlayer = randomSelectStartingPlayer(numOfNPC);

        //start turn loop
        while(mainDeck.checkSize()!=0 && continueTurn(currentPlayer)){
            currentPlayer = getNextPlayer(currentPlayer);
        }

        //game ends
        if(mainDeck.checkSize()==0)
            console.println("No card in the pool!");
        console.println("The game has ended.");
        promptForNextOrEnd(console);

        //print result
        printGameResult(you);


        /*
                while(game not end){
                    //step1: rotated thru every player
                             turn(currentPlayer);
                    //step2: switch to next player
                             currentP = nextP
                }
                turn(){
                    step0:  check for 4 of a kind <-(check if current player ran out of card, than terminate)
                    step1:  choose a face to ask
                    step2:  if(ask success)
                            { get card with another turn }
                            else(ask fail)
                            {
                                goFish/deal a card to player
                                if(card had same face asked)
                                  { player get a turn }
                                else if (check4){
                                    dump card
                                    player get a turn
                                }
                            }
                      }
        */

    }

    public Boolean continueTurn(GoFishPlayer currentP){
        showEveryoneNumOfCard();
        if(currentP.getGoFishHand().getNumOfCards() == 0){
            console.println(currentP+" has no cards on hand.");
            return false;
        }

        Face face = check4(currentP.getGoFishHand());

        if(face != null){//match found!!!!!
            console.println("Ooh! "+currentP+ "'s got 4-of-a-kind of "+face.getFaceString()+"!");
            promptForNextOrEnd(console);
            currentP.getGoFishHand().increaseTally();
            currentP.getGoFishHand().discardCardsWith(face);

            console.println(currentP+"'s score is now "+currentP.getGoFishHand().getTallyMatches()+".");
            console.println(currentP+" put the set on the table.");
            console.println("Now "+currentP+" has "+currentP.getGoFishHand().getNumOfCards()+" cards on hand.");
            promptForNextOrEnd(console);

            if(currentP.getGoFishHand().getNumOfCards()==0){
                console.println(currentP+" has no cards on hand.");
                return false;
            }
        }

        console.println("Now it's "+currentP+"'s turn to ask.");
        currentP.showUserTheHand();
        face = currentP.promptForFace();
        GoFishPlayer askedPlayer = currentP.promptForPlayer(players);
        console.println(currentP+" is asking "+askedPlayer+": Do you have any "+ face.getFaceString()+"?");
        promptForNextOrEnd(console);

        //if(ask success)
        if(currentP.askFor(askedPlayer, face)){
            currentP.showUserTheHand();
            return continueTurn(currentP);
        }else{
            //r says go fish
            console.println(askedPlayer+" says GO FISH.");
            promptForNextOrEnd(console);
            Card fish = deal(mainDeck, currentP.getGoFishHand());

            if(fish != null) {
                console.println(currentP + " draw a card from the pool.");
                currentP.showUserTheFish(fish);
                currentP.showUserTheHand();
                promptForNextOrEnd(console);
                if(fish.getFace().equals(face)){
                    console.println(currentP + " drew a " +face.getFaceString()+"! "+currentP+" can now ask again!");
                    promptForNextOrEnd(console);
                    return continueTurn(currentP);
                }else{
                    if(check4(currentP.getGoFishHand()) != null)
                        return continueTurn(currentP);
                }

            }

        }
        return true;
    }

    public Integer getNumOfNPC(Player p){
        Integer numOfNPC = promptForNumberNPC("Please enter number of NPCs: ");
        console.println(p+" and "+numOfNPC+" others are playing.");
        promptForNextOrEnd(console);
        return numOfNPC;
    }

    public GoFishPlayer randomSelectStartingPlayer(int numOfNPC){
        int currentIndex = ThreadLocalRandom.current().nextInt(0,numOfNPC+1);
        console.println("A random player will be selected to start the game.");
        promptForNextOrEnd(console);
        console.println(players.get(currentIndex)+" is selected.");
        promptForNextOrEnd(console);

        return players.get(currentIndex);
    }

    public void addNPCtoPlayerList(int numOfNPC){
        for(int i=1; i<=numOfNPC; i++) {
            players.add(new GoFishNPC(new Player(-1,"NPC"+i,0,false)));
        }
    }

    public int getStartingCard(int numOfNPC){
        if(numOfNPC>1)
            return 5;
        else
            return 7;
    }

    public void initialDeal(int numOfNPC){
        int startingCardNum = getStartingCard(numOfNPC);
        for(int i=0; i<1+numOfNPC; i++) {
            for(int j=0; j<startingCardNum; j++)
                deal(mainDeck, players.get(i).getGoFishHand());
        }
        console.println(startingCardNum+ " cards are dealt to each player.");
        promptForNextOrEnd(console);
    }

    public void showEveryoneNumOfCard(){
        console.println("=====Current Table=====");
        for(GoFishPlayer gp :players){
            console.println(gp+" has "+gp.getGoFishHand().getNumOfCards()+ " cards, Matched sets:" + gp.getGoFishHand().getTallyMatches());
        }
        console.println("=======================");

    }


    public Integer promptForNumberNPC(String msg){
        while(true) {
            int x = console.getIntegerInputWithoutln(msg);
            if(x == 0)
                console.println("You can't play without NPC! Please try again.");
            else if(x < 0)
                console.println("You can't play with negative number of people! Please try again.");
            else if (x > 5)
                console.println("That's too many people! Please try a number less then 5.");
            else
                return x;
        }
    }

    public static String promptForNextOrEnd(Console c){
        while(true) {
            try {
                return c.getStringInputWithoutln("/");
            }catch (Exception e){
                c.println("Something went wrong! Please try again.");
            }
        }
    }


    public Face check4(GoFishHand hand){

        for(Face f: Face.values()){
            if(hand.howManyDoIHave(f)==4){
                return f;
            }
        }
        return null;
    }

    public GoFishPlayer getNextPlayer(GoFishPlayer p){
        int index = players.indexOf(p);
        if(index==players.size()-1){
            return players.get(0);
        }
        return players.get(index+1);
    }


    public void printGameResult(GoFishPlayer you){
        ArrayList<GoFishPlayer> tempP = new ArrayList<>(players);
        Collections.sort(tempP);

        console.println("== RESULT ==");
        for (GoFishPlayer p :tempP) {
            console.println(p + "   " + p.getGoFishHand().getTallyMatches());
        }

        if(tempP.get(0).equals(you) &&
                tempP.get(1).getGoFishHand().getTallyMatches() < you.getGoFishHand().getTallyMatches()){
            console.println("You won!");
            console.println("You received a pack of Goldfish as reward!");
            you.getPlayerData().addAFish();
        }else{
            console.println("Too bad....Try harder!");
        }
        console.println("Sending you back to lobby...come back next time!");
        promptForNextOrEnd(console);
    }

    @Override
    public void end(Player p1) {
    }



}

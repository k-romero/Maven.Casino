package io.zipcoder.casino.gofish;

import io.zipcoder.casino.game.CardGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GoFish extends CardGame implements GamblingGame {


    ArrayList<GoFishPlayer> players = new ArrayList<>();
    Deck mainDeck = new Deck();
    Console console = new Console(System.in, System.out);

    @Override
    public void start(Player p1) {
        console.println("Welcome to Gofish");
        Integer numOfNPC = promptForNumber("Please enter number of NPCs: ");
        console.println(p1.getName()+" and "+numOfNPC+" others are playing.");
        promptForNextOrEnd();

        //===============Set up===============
        mainDeck.shuffleDeck();
        GoFishPlayer you = new GoFishPlayer(p1);
        players.add(you);

        //generate npc
        for(int i=1; i<=numOfNPC; i++) {
            players.add(new GoFishNPC(new Player(-1,"NPC"+i,0,false)));
        }

        //initial deal
        int startingCardNum = 7;

        if(numOfNPC>1)
            startingCardNum = 5;

        for(int i=0; i<1+numOfNPC; i++) {
            for(int j=0; j<startingCardNum; j++)
                deal(mainDeck, players.get(i).getGoFishHand());
        }

        console.println(startingCardNum+ " cards are dealt to each player.");
        promptForNextOrEnd();
        players.get(0).showUserTheHand();
        console.println("A random player will be selected to start the game.");
        promptForNextOrEnd();

        //randomly select starting player
        int currentIndex = ThreadLocalRandom.current().nextInt(0,numOfNPC+1);
        GoFishPlayer currentPlayer = players.get(currentIndex);

        while(mainDeck.checkSize()!=0 && continueTurn(currentPlayer)){
            currentPlayer = getNextPlayer(currentPlayer);
            console.println("Now it's "+currentPlayer+"'s turn to ask.");
        }
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
                        }
                  }
        */

    }

    public Boolean continueTurn(GoFishPlayer currentP){
        showEveryoneNumOfCard();
        Face face = check4(currentP.getGoFishHand());

        if(face != null){//match found!!!!!
            console.println("Ooh! "+currentP.getPlayerData().getName()+ " found 4 of a kind of "+face.getFaceString()+"!");
            promptForNextOrEnd();
            currentP.getGoFishHand().increaseTally();
            currentP.getGoFishHand().discardCardsWith(face);

            console.println(currentP.getPlayerData().getName()+"'s score is now "+currentP.getGoFishHand().getTallyMatches()+".");
            console.println(currentP.getPlayerData().getName()+" put the set on the table.");
            console.println("Now "+currentP.getPlayerData().getName()+" has "+currentP.getGoFishHand().getNumOfCards()+" cards on hand.");
            promptForNextOrEnd();

            if(currentP.getGoFishHand().getNumOfCards()==0){
                console.println(currentP.getPlayerData().getName()+" has no cards on hand.");
                return false;
            }
        }

        face = currentP.promptForFace();
        GoFishPlayer askedPlayer = currentP.promptForPlayer(players);
        console.print(currentP.getPlayerData().getName()+" is asking "+askedPlayer.getPlayerData().getName()+": Do you have any "+ face.getFaceString()+"s?");
        promptForNextOrEnd();

        //if(ask success)
        if(currentP.askFor(askedPlayer, face)){
            return continueTurn(currentP);
        }else{
            //r says go fish
            console.println(askedPlayer.getPlayerData().getName()+" said GO FISH.");
            promptForNextOrEnd();
            Card fish = deal(mainDeck, currentP.getGoFishHand());

            if(fish != null) {
                console.println(currentP + " drew a card from the pool.");
                promptForNextOrEnd();
            }
            currentP.showUserTheHand();
            promptForNextOrEnd();
            if(fish.getFace().equals(face)){
                console.println(currentP + " drew a " +face.getFaceString()+"! "+currentP+" can now ask again!");
                return continueTurn(currentP);
            }
        }
        return true;
    }

    public void showEveryoneNumOfCard(){
        console.println("=====Current number of cards=====");
        for(GoFishPlayer gp :players){
            console.println(gp.getPlayerData().getName()+" has "+gp.getGoFishHand().getNumOfCards()+ " cards.");
        }
        console.println("=================================");

    }

    public Integer promptForNumber(String msg){
        while(true) {
            try {
                return console.getIntegerInputWithoutln(msg);
            }catch (Exception e){
                console.println("Invalid Input! Please try again.");
            }
        }
    }

    public String promptForNextOrEnd(){
        while(true) {
            try {
                return console.getStringInputWithoutln("///////Press Enter to continue or say BYE to abort.///////");
            }catch (Exception e){
                console.println("Something went wrong! Please try again.");
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

    @Override
    public void end(Player p1) {
    }



}

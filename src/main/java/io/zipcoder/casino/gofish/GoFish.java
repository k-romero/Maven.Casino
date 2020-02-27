package io.zipcoder.casino.gofish;

import io.zipcoder.casino.game.CardGame;
import io.zipcoder.casino.game.GamblingGame;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Face;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GoFish extends CardGame implements GamblingGame {

    private int numOfNPC = 1;
    ArrayList<GoFishPlayer> players = new ArrayList<>();
    Deck mainDeck = new Deck();

    @Override
    public void start(Player p1) {

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
        if(numOfNPC>2)
            startingCardNum = 5;

        for(int i=0; i<startingCardNum; i++) {
            deal(mainDeck, you.getGoFishHand());
            for(int j=0; j<numOfNPC; j++)
                deal(mainDeck, players.get(i).getGoFishHand());
        }

        //randomly select starting player
        int currentIndex = ThreadLocalRandom.current().nextInt(0,numOfNPC+1);
        GoFishPlayer currentPlayer = players.get(currentIndex);

        while(mainDeck.checkSize()!=0 && turn(currentPlayer)){
            currentPlayer = getNextPlayer(currentPlayer);
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

    public Boolean turn(GoFishPlayer currentP){
        Face face = check4(currentP.getGoFishHand());

        if(face != null){//match found!!!!!
            currentP.getGoFishHand().increaseTally();
            currentP.getGoFishHand().discardCardsWith(face);
            if(currentP.getGoFishHand().getNumOfCards()==0){
                return false;
            }
        }

        face = currentP.promptForFace();
        GoFishPlayer askedPlayer = currentP.promptForPlayer(players);

        //if(ask success)
        if(currentP.askFor(askedPlayer, face, mainDeck)){
            turn(currentP);
        }else{
            //r says go fish
            Card fish = deal(mainDeck, currentP.getGoFishHand());
            if(fish.getFace().equals(face)){
                turn(currentP);
            }
        }
        return true;
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

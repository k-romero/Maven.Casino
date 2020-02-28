package io.zipcoder.casino.gofish;
import io.zipcoder.casino.player.FriendlyPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.utilities.Console;
import java.util.ArrayList;
import java.util.InputMismatchException;

import static io.zipcoder.casino.gofish.GoFish.promptForNextOrEnd;


public class GoFishPlayer implements FriendlyPlayer, Comparable<GoFishPlayer> {
    private GoFishHand myHand;
    private Player player;
    Console console = new Console(System.in, System.out);

    public GoFishPlayer(Player player){
        this.myHand = new GoFishHand();
        this.player=player;
    }

    public GoFishHand getGoFishHand() {
        return myHand;
    }

    public Boolean askFor(GoFishPlayer they, Face f){
        if(they.getGoFishHand().doesMyHandHave(f)){
            console.println("Apparently "+they.getPlayerData().getName()+" has some "+f.getFaceString()+".");
            //they.getGoFishHand().displayHands();
            console.println("So "+ they.getPlayerData().getName()+" handed over "+ they.getGoFishHand().howManyDoIHave(f)+" cards to "+this.getPlayerData().getName());
            they.getGoFishHand().giveCardsTo(f, myHand);
            promptForNextOrEnd(console);
            return true;
        }
        return false;
    }

    public Player getPlayerData() {
        return player;
    }

    @Override
    public void obtainFish() {
        getPlayerData().addAFish();
    }

    public void placeBet(int value){
        player.reducePlayerFunds(value);
    }

    public void payOut(int valueWon){
        player.addPlayerFunds(valueWon);
    }

    public Integer getRanking() {
        return 0;
    }

    public Integer getNumberOfWins() {
        return 0;
    }

    public void showUserTheFish(Card fish){
        console.println("It is a "+fish.toStringWithSymbol());
        promptForNextOrEnd(console);
    }


    public void showUserTheHand(){
        this.getGoFishHand().sortCardsOnHand();
        console.println("Cards on your hand are: ");
        this.getGoFishHand().displayHandsWithSymbol();
    }

    public GoFishPlayer promptForPlayer(ArrayList<GoFishPlayer> players){
        int index;
        if(players.size() == 2){
            return players.get(1);
        }
        while(true) {
            console.println("-- Please select a player to ask --");
            for(int i=1; i<players.size(); i++){
                console.println( " > ("+ i +") " + players.get(i) );
            }
            try {
                index = console.getIntegerInputWithoutln("I will select: ");
                if(index>players.size()){
                    throw new InputMismatchException();
                }
                break;
            }catch (Exception e){
                console.println("Invalid Input! Please try again.");
            }
        }
        return players.get(index);
    }

//    public Face stringToFace(String input){
//        for(Face f: Face.values()){
//            if(f.getFaceString().equalsIgnoreCase(input))
//                return f;
//        }
//        return null;
//    }

    public Face promptForFace(){
        int val;
        ArrayList<Face> fs;
        while(true) {
            console.println("-- Please select a face to ask for --");
            fs = this.getGoFishHand().listEveryFaceIHave();
            for(int i=0; i<fs.size(); i++){
                console.println( " > ("+ i +") "+ fs.get(i).getFaceString() );
            }
            try {
                val = console.getIntegerInputWithoutln("I will select: ");
                if(val>fs.size()){
                    throw new InputMismatchException();
                }
                break;
            }catch (Exception e){
                console.println("Invalid Input! Please try again.");
            }
        }
        return fs.get(val);
    }

    @Override
    public String toString(){
        return this.getPlayerData().getName();
    }

    @Override
    public int compareTo(GoFishPlayer p) {
        return p.getGoFishHand().getTallyMatches()-getGoFishHand().getTallyMatches();
    }
}

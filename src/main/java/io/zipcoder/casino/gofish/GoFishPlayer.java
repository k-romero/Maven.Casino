package io.zipcoder.casino.gofish;
import io.zipcoder.casino.player.GamblingPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.utilities.Console;

import java.util.ArrayList;
import java.util.InputMismatchException;

import static io.zipcoder.casino.game.CardGame.deal;

public class GoFishPlayer implements GamblingPlayer {
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
            console.print(they.getPlayerData().getName()+" has "+ they.getGoFishHand().howManyDoIHave(f)+" "+f.getFaceString()+".");
            they.getGoFishHand().giveCardsTo(f, myHand);
            return true;
        }
        return false;
    }

    public Player getPlayerData() {
        return player;
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

    public void showUserTheHand(){
        console.print("Cards on your hand are: ");
        this.getGoFishHand().displayHands();
    }

    public GoFishPlayer promptForPlayer(ArrayList<GoFishPlayer> players){
        int index;
        while(true) {
            console.println("[ Please select a player to ask ]");
            for(int i=1; i<players.size(); i++){
                console.println( " > ("+ i +") " + players.get(i).getPlayerData().getName() );
            }
            try {
                index = console.getIntegerInput("I will select: ");
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
            console.println("[ Please select a face to ask for ]");
            fs = this.getGoFishHand().listEveryFaceIHave();
            for(int i=0; i<fs.size(); i++){
                console.println( " > ("+ i +") "+ fs.get(i).getFaceString() );
            }
            try {
                val = console.getIntegerInput("I will select: ");
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


}

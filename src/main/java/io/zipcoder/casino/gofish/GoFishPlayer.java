package io.zipcoder.casino.gofish;
import io.zipcoder.casino.player.FriendlyPlayer;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;

import java.util.ArrayList;

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
            console.println("So "+ they+" handed over "+ they.getGoFishHand().howManyDoIHave(f)+" cards to "+this);
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
        getPlayerData().increaseWinning(Menu.GOFISH);
    }

    public void placeBet(int value){
        player.reducePlayerFunds(value);
    }

    public void payOut(int valueWon){
        player.addPlayerFunds(valueWon);
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
        console.println("-- Please select a player to ask --");
        for(int i=1; i<players.size(); i++){
            console.println( " > ("+ i +") " + players.get(i) );
        }
        while(true) {
            index = console.getIntegerInputWithoutln("I will select: ");
            if(index>=players.size()){
                console.println("Invalid Input! Please try again.");
            }else{
                break;
            }
        }
        return players.get(index);
    }

    public Face promptForFace(){
        String input = "";
        ArrayList<Face> fs;
        Face f;
        while(true) {
            fs = this.getGoFishHand().listEveryFaceIHave();
            console.println("-- Please select a rank to ask for --");
            for (Face face : fs) {
                console.println(" >  " + face.getFaceString());
            }
            input = console.getStringInputWithoutln("I will select: ");
            f = Face.toFace(input);
            if(input.equalsIgnoreCase("bye") || input.equalsIgnoreCase("end")) {
                return null;
            }else if(f == null || !fs.contains(f)){
                console.println("Input not recognized! Try again");
            }else{
                break;
            }
        }
        return f;
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

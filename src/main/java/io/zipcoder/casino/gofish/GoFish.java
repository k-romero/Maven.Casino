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
        printWelcomeGoFIsh();
        mainDeck.shuffleDeck();
        GoFishPlayer you = new GoFishPlayer(p1);
        players.add(you);
        int numOfNPC = getNumOfNPC(p1);
        addNPCtoPlayerList(numOfNPC);
        initialDeal(numOfNPC);
        players.get(0).showUserTheHand();
        GoFishPlayer currentPlayer = randomSelectStartingPlayer(numOfNPC);

        while(mainDeck.checkSize()!=0 && continueTurn(currentPlayer)){
            currentPlayer = getNextPlayer(currentPlayer);
        }

        if(mainDeck.checkSize()==0)
            console.println("No card in the pool!");
        console.println("The game has ended.");
        promptForNextOrEnd(console);

        printGameResult(you);

    }

    private void printWelcomeGoFIsh() {
        console.println(goFishImage());
        console.println("Welcome to Go fish!");
    }

    private Integer getNumOfNPC(Player p){
        Integer numOfNPC = promptForNumberNPC();
        console.println(p+" and "+numOfNPC+" others are playing.");
        promptForNextOrEnd(console);
        return numOfNPC;
    }

    private GoFishPlayer randomSelectStartingPlayer(int numOfNPC){
        int currentIndex = ThreadLocalRandom.current().nextInt(0,numOfNPC+1);
        console.println("A random player will be selected to start the game.");
        promptForNextOrEnd(console);
        console.println(players.get(currentIndex)+" is selected.");
        promptForNextOrEnd(console);

        return players.get(currentIndex);
    }

    private void addNPCtoPlayerList(int numOfNPC){
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

    private void initialDeal(int numOfNPC){
        int startingCardNum = getStartingCard(numOfNPC);
        for(int i=0; i<1+numOfNPC; i++) {
            for(int j=0; j<startingCardNum; j++)
                deal(mainDeck, players.get(i).getGoFishHand());
        }
        console.println(startingCardNum+ " cards are dealt to each player.");
        promptForNextOrEnd(console);
    }

    private Boolean continueTurn(GoFishPlayer currentP){

        showEveryoneNumOfCard();

        if(currentP.getGoFishHand().getNumOfCards() == 0){
            console.println(currentP+" has no cards on hand.");
            return false;
        }

        Face face = check4(currentP.getGoFishHand());

        if(face != null){
            printFind4Msg(currentP,face);
            if(currentP.getGoFishHand().getNumOfCards()==0){
                console.println(currentP+" has no cards on hand.");
                return false;
            }
        }

        face = askForFace(currentP);

        GoFishPlayer askedPlayer = currentP.promptForPlayer(players);
        console.println(currentP+" is asking "+askedPlayer+": Do you have any "+ face.getFaceString()+"?");
        promptForNextOrEnd(console);

        if(currentP.askFor(askedPlayer, face)){
            currentP.showUserTheHand();
            return continueTurn(currentP);
        }else{

            Card fish = saysGoFish( askedPlayer, currentP);

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

    private void printFind4Msg(GoFishPlayer currentP, Face face){
        console.println("Ooh! "+currentP+ "'s got 4-of-a-kind of "+face.getFaceString()+"!");
        promptForNextOrEnd(console);
        currentP.getGoFishHand().increaseTally();
        currentP.getGoFishHand().discardCardsWith(face);

        console.println(currentP+"'s score is now "+currentP.getGoFishHand().getTallyMatches()+".");
        console.println(currentP+" put the set on the table.");
        console.println("Now "+currentP+" has "+currentP.getGoFishHand().getNumOfCards()+" cards on hand.");
        promptForNextOrEnd(console);
    }

    private Face askForFace(GoFishPlayer currentP){
        currentP.showUserTheHand();
        console.println("Now it's "+currentP+"'s turn to ask.");
        return currentP.promptForFace();
    }

    public Card saysGoFish(GoFishPlayer askedPlayer, GoFishPlayer currentPlayer){
        console.println(askedPlayer+" says GO FISH.");
        promptForNextOrEnd(console);
        return deal(mainDeck, currentPlayer.getGoFishHand());
    }

    private void showEveryoneNumOfCard(){
        console.println("=====Current Table=====");
        for(GoFishPlayer gp :players){
            console.println(gp+" has "+gp.getGoFishHand().getNumOfCards()+ " cards, Matched sets:" + gp.getGoFishHand().getTallyMatches());
        }
        console.println("=======================");

    }

    private Integer promptForNumberNPC(){
        while(true) {
            int x = console.getIntegerInputWithoutln("How many people do you want to play with?: ");
            if(x == 0)
                console.println("You can't play alone! Please try again.");
            else if(x < 0)
                console.println("You really need some friends.");
            else if (x > 5)
                console.println("That's too many people! Please try a number less then 5.");
            else
                return x;
        }
    }

    public static String promptForNextOrEnd(Console c){
        while(true) {
            String input = c.getStringInputWithoutln("/");
            return input;
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

    private void printGameResult(GoFishPlayer you){
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
            console.println("Too bad... you lost, try harder!");
        }
        console.println("You will be sent back to the lobby, come back next time!");
        promptForNextOrEnd(console);
    }

    public String goFishImage(){
        String result =
                "                              .######....#######..########.####..######..##.....##\n" +
                "  o        /`·. ¸          .##....##..##.....##.##........##..##....##.##.....##\n" +
                " .        /¸...¸`:·        .##........##.....##.##........##..##.......##.....##\n" +
                "  o    ¸.·´  ¸   `·.¸.·´)  .##...####.##.....##.######....##...######..#########\n" +
                "   . : © ):´;      ¸  {     .##....##..##.....##.##........##........##.##.....##\n" +
                "      `·.¸ `·  ¸.·´\\`·¸)    .##....##..##.....##.##........##..##....##.##.....##\n" +
                "          `\\\\´´\\¸.·´          ..######....#######..##.......####..######..##.....##\n";
        return result;
    }


    @Override
    public void end(Player p1) {
    }



}

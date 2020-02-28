package io.zipcoder.casino.utilities;

import io.zipcoder.casino.blackjack.BlackJack;
import io.zipcoder.casino.craps.Craps;
import io.zipcoder.casino.gofish.GoFish;
import io.zipcoder.casino.highroller.HighRoller;
import io.zipcoder.casino.player.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Menu {
    public static Console c = new Console(System.in, System.out);

    public void run(){
        Player you = setUpUserAsPlayer();
        ArrayList<String[]> selections = setUpselections();

        while(true) {
            c.println("");
//            c.println("       //========================//");
            c.println("             Welcome to the   ");
//            c.println("       //========================//");
            c.println(
                    " ██████╗ █████╗ ███████╗██╗███╗   ██╗ ██████╗ \n" +
                    "██╔════╝██╔══██╗██╔════╝██║████╗  ██║██╔═══██╗\n" +
                    "██║     ███████║███████╗██║██╔██╗ ██║██║   ██║\n" +
                    "██║     ██╔══██║╚════██║██║██║╚██╗██║██║   ██║\n" +
                    "╚██████╗██║  ██║███████║██║██║ ╚████║╚██████╔╝\n" +
                    " ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝ ╚═════╝ ");
            c.println("\nSelect from the following:");
            c.println(setUpMenu(selections));


            String input = "";
            try {
                input = c.getStringInputWithoutln("Enter Your selection: ");
                boolean b =false;
                String currentGame  = "";
                for (String[] s : selections) {
                    if (s[1].equalsIgnoreCase(input)) {
                        b = true;
                        currentGame = s[0];
                    }
                }
                if(!b) throw new InputMismatchException();

                jumpTo(input,you,selections);
            } catch (Exception e) {
                c.println("Your input is invalid, please try again.");
            }
        }

    }

    public ArrayList<String[]> setUpselections(){
        ArrayList<String[]> selectionNames = new ArrayList<>();
        selectionNames.add( new String[]{"Player Info", "p"} );
        selectionNames.add( new String[]{"High Roller", "hr"} );
        selectionNames.add( new String[]{"Craps", "c"});
        selectionNames.add( new String[]{"Go Fish", "gf"});
        selectionNames.add( new String[]{"Black Jack", "bj"});
        return selectionNames;
    }

    public String setUpMenu(ArrayList<String[]> selections){
        String menu = "";
        for(String[] s: selections){
            menu += "("+s[1]+") "+s[0]+"\n";
        }
        return menu;
    }

    public void printPlayerInfo(Player p){
        c.println("");
        c.println("//=========================//");
        c.println("      Player Information     ");
        c.println("//=========================//");
        c.println("");

        String info = "";
        info += "Name: "+p.getName()+"\n";
        info += "Balance: "+p.getPlayerFunds()+"\n";
        c.println(info);
        c.pressEnterToCount();
    }

    public void jumpTo(String s, Player p, ArrayList<String[]> place){
        if(s.equalsIgnoreCase(place.get(0)[1])) {
            printPlayerInfo(p);
        }else if(s.equalsIgnoreCase(place.get(1)[1])){
            highRollerQuickStart(p);
        }else if(s.equalsIgnoreCase(place.get(2)[1])){
            crapsQuickStart(p);
        }else if(s.equalsIgnoreCase(place.get(3)[1])){
            goFishQuickStart(p);
        }else if(s.equalsIgnoreCase(place.get(4)[1])){
            blackjackQuickStart(p);
        }
    }

    public Player setUpUserAsPlayer(){
        String name = c.getStringInputWithoutln("Please enter your name to enter casino: ");
        Player p = new Player(name);
        //c.println("Hi "+ name +", you have "+p.getPlayerFunds()+" to start with.");
        return p;
    }

    public static void highRollerQuickStart(Player p){
        HighRoller game = new HighRoller();
        game.start(p);
    }
    public static void crapsQuickStart(Player p){
        Craps game = new Craps();
        game.start(p);
    }
    public static void goFishQuickStart(Player p){
        GoFish game = new GoFish();
        game.start(p);
    }
    public static void blackjackQuickStart(Player p){
        BlackJack game = new BlackJack(p);
        game.start(p);
    }
}

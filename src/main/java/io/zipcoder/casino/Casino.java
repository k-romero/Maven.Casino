package io.zipcoder.casino;

import io.zipcoder.casino.blackjack.BlackJack;
import io.zipcoder.casino.craps.Craps;
import io.zipcoder.casino.gofish.GoFish;
import io.zipcoder.casino.highroller.HighRoller;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.Menu;
import java.util.HashMap;
import java.util.Map;

public class Casino {

    public static Console c = new Console(System.in, System.out);
    public static Map<Menu, Runnable> selection = new HashMap<>();

    public static void main(String[] args) {
        Casino casino = new Casino();

        //======================================
        // This is a temporary set up!!!!!!!!!!!
        // This can bypass the login/enter-name session
        // login session is still under construction
            Player p1 = new Player(10, "JJ",1000,true );
            casino.setUpSelection(p1);
            casino.printLogo();
            casino.printMainMenu();
            casino.run(p1);
        //======================================

    }

    public void setUpSelection(Player p){
        selection.put(Menu.PLAYERINFO, ()-> showPlayerInfo(p));
        selection.put(Menu.HIGHROLLER, ()-> startHighRollerSession(p));
        selection.put(Menu.CRAPS, ()-> startCrapsSession(p));
        selection.put(Menu.GOFISH, ()-> startGoFishSession(p));
        selection.put(Menu.BLACKJACK, ()-> startBlackJackSession(p));
    }


    public void printLogo(){
        String logo =
                "            == Welcome to the ==\n" +
                " ██████╗ █████╗ ███████╗██╗███╗   ██╗ ██████╗ \n" +
                "██╔════╝██╔══██╗██╔════╝██║████╗  ██║██╔═══██╗\n" +
                "██║     ███████║███████╗██║██╔██╗ ██║██║   ██║\n" +
                "██║     ██╔══██║╚════██║██║██║╚██╗██║██║   ██║\n" +
                "╚██████╗██║  ██║███████║██║██║ ╚████║╚██████╔╝\n" +
                " ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝ ╚═════╝ ";
        c.println("");
        c.println(logo);
    }

    public void printMainMenu(){
        String menuString = "";
        for (Menu m:Menu.values()) {
            menuString += " > " + m.getName()+"\n";
        }
        c.println("");
        c.println(menuString);
    }

    public void run(Player p ){
        String input = "";
        while(true) {
                input = c.getStringInputWithoutln("Enter Your selection: ");
                Menu m = getMenuItemByInput(input);
                if (selection.get(m) == null)
                    c.println("Your input is invalid, please try again.");
                else{
                    selection.get(m).run();
                    printLogo();
                    printMainMenu();
                }
        }
    }

    public Menu getMenuItemByInput(String input){
        input = input.toLowerCase();
        for (Menu m:selection.keySet()) {
            if(m.getShortKey().contains(input) || m.getName().equalsIgnoreCase(input))
                return m;
        }
        return null;
    }

    public static void showPlayerInfo(Player p) {
        String info = "";
        info += "- Name: "+p.getName()+"\n";
        info += "- Balance: "+p.getPlayerFunds()+"\n";
        info += "- Number of Fish: "+p.getNumOfFish()+"\n";

        c.println("====================\n");
        c.println(info);
        c.println("====================\n");
        c.pressEnterToCount();

    }

    public static void startHighRollerSession(Player p) {
        HighRoller game = new HighRoller();
        game.start(p);
    }
    public static void startCrapsSession(Player p) {
        Craps game = new Craps();
        game.start(p);
    }
    public static void startGoFishSession(Player p){
        GoFish game = new GoFish();
        game.start(p);
    }
    public static void startBlackJackSession(Player p){
        BlackJack game = new BlackJack();
        game.start(p);
    }
}
package io.zipcoder.casino;

import io.zipcoder.casino.blackjack.BlackJack;
import io.zipcoder.casino.craps.Craps;
import io.zipcoder.casino.gofish.GoFish;
import io.zipcoder.casino.highroller.HighRoller;
import io.zipcoder.casino.player.Player;
import io.zipcoder.casino.tools.Color;
import io.zipcoder.casino.utilities.Console;
import io.zipcoder.casino.utilities.LoginData;
import io.zipcoder.casino.utilities.Menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Casino {

    public static Console c = new Console(System.in, System.out);
    public static Map<Menu, Runnable> selection = new HashMap<>();
    public static LoginData login = new LoginData();
    public static ArrayList<Player> players = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Casino casino = new Casino();
        casino.loadPlayerDataBase();
        Player p1 = null;
        while(p1 == null) {
            p1 = casino.startUpUserLogIn();
        }
        casino.setUpSelection(p1);
        casino.printLogo();
        casino.printMainMenu();
        casino.run(p1);

    }

    public void printLogInInfo(){
        String info = "     > (1) Log In        |\n"
                    +"|     > (2) Sign Up       ";
        printWindow(Color.ANSI_CYAN,"|","=","    Log in or Sign Up    ", info,true);
    }

    public Player startUpUserLogIn(){
        printLogInInfo();
        Player p;
        while(true) {
            int choice = c.getIntegerInputWithoutln("I would like to... ");
            if (choice == 1) {
                p = startSignInSession();
                return p;
            } else if (choice == 2) {
                p = startSignUpSession();
                return p;
            } else {
                c.println(Color.ANSI_RED+"Input invalid!"+Color.ANSI_RESET);
            }
        }
    }

    public static void logInSuccessful(Player p ){
        String center = Console.getPaddedString("Welcome Back! "+p.getName(), ' ',30);
        printWindow(Color.ANSI_GREEN,"|","=","      Login Successful!      ", center,true);
        c.pressEnterToCount("Press Enter to Go to the Lobby.");
    }

    public Player startSignInSession(){
        while(true) {
            int id = c.getIntegerInputWithoutln("Enter your ID: ");
            for (Player p : players) {
                if (p.getId() == id) {
                    logInSuccessful(p);
                    return p;
                }
            }
            c.println(Color.ANSI_RED+"Such id does not exist in the system! Try again."+Color.ANSI_RESET);
            return null;
        }
    }

    public void loadPlayerDataBase() throws IOException {
        login.jsonLoadData();
        players = login.getPlayerDataBase();
    }


    public void signUpComplete(String name, int id, int balance){


        String title = "      Registration Complete     ";
        printWindow(Color.ANSI_GREEN,"|","=",title,null,false);
        c.print(Color.ANSI_GREEN);
        c.println(String.format("|         Welcome! %-12s  |",name));
        c.println(String.format("|         Your ID is %-10d  |",id));
        c.println(String.format("|  Your initial balance is $%-4d |", balance));
        c.println(stringOfALine("|","=",title.length()));
        c.println(Color.ANSI_RESET);
        c.pressEnterToCount("Press enter and you will be sent to the casino lobby.");
    }

    public Player startSignUpSession(){
        printWindow(Color.ANSI_CYAN,"|","=","    Create an Account    ",null,true);
        String input = c.getStringInputWithoutln("Enter your name here: ");
        int id = players.size()+1;
        int balance = 100;
        Player newPlayer = new Player(id, input, balance,false);
        login.addPlayerData(newPlayer);
        try {
            login.jsonWrite();
        } catch (IOException e) {
            e.printStackTrace();
        }
        signUpComplete(input, id,balance);
        return newPlayer;
    }


    public void setUpSelection(Player p){
        selection.put(Menu.PLAYERINFO, ()-> showPlayerInfo(p));
        selection.put(Menu.HIGHROLLER, ()-> startHighRollerSession(p));
        selection.put(Menu.CRAPS, ()-> startCrapsSession(p));
        selection.put(Menu.BLACKJACK, ()-> startBlackJackSession(p));
        selection.put(Menu.GOFISH, ()-> startGoFishSession(p));
    }


    public void printLogo(){
        String line = Color.ANSI_YELLOW+ "◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆*．◆"+Color.ANSI_RESET;
        String subTitle = Color.ANSI_DARKYELLOW + "\n                 == Welcome to the ==";
        String logo = Color.ANSI_YELLOW+
                "     ██████╗ █████╗ ███████╗██╗███╗   ██╗ ██████╗ \n" +
                "    ██╔════╝██╔══██╗██╔════╝██║████╗  ██║██╔═══██╗\n" +
                "    ██║     ███████║███████╗██║██╔██╗ ██║██║   ██║\n" +
                "    ██║     ██╔══██║╚════██║██║██║╚██╗██║██║   ██║\n" +
                "    ╚██████╗██║  ██║███████║██║██║ ╚████║╚██████╔╝\n" +
                "     ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝ ╚═════╝ \n";

        c.println("");
        c.println(line);
        c.println(subTitle);
        c.println(logo);
        c.println(line);
    }

    public void printMainMenu(){
        String menuString = "";
        for (Menu m:Menu.values()) {
            menuString += " > " + m.getName()+"\n";
        }
        c.println("");
        c.println(menuString);
    }

    public void run(Player p ) throws IOException {
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
                    login.updatePlayerData(p);
                    login.jsonWrite();
                    c.println(Color.ANSI_GREEN+"Your progress is saved."+Color.ANSI_RESET);
                }
        }
    }

    public Menu getMenuItemByInput(String input){
        input = input.toLowerCase();
        for (Menu m:selection.keySet()) {
            if(m.getShortKeys().contains(input) || m.getName().equalsIgnoreCase(input))
                return m;
        }
        return null;
    }

    public static void showPlayerInfo(Player p) {
        String info = "";
        info += String.format("  ID: %-16s  |\n",p.getId());
        info += String.format("|  Name: %-14s  |\n",p.getName());
        info += String.format("|  Balance: $%-10s  |\n",p.getPlayerFunds());
        info += String.format("|  High Roller Wins: %-2s  |\n",p.getNumOfWin(Menu.HIGHROLLER));
        info += String.format("|  Craps Wins: %-8s  |\n",p.getNumOfWin(Menu.CRAPS));
        info += String.format("|  Black Jack Wins: %-3s  |\n",p.getNumOfWin(Menu.BLACKJACK));
        info += String.format("|  Number of Fish: %-4s  ",p.getNumOfWin(Menu.GOFISH));

        printWindow(Color.ANSI_GREEN,"|","=","    Your Player Info    ",info,true);

        c.pressEnterToCount("press enter to go back");
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


    public static String stringOfALine(String sideSymbol, String lineSymbol, int width){
        StringBuilder theLine = new StringBuilder(sideSymbol);
        for (int i = 0; i < width ; i++) {
            theLine.append(lineSymbol);
        }
        theLine.append(sideSymbol);
        return theLine.toString();
    }

    public static void printWindow(String color, String sideSymbol, String lineSymbol, String title, String info, Boolean nextLine){
        c.println(color);
        c.println(stringOfALine(sideSymbol,lineSymbol,title.length()));
        c.println(sideSymbol+title+sideSymbol);
        c.println(stringOfALine(sideSymbol,lineSymbol,title.length()));
        if( info != null ){
            c.println(sideSymbol+info+sideSymbol);
            c.println(stringOfALine(sideSymbol,lineSymbol,title.length()));
        }
        if(nextLine){
            c.println("");
        }
        c.print(Color.ANSI_RESET);
    }
}
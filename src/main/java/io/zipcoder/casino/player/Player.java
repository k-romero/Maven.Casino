package io.zipcoder.casino.player;
import io.zipcoder.casino.utilities.Menu;

import java.util.HashMap;
import java.util.Map;

public class Player {

    private int id;
    private String name;
    private int playerFunds;
    private boolean isDrunk;
    private int drinks = 0;
    private Map<Menu,Integer> winnings;

    public Player(){};

    public Player(String name){
        this(0,name,1000,false);
    }

    public Player(int id, String name, int playerFunds, boolean isDrunk) {
        this.id = id;
        this.name = name;
        this.playerFunds = playerFunds;
        this.isDrunk = isDrunk;
        this.winnings = new HashMap<>();

        for (int i = 1; i < Menu.values().length; i++) {
            winnings.put(Menu.values()[i], 0);
        }

    }

    public Player(int id, String name, int playerFunds, boolean isDrunk, Map<Menu,Integer> winnings) {
        this(id, name, playerFunds, isDrunk);
        for (int i = 1; i < Menu.values().length; i++) {
            this.winnings.put(Menu.values()[i], winnings.get(Menu.values()[i]));
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerFunds() {
        return playerFunds;
    }

    public void setPlayerFunds(int playerFunds) {
        this.playerFunds = playerFunds;
    }

    public void addPlayerFunds(int amountWantsToAdd){ this.playerFunds += amountWantsToAdd; }

    public void reducePlayerFunds(int amountWantsToReduce){ this.playerFunds -= amountWantsToReduce; }

    public void increaseWinning(Menu game){
        winnings.put(game, winnings.get(game)+1);
    }

    public int getNumOfWin(Menu game){
        return winnings.get(game);
    }

    public boolean isDrunk() {
        if(this.drinks > 3){
            this.isDrunk = true;
        }
        return isDrunk;
    }

    public void setDrunk(boolean drunk) {
        isDrunk = drunk;
    }

    public void giveDrink(){
        this.drinks += 1;
    }

    public int getDrinks() {
        return drinks;
    }

    public Map<Menu,Integer> getWinnings(){ return winnings; }

    @Override
    public String toString(){
        return this.getName();
    }
}

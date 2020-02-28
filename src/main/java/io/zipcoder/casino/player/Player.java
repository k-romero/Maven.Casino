package io.zipcoder.casino.player;

public class Player {

    int id;
    String name;
    int playerFunds;
    boolean isDrunk;
    int drinks = 0;

    public Player(int id, String name, int playerFunds, boolean isDrunk) {
        this.id = id;
        this.name = name;
        this.playerFunds = playerFunds;
        this.isDrunk = isDrunk;
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

    @Override
    public String toString(){
        return this.getName();
    }
}

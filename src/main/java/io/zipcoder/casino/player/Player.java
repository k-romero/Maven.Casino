package io.zipcoder.casino.player;

public class Player {

    private int id;
    private String name;
    private int playerFunds;
    private boolean isDrunk;
    private int drinks = 0;

    private int numOfFish = 0;

    public Player(String name){
        this.name = name;
        id = -1;
        playerFunds = 1000;
        isDrunk = false;
    }

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

    public int getNumOfFish() {
        return numOfFish;
    }

    public void addAFish() {
        this.numOfFish++;
    }

    @Override
    public String toString(){
        return this.getName();
    }
}

package io.zipcoder.casino.dealer;

public abstract class Dealer {
    String name;
    Boolean isAngry;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAngry() {
        return isAngry;
    }

    public void setAngry(Boolean angry) {
        isAngry = angry;
    }

}

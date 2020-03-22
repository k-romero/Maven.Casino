package io.zipcoder.casino.utilities;

import java.util.Arrays;
import java.util.List;

public enum Menu {
    PLAYERINFO("Player Information", "1", "p", "info", "player"),
    HIGHROLLER("High Roller", "2", "h", "hr", "high","roller"),
    CRAPS("Craps", "3", "c", "cr", "crap"),
    BLACKJACK("Black Jack", "4", "b", "bj", "black", "jack"),
    BLACKJACK2("Black Jack2", "6", "b2", "bj2", "black2", "jack2"),
    GOFISH("Go Fish","5","g", "gf", "fish", "go");

    private String name;
    private String[] shortKeys;

    Menu(String name, String... shortKeys) {
        this.name = name;
        this.shortKeys = shortKeys;
    }

    public String getName() {
        return name;
    }

    public List<String> getShortKeys() {
        return Arrays.asList(shortKeys);
    }


}

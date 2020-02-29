package io.zipcoder.casino.utilities;

import java.util.Arrays;
import java.util.List;

public enum Menu {
    PLAYERINFO("Player Information", "1", "p", "info", "player"),
    HIGHROLLER("High Roller", "2", "h", "hr", "high"),
    CRAPS("Craps", "3", "c", "cr", "crap"),
    GOFISH("Go Fish","4", "gf", "fish", "go"),
    BLACKJACK("Black Jack", "5", "bj", "black", "jack");

    private String name;
    private String[] shortKeys;

    Menu(String name, String... shortKeys) {
        this.name = name;
        this.shortKeys = shortKeys;
    }

    public String getName() {
        return name;
    }

    public List<String> getShortKey() {
        return Arrays.asList(shortKeys);
    }
}

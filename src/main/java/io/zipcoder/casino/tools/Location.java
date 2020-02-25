package io.zipcoder.casino.tools;

public enum Location {
    FOUR    ("4", 4),
    FIVE    ("5",5),
    SIX     ("6",6),
    EIGHT   ("8",7),
    NINE    ("9",9),
    TEN     ("10",10),
    PASS    ("pass",1),
    NOTTPASS("notPass",-1),
    COME    ("come",13),
    NOTCOME ("notCome",-13);

    private final String locationName;
    private final int locationNumber;

    public String getLocationString() {
        return locationName;
    }

    public int getLocationNumber() {
        return locationNumber;
    }

    Location(String locationName, int locationNumber){
        this.locationName = locationName;
        this.locationNumber = locationNumber;
    }
}

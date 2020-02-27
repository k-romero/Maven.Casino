package io.zipcoder.casino.tools;

public enum Face {

    ACE("A",1, 11),
    TWO("2",2, 2),
    THREE("3",3, 3),
    FOUR("4",4, 4),
    FIVE("5",5, 5),
    SIX("6", 6, 6),
    SEVEN("7",7, 7),
    EIGHT("8",8, 8),
    NINE("9",9, 9),
    TEN("10", 10, 10),
    JACK("J", 11, 11),
    QUEEN("Q", 12, 12),
    KING("K", 13, 13);

    String faceString;
    int value;
    int altValue;

    private Face(String f, int v, int av){
        this.faceString = f;
        this.value = v;
        this.altValue = av;
    }

    public String getFaceString(){
        return faceString;
    }

    public int getValue(){
        return this.value;
    }

    public int getAltValue(){
        return this.altValue;
    }

}

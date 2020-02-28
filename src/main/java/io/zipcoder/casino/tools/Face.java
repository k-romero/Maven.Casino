package io.zipcoder.casino.tools;

public enum Face {

    ACE("A",1, 11, "ACE"),
    TWO("2",2, 2, "TWO"),
    THREE("3",3, 3, "THREE"),
    FOUR("4",4, 4, "FOUR"),
    FIVE("5",5, 5, "FIVE"),
    SIX("6", 6, 6, "SIX"),
    SEVEN("7",7, 7, "SEVEN"),
    EIGHT("8",8, 8, "EIGHT"),
    NINE("9",9, 9, "NINE"),
    TEN("10", 10, 10, "TEN"),
    JACK("J", 11, 11, "JACK"),
    QUEEN("Q", 12, 12, "QUEEN"),
    KING("K", 13, 13, "KING");

    String faceString;
    int value;
    int altValue;
    String nameString;

    Face(String f, int v, int av, String n){
        this.faceString = f;
        this.value = v;
        this.altValue = av;
        this.nameString = n;
    }

    public String getFaceString(){
        return faceString;
    }

    public int getValue(){
        return this.value;
    }

    public String getNameString(){ return nameString; }

    public static Face toFace(String s){
        for( Face f :Face.values()  ){
            if(s.equalsIgnoreCase(f.getFaceString()) || s.equalsIgnoreCase(f.getNameString()))
                return f;
        }
        return null;
    }

    public int getAltValue(){
        return this.altValue;
    }

}

package io.zipcoder.casino.gofish;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Face;
import io.zipcoder.casino.tools.Hand;

import java.util.ArrayList;
import java.util.Comparator;

public class GoFishHand extends Hand{


    private Integer tallyMatches = 0;

    public GoFishHand(){
        //super.cardsOnHand = new ArrayList<>();
    }

    public Integer getTallyMatches() {
        return tallyMatches;
    }
    public Boolean doesMyHandHave(Face theFace){
        for (Card c: getCardsOnHand()) {
            if(c.getFace()== theFace){
                return true;
            }
        }
        return false;
    }

    public Integer howManyDoIHave(Face theFace){
        int counter = 0;
        for (Card c: getCardsOnHand()) {
            if(c.getFace()== theFace){
                counter++;
            }
        }
        return counter;
    }

    //this method will return an array of faces on hands
    //this will take out the dupes
    public ArrayList<Face> listEveryFaceIHave(){
        ArrayList<Face> f = new ArrayList<>();
        f.add(super.getCardsOnHand().get(0).getFace());

        for (int i=1; i< super.getCardsOnHand().size(); i++) {
            if(!f.contains(super.getCardsOnHand().get(i).getFace()))
                f.add(super.getCardsOnHand().get(i).getFace());
        }
        f.sort(Comparator.comparingInt(Face::getValue));
        return f;
    }

    //this method will pass cards in current hand to another hand that has the given face
    public void giveCardsTo(Face theFace,Hand anotherHand){
        for(Card c :super.getCardsOnHand()){
            if(theFace == c.getFace()){
                anotherHand.addCardToHand(c);
            }
        }
    }


    public void increaseTally(){
        tallyMatches++;
    }

}

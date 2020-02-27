package io.zipcoder.casino.game;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Hand;

public abstract class CardGame extends Game {
    public static Card deal(Deck aDeck, Hand aHand){
        Card dealtCard = aDeck.takeCard();

        if(dealtCard != null)
            aHand.addCardToHand(dealtCard);

        return dealtCard;
    }
}

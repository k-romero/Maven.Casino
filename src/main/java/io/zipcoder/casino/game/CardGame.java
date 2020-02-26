package io.zipcoder.casino.game;
import io.zipcoder.casino.tools.Card;
import io.zipcoder.casino.tools.Deck;
import io.zipcoder.casino.tools.Hand;

public abstract class CardGame extends Game {
    public static void deal(Deck aDeck, Hand aHand){
        Card someCard = aDeck.peek();
        aDeck.remove();
        aHand.addCardToHand(someCard);
    }
}

package io.zipcoder.casino.gofish;

import io.zipcoder.casino.dealer.CardDealer;
import io.zipcoder.casino.tools.Hand;

public class GoFishDealer extends CardDealer {

    GoFishHand goFishHand;

    public GoFishDealer() {
        this.goFishHand = new GoFishHand();
    }

    public GoFishHand getGoFishHand() {
        return goFishHand;
    }
}

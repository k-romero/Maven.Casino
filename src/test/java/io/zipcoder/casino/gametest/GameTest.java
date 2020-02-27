package io.zipcoder.casino.gametest;

import io.zipcoder.casino.game.Game;
import io.zipcoder.casino.player.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void comparePositiveTest()
    {
        int sum1 = 10;
        int sum2 = 4;
        int expected = 1;
        Game game = new Game(){
            @Override
            public void start(Player p1) { }

            @Override
            public void end(Player p1) { }
        };

        int actual = game.compare(sum1,sum2);
        assertEquals(expected,actual);
    }

    @Test
    public void compareNegativeTest()
    {
        int sum1 = 4;
        int sum2 = 10;
        int expected = -1;
        Game game = new Game(){
            @Override
            public void start(Player p1) { }

            @Override
            public void end(Player p1) { }
        };

        int actual = game.compare(sum1,sum2);
        assertEquals(expected,actual);
    }

    @Test
    public void compareEqualTest()
    {
        int sum1 = 4;
        int sum2 = 4;
        int expected = 0;
        Game game = new Game(){
            @Override
            public void start(Player p1) { }

            @Override
            public void end(Player p1) { }
        };

        int actual = game.compare(sum1,sum2);
        assertEquals(expected,actual);
    }
}

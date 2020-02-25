package io.zipcoder.casino.toolstest;

import io.zipcoder.casino.tools.Dice;
import org.junit.Assert;
import org.junit.Test;

public class DiceTest {
    @Test
    public void TestDice(){
        // Given
        Integer expected = 5;

        // When
        Dice dice = new Dice(expected);

        // Then
        Assert.assertEquals(dice.getNumberOfDice(), expected);
    }


    @Test
    public void TestTossAndSum(){
        // Given
        Integer numberOfDice = 2;
        Dice newDice = new Dice(2);
        Integer upperRange = numberOfDice * 6;

        // When
        Integer actual = newDice.tossAndSum();

        // Then
        Assert.assertTrue(actual >= numberOfDice && actual <= upperRange);
    }

    @Test
    public void TestTossAndSumWithSeedTest(){
        //Given
        Dice dice = new Dice(2);

        //When
        Integer actual = dice.tossAndSumWithSeed(7);
        Integer expected = 8;

        //Then
        Assert.assertEquals(expected,actual,0);
    }

}

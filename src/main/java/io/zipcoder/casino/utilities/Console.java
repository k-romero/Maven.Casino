package io.zipcoder.casino.utilities;


import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * You are advised against modifying this class.
 */
public final class Console {
    private final Scanner input;
    private final PrintStream output;

    public Console(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.output = out;
    }

    public void print(String val, Object... args) {
        output.format(val, args);
    }

    public void println(String val, Object... vals) {
        print(val + "\n", vals);
    }


    public String getStringInput(String prompt, Object... args) {
        println(prompt, args);
        return input.nextLine();
    }

    public String getStringInputWithoutln(String prompt, Object... args) {
        print(prompt, args);
        return input.nextLine();
    }

    public Double getDoubleInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Double doubleInput = Double.parseDouble(stringInput);
            return doubleInput;
        } catch (NumberFormatException nfe) { // TODO - Eliminate recursive nature
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting a numeric value!");
            return getDoubleInput(prompt, args);
        }
    }

    public Long getLongInput(String prompt, Object... args) {
        String stringInput = getStringInput(prompt, args);
        try {
            Long longInput = Long.parseLong(stringInput);
            return longInput;
        } catch (NumberFormatException nfe) { // TODO - Eliminate recursive nature
            println("[ %s ] is an invalid user input!", stringInput);
            println("Try inputting an integer value!");
            return getLongInput(prompt, args);
        }
    }

    public Integer getIntegerInput(String prompt, Object... args) {
        return getLongInput(prompt, args).intValue();
    }

    public Integer getIntegerInputWithoutln(String prompt, Object... args) {
        while(true) {
            print(prompt, args);
            String stringInput = input.nextLine();
            try {
                return Integer.parseInt(stringInput);
            } catch (NumberFormatException nfe) {
                println("Invalid Input! Try enter a number.");
            }
        }
    }

    public void pressEnterToCount(String s){
        getStringInputWithoutln(s);
    }

    public static String getPaddedString(String str, char paddingChar, int max) {
        if (str == null) {
            throw new NullPointerException("Can not add padding in null String!");
        }
        int maxPadding =max;
        int length = str.length();
        int padding = (maxPadding - length) / 2;
        if (padding <= 0) {
            return str;
        }
        String empty = "", hash = "#";
        int extra = (length % 2 == 0) ? 1 : 0;
        String leftPadding = "%" + padding + "s";
        String rightPadding = "%" + (padding - extra) + "s";
        String strFormat = leftPadding + "%s" + rightPadding;
        String formattedString = String.format(strFormat, empty, hash, empty);

        return formattedString.replace(' ', paddingChar).replace(hash, str);
    }
}


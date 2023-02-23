package model;

import java.lang.reflect.Array;
import java.util.Random;

public class WordColour {

    private final String[] colors = {"black", "red", "green", "yellow", "blue", "cyan", "white"};

    private final String[] spellings = {"BLACK", "WHITE", "YELLOW", "BLUE", "GREEN", "RED", "CYAN"};
    Random spellingIndex = new Random();

    // EFFECTS: Generates a random index for the array of colors available
    public int randomColorIndex() {
        Random colorIndex = new Random();
        return colorIndex.nextInt(7);
    }

    public int randomSpellingIndex() {
        return spellingIndex.nextInt(7);
    }

    // EFFECTS: Returns a string from the array randomly based on the index
    public String chooseRandomColorName() {
        return (String) Array.get(colors, randomColorIndex());
    }

    public String chooseSpellingOfColor() {
        return (String) Array.get(spellings, randomSpellingIndex());
    }

    // EFFECTS: returns an equivalent ANSI code randomly to print colored text on terminal
    public String ansiCodeOfColor(String givenColor) {
        if (givenColor == "red") {

            return "\u001B[31m";
        } else if (givenColor == "green") {

            return "\u001B[32m";
        } else if (givenColor == "blue") {

            return "\u001B[34m";
        } else if (givenColor == "cyan") {

            return "\u001B[36m";

        } else if (givenColor == "yellow") {

            return "\u001B[33m";

        } else if (givenColor == "black") {

            return "\u001B[30m";

        } else {

            return "\u001B[37m";
        }

    }

}

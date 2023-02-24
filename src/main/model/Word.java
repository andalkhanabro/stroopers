package model;

import java.lang.reflect.Array;
import java.util.Random;

public class Word {

    public final String[] colors = {"black", "red", "green", "yellow", "blue", "cyan", "white"};

    public final String[] spellings = {"BLACK", "WHITE", "YELLOW", "BLUE", "GREEN", "RED", "CYAN"};
    Random spellingIndex = new Random();

    // EFFECTS: Generates a random index for the array of colors available
    public int randomColorIndex() {
        Random colorIndex = new Random();
        return colorIndex.nextInt(7);
    }

    // EFFECTS: Generates a random index for the array of spellings of colors available
    public int randomSpellingIndex() {
        return spellingIndex.nextInt(7);
    }

    // EFFECTS: Returns a color from the array of colors randomly based on the index
    public String chooseRandomColorName() {
        return (String) Array.get(colors, randomColorIndex());
    }

    // EFFECTS: Returns a spelling of the color from the array of spellings randomly based on the index
    public String chooseSpellingOfColor() {
        return (String) Array.get(spellings, randomSpellingIndex());
    }

    // REQUIRES: givenColor cannot be null or other than the ones specified in the array of available colors
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

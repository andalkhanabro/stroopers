package model;


// the test class for Word

//public String ansiCodeOfColor(String givenColor) {
//        if (givenColor == "red") {
//
//        return "\u001B[31m";
//        } else if (givenColor == "green") {
//
//        return "\u001B[32m";
//        } else if (givenColor == "blue") {
//
//        return "\u001B[34m";
//        } else if (givenColor == "cyan") {
//
//        return "\u001B[36m";
//
//        } else if (givenColor == "yellow") {
//
//        return "\u001B[33m";
//
//        } else if (givenColor == "black") {
//
//        return "\u001B[30m";
//
//        } else {
//
//        return "\u001B[37m";
//        }
//
//        }

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordColourTest {

    WordColour wordColour;


    @BeforeEach
    void setup() {

        wordColour = new WordColour();
    }


    @Test
    void ansiCodeOfColorTest() {

        assertEquals("\u001B[31m", wordColour.ansiCodeOfColor("red"));
        assertEquals("\u001B[32m", wordColour.ansiCodeOfColor("green"));
        assertEquals("\u001B[34m", wordColour.ansiCodeOfColor("blue"));
        assertEquals("\u001B[36m", wordColour.ansiCodeOfColor("cyan"));
        assertEquals("\u001B[33m", wordColour.ansiCodeOfColor("yellow"));
        assertEquals("\u001B[30m", wordColour.ansiCodeOfColor("black"));
        assertEquals("\u001B[37m", wordColour.ansiCodeOfColor("white"));

    }










}
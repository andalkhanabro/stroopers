package model;

// the test class for WordColor

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
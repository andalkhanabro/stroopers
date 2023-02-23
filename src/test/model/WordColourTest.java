package model;

// the test class for WordColor

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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


    @Test
    void randomColorIndexTest() {

        int count = 0;

        while (count < 10000) {

        int output = wordColour.randomColorIndex();

        assertTrue(0 <= output && output <= 6);

        count++; }

    }

    @Test
    void randomSpellingIndexTest() {

        int count = 0;

        while (count < 10000) {

        int output = wordColour.randomSpellingIndex();
        assertTrue(0 <= output && output <= 6);

        count++;

        }

    }


    @Test
    void chooseRandomColorNameTest() {

        int count = 0;

        while (count < 10000) {

        String colorName = wordColour.chooseRandomColorName();

        boolean result = (Objects.equals(colorName, "black")) || (Objects.equals(colorName, "red")) ||
                (Objects.equals(colorName, "white")) ||
                (Objects.equals(colorName, "blue")) || (Objects.equals(colorName, "cyan")) ||
                (Objects.equals(colorName, "yellow")) || (Objects.equals(colorName, "green"));

        assertTrue(result);

        count++;
        }

    }


    @Test
    void chooseRandomSpellingTest() {

        int count = 0;

        while (count < 10000) {

        String spelling = wordColour.chooseSpellingOfColor();

        boolean result = (Objects.equals(spelling, "BLACK")) || (Objects.equals(spelling, "RED")) ||
                (Objects.equals(spelling, "WHITE")) ||
                (Objects.equals(spelling, "BLUE")) || (Objects.equals(spelling, "CYAN")) ||
                (Objects.equals(spelling, "YELLOW")) || (Objects.equals(spelling, "GREEN"));

        assertTrue(result);

        count++;

        }

    }

}
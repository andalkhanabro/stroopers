package model;

// the test class for Answer

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {

    Answer answer1;

    @BeforeEach
    void setup() {

        answer1 = new Answer();

    }

    @Test
    void generateCorrectAnswerTest() {

        assertEquals('r', answer1.generateCorrectAnswer("red"));
        assertEquals('b', answer1.generateCorrectAnswer("blue"));
        assertEquals('b', answer1.generateCorrectAnswer("black"));
        assertEquals('w', answer1.generateCorrectAnswer("white"));
        assertEquals('c', answer1.generateCorrectAnswer("cyan"));
        assertEquals('g', answer1.generateCorrectAnswer("green"));
        assertEquals('y', answer1.generateCorrectAnswer("yellow"));

    }

    @Test
    void isUserAnswerCorrectTest() {

        // if first letter of true color is entered in lowercase

        assertTrue(answer1.isUserAnswerCorrect("r", "red"));
        assertTrue(answer1.isUserAnswerCorrect("b", "black"));
        assertTrue(answer1.isUserAnswerCorrect("b", "blue"));

        assertTrue(answer1.isUserAnswerCorrect("g", "green"));
        assertTrue(answer1.isUserAnswerCorrect("c", "cyan"));
        assertTrue(answer1.isUserAnswerCorrect("w", "white"));

        // if first letter of true color is entered in uppercase

        assertTrue(answer1.isUserAnswerCorrect("C", "cyan"));
        assertTrue(answer1.isUserAnswerCorrect("G", "green"));

    }

    @Test
    void isUserAnswerNotCorrectTest() {

        assertFalse(answer1.isUserAnswerCorrect("G", "black"));
        assertFalse(answer1.isUserAnswerCorrect("g", "blue"));
        assertFalse(answer1.isUserAnswerCorrect("word", "green"));
        assertFalse(answer1.isUserAnswerCorrect("Black", "white"));

    }






}

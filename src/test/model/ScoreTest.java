package model;

// the test class for Score

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreTest {

    Score score1;

    Score score2;

    Score score3;

    Score score4;

    @BeforeEach
    void setup() {

        score1 = new Score("Andal", 2);
        score2 = new Score("Sarah", 3);
        score3 = new Score("Jack", 8);

        score4 = new Score();

    }

    @Test
    void ScoreConstructorTest() {

        assertEquals(2, score1.getPoints());
        assertEquals(3, score2.getPoints());
        assertEquals(8, score3.getPoints());

        assertEquals("Andal", score1.getName());
        assertEquals("Sarah", score2.getName());
        assertEquals("Jack", score3.getName());

    }

    @Test
    void ScoreSecondConstructorTest() {

        assertEquals(0, score4.getPoints());

    }

    @Test
    void toStringTest() {
        assertEquals("Name: Andal | Points: 2", score1.toString());
        assertEquals("Name: Sarah | Points: 3",score2.toString());
        assertEquals("Name: Jack | Points: 8", score3.toString());
    }

    @Test
    void updatePointCount() {

        assertEquals(2, score1.getPoints());

        score1.updatePointCount();

        assertEquals(3, score1.getPoints());

        score1.updatePointCount();
        score1.updatePointCount();

        assertEquals(5, score1.getPoints());

    }


    @Test
    void toJsonTest() {
        Score testScore = new Score("A", 2);
        JSONObject testJson = testScore.toJson();
        assertEquals(testJson.getString("name"), "A");
        assertEquals(testJson.getString("points"), "2");
    }

}

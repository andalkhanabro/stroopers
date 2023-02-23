package model;


// the test class for ScoreBoard

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreBoardTest {

    ScoreBoard scoreboard;

    Score score1;

    Score score2;

    Score score3;

    Score score4;

    @BeforeEach
    void setup() {

        scoreboard = new ScoreBoard();

        score1 = new Score("Aandal", 3);
        score2 = new Score("Jack", 2);
        score3 = new Score("John", 5);
        score4 = new Score("Sarah", 10);
    }

    @Test
    void ConstructorTest() {

        assertEquals(0, scoreboard.scoreboardSize());
    }

    @Test
    void addScoreTest() {
        scoreboard.addScore(score1);
        scoreboard.addScore(score2);
        scoreboard.addScore(score3);
        scoreboard.addScore(score4);

        assertEquals(4, scoreboard.scoreboardSize());
        assertEquals(score1, scoreboard.getScore(0));
        assertEquals(score2, scoreboard.getScore(1));
        assertEquals(score3, scoreboard.getScore(2));
        assertEquals(score4, scoreboard.getScore(3));

    }

    @Test
    void deleteScoreWhenUserWantsTest() {

        scoreboard.addScore(score1);
        scoreboard.addScore(score2);
        scoreboard.addScore(score3);
        scoreboard.addScore(score4);

        assertEquals(4, scoreboard.scoreboardSize());
        assertEquals(score4, scoreboard.getScore(3));

        scoreboard.deleteScore(true);

        assertEquals(3, scoreboard.scoreboardSize());
        assertEquals(score3, scoreboard.getScore(2));
        assertEquals(score2, scoreboard.getScore(1));
        assertEquals(score1, scoreboard.getScore(0));
    }


    @Test
    void deleteScoreWhenUserDoesNotWantTest() {

        scoreboard.addScore(score1);
        scoreboard.addScore(score2);
        scoreboard.addScore(score3);
        scoreboard.addScore(score4);

        assertEquals(4, scoreboard.scoreboardSize());
        assertEquals(score4, scoreboard.getScore(3));

        scoreboard.deleteScore(false);

        assertEquals(4, scoreboard.scoreboardSize());
        assertEquals(score4, scoreboard.getScore(3));
        assertEquals(score3, scoreboard.getScore(2));
        assertEquals(score2, scoreboard.getScore(1));
        assertEquals(score1, scoreboard.getScore(0));

    }

    @Test
    void getScoreTest() {

        scoreboard.addScore(score1);
        scoreboard.addScore(score2);
        scoreboard.addScore(score3);
        scoreboard.addScore(score4);

        assertEquals(score4, scoreboard.getScore(3));
        assertEquals(score3, scoreboard.getScore(2));
        assertEquals(score2, scoreboard.getScore(1));
        assertEquals(score1, scoreboard.getScore(0));

    }

    @Test
    void scoreboardSizeTest() {

        assertEquals(0, scoreboard.scoreboardSize());

        scoreboard.addScore(score1);
        scoreboard.addScore(score2);
        scoreboard.addScore(score3);

        assertEquals(3, scoreboard.scoreboardSize());

    }

    @Test
    void rankScoreBoardTest() {

        scoreboard.addScore(score1);
        scoreboard.addScore(score2);
        scoreboard.addScore(score3);
        scoreboard.addScore(score4);

        scoreboard.rankScoreboard();

        assertEquals(score4, scoreboard.getScore(0));
        assertEquals(score3, scoreboard.getScore(1));
        assertEquals(score1, scoreboard.getScore(2));
        assertEquals(score2, scoreboard.getScore(3));

    }











}

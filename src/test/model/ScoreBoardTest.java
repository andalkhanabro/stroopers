package model;


// the test class for ScoreBoard

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScoreBoardTest {

    ScoreBoard scoreboard1;

    ScoreBoard scoreboard2;

    Score score1;

    Score score2;

    Score score3;

    Score score4;

    @BeforeEach
    void setup() {

        scoreboard1 = new ScoreBoard();
        scoreboard2 = new ScoreBoard();

        score1 = new Score("Aandal", 3);
        score2 = new Score("Jack", 2);
        score3 = new Score("John", 5);
        score4 = new Score("Sarah", 10);

    }

    @Test
    void ConstructorTest() {

        assertEquals(0, scoreboard1.scoreboardSize());
    }

    @Test
    void addScoreTest() {
        scoreboard1.addScore(score1);
        scoreboard1.addScore(score2);
        scoreboard1.addScore(score3);
        scoreboard1.addScore(score4);

        assertEquals(4, scoreboard1.scoreboardSize());
        assertEquals(score1, scoreboard1.getScore(0));
        assertEquals(score2, scoreboard1.getScore(1));
        assertEquals(score3, scoreboard1.getScore(2));
        assertEquals(score4, scoreboard1.getScore(3));

    }

    @Test
    void deleteScoreWhenUserWantsTest() {

        scoreboard1.addScore(score1);
        scoreboard1.addScore(score2);
        scoreboard1.addScore(score3);
        scoreboard1.addScore(score4);

        assertEquals(4, scoreboard1.scoreboardSize());
        assertEquals(score4, scoreboard1.getScore(3));

        scoreboard1.deleteScore(true);

        assertEquals(3, scoreboard1.scoreboardSize());
        assertEquals(score3, scoreboard1.getScore(2));
        assertEquals(score2, scoreboard1.getScore(1));
        assertEquals(score1, scoreboard1.getScore(0));
    }


    @Test
    void deleteScoreWhenUserDoesNotWantTest() {

        scoreboard1.addScore(score1);
        scoreboard1.addScore(score2);
        scoreboard1.addScore(score3);
        scoreboard1.addScore(score4);

        assertEquals(4, scoreboard1.scoreboardSize());
        assertEquals(score4, scoreboard1.getScore(3));

        scoreboard1.deleteScore(false);

        assertEquals(4, scoreboard1.scoreboardSize());
        assertEquals(score4, scoreboard1.getScore(3));
        assertEquals(score3, scoreboard1.getScore(2));
        assertEquals(score2, scoreboard1.getScore(1));
        assertEquals(score1, scoreboard1.getScore(0));

    }

    @Test
    void getScoreTest() {

        scoreboard1.addScore(score1);
        scoreboard1.addScore(score2);
        scoreboard1.addScore(score3);
        scoreboard1.addScore(score4);

        assertEquals(score4, scoreboard1.getScore(3));
        assertEquals(score3, scoreboard1.getScore(2));
        assertEquals(score2, scoreboard1.getScore(1));
        assertEquals(score1, scoreboard1.getScore(0));

    }

    @Test
    void scoreboardSizeTest() {

        assertEquals(0, scoreboard1.scoreboardSize());

        scoreboard1.addScore(score1);
        scoreboard1.addScore(score2);
        scoreboard1.addScore(score3);

        assertEquals(3, scoreboard1.scoreboardSize());

    }

    @Test
    void rankScoreBoardTest() {

        scoreboard1.addScore(score1);
        scoreboard1.addScore(score2);
        scoreboard1.addScore(score3);
        scoreboard1.addScore(score4);

        scoreboard1.rankScoreboard();

        assertEquals(score4, scoreboard1.getScore(0));
        assertEquals(score3, scoreboard1.getScore(1));
        assertEquals(score1, scoreboard1.getScore(2));
        assertEquals(score2, scoreboard1.getScore(3));

    }

    @Test
    void determineRankTest() {

        // already sorted in points, rank is determined directly

        scoreboard1.addScore(score4);
        scoreboard1.addScore(score3);
        scoreboard1.addScore(score1);
        scoreboard1.addScore(score2);

        assertEquals(1,scoreboard1.determineRank(score4));
        assertEquals(2,scoreboard1.determineRank(score3));
        assertEquals(3,scoreboard1.determineRank(score1));
        assertEquals(4,scoreboard1.determineRank(score2));

        // sort by points first, then determine rank

        scoreboard2.addScore(score1);
        scoreboard2.addScore(score2);
        scoreboard2.addScore(score3);
        scoreboard2.addScore(score4);

        assertEquals(1,scoreboard2.determineRank(score4));
        assertEquals(2,scoreboard2.determineRank(score3));
        assertEquals(4,scoreboard2.determineRank(score2));
        assertEquals(3,scoreboard2.determineRank(score1));

    }











}

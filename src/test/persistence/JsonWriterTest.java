package persistence;


import model.Score;
import model.ScoreBoard;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// a test class for JsonWriter
public class JsonWriterTest {

    // Testing writer by writing a file and then reading it back in to see if it matches what we wanted


    // testing the case when an invalid destination is given as file path to write to
    @Test
    void testWriterCannotBeOpenedFromInvalidPath() {
        try {
            ScoreBoard sb = new ScoreBoard(); // create a scoreboard to be written
            JsonWriter writer = new JsonWriter("./data/my/illegalfilepath:json");
            writer.open();
            fail("Did not raise IOException due to invalid file path.");
        } catch (IOException e) {
            // all good because this block will not run in correct circumstances.
        }
    }


    // testing the case in which a valid path is given, but the scoreboard to be written has no scores
    // valid file path has the form "./data/<custom>.json"; where custom can be modified as we want
    @Test
    void testWriterScoreBoardIsEmpty() {

        try {
            // Writing Scoreboard To File

            ScoreBoard sb = new ScoreBoard();
            JsonWriter writer = new JsonWriter("./data/ScoreBoardIsEmpty:json");
            writer.open(); // writer should open successfully
            writer.write(sb); // writer should write sb as json object
            writer.close(); // writer should close successfully

            // Reading Scoreboard From File and then testing it to check if it was written properly

            JsonReader reader = new JsonReader("./data/ScoreBoardIsEmpty:json");
            ScoreBoard retrievedScoreboard = reader.read();
            assertEquals(retrievedScoreboard.scoreboardSize(), 0);
            assertTrue(retrievedScoreboard.mapScoresToScoreEntries().isEmpty());


        } catch (IOException e) {
            fail("Fail test because exception is caught when it should not have been thrown in the first place.");
        }
    }

    @Test
    void testWriterNormalScoreBoardWithScores() {

        try {
            // Writing Scoreboard To File

            ScoreBoard sb = new ScoreBoard();
            sb.addScore(new Score("Aandal", 3));
            sb.addScore(new Score("Sam", 1));
            sb.addScore(new Score("Jack", 0));

            JsonWriter writer = new JsonWriter("./data/ScoreBoardHasScores:json");
            writer.open(); // writer should open successfully
            writer.write(sb); // writer should write sb as json object
            writer.close(); // writer should close successfully

            // Reading Scoreboard From File and then testing it to check if it was written properly

            JsonReader reader = new JsonReader("./data/ScoreBoardHasScores:json");
            ScoreBoard retrievedScoreboard = reader.read();

            assertEquals(retrievedScoreboard.scoreboardSize(), 3);
            assertFalse(retrievedScoreboard.mapScoresToScoreEntries().isEmpty());

            assertEquals(retrievedScoreboard.getScore(0).getPoints(), 3);
            assertEquals(retrievedScoreboard.getScore(1).getPoints(), 1);
            assertEquals(retrievedScoreboard.getScore(2).getPoints(), 0);

            assertTrue(retrievedScoreboard.getScore(0).getName().equals("Aandal"));
            assertTrue(retrievedScoreboard.getScore(1).getName().equals("Sam"));
            assertTrue(retrievedScoreboard.getScore(2).getName().equals("Jack"));

        } catch (IOException e) {
            fail("Fail test because exception is caught when it should not have been thrown in the first place.");
        }

    }









}

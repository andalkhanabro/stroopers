package persistence;

import model.ScoreBoard;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// a test class for JsonReader
public class JsonReaderTest {

    @Test
    void testReaderForFileNotFound() {

        JsonReader reader = new JsonReader("./data/notAnActualFile:json");
        try
        {
            reader.read();
            fail("Compiler should not have reached here in normal circumstances. Fail test! ");
        }

        catch (IOException ioException) {
            // all good!
        }
    }

    @Test
    void testReaderForEmptyScoreboard() {

        try {

            JsonReader reader = new JsonReader("./data/ScoreBoardIsEmpty:json");
            ScoreBoard retrievedScoreboard = reader.read();
            assertEquals(retrievedScoreboard.scoreboardSize(), 0);
            assertTrue(retrievedScoreboard.mapScoresToScoreEntries().isEmpty());
        } catch (IOException e) {
            fail("An exception should not have been thrown in normal execution.");
        }

    }

    @Test
    void testReaderForNormalScoreboard() {

        try {
            JsonReader reader = new JsonReader("./data/ScoreBoardHasScores:json");
            ScoreBoard retrievedScoreboard = reader.read();

            assertEquals(retrievedScoreboard.scoreboardSize(), 3);
            assertFalse(retrievedScoreboard.mapScoresToScoreEntries().isEmpty());

            assertEquals(retrievedScoreboard.getScore(0).getPoints(), 3);
            assertEquals(retrievedScoreboard.getScore(1).getPoints(), 1);
            assertEquals(retrievedScoreboard.getScore(2).getPoints(), 0);

            assertEquals("Aandal", retrievedScoreboard.getScore(0).getName());
            assertEquals("Sam", retrievedScoreboard.getScore(1).getName());
            assertEquals("Jack", retrievedScoreboard.getScore(2).getName());

        } catch (IOException e) {
            fail("An exception was not expected under normal execution.");
        }

    }


    // other methods need to be explicitly tested? not done like this in workroom App














}

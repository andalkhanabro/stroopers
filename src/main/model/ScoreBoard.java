package model;

// A collection of Scores of users who play the game

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard implements Writable {

    private List<Score> scoreBoard;

    private String name;

    // EFFECTS: Creates an empty scoreboard for the Stroop Game
    public ScoreBoard() {

        this.scoreBoard = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds the given score to the scoreboard
    public void addScore(Score s) {
        this.scoreBoard.add(s);
        int points = s.getPoints();
        String name = s.getName();
        EventLog.getInstance().logEvent(new Event("Profile [Username: " + name + ", Points: " + points + "]"
                + " has been added to the board!"));
        // logs the event when score is added to board!
    }

    // REQUIRES: scoreboard is not empty
    // MODIFIES: this
    // EFFECTS: Deletes the recently added score from the scoreboard
    public void deleteScore(Score s) {

        this.scoreBoard.remove(s);

    }

    // REQUIRES: scoreboard should not be empty
    // EFFECTS: returns a score from the scoreboard at a given index
    public Score getScore(int index) {
        return this.scoreBoard.get(index);
    }

    // REQUIRES: scoreboard should not be empty
    // MODIFIES: this
    // EFFECTS: ranks all scores present in scoreboard in order of decreasing point count

    public void sortScoreBoard() {

        boolean ranked = true;

        while (ranked) {

            ranked = false;

            for (int i = 0; i < this.scoreboardSize() - 1; i++) {

                Score currentScore = this.scoreBoard.get(i);
                Score nextScore = this.scoreBoard.get(i + 1);
                int currentScorePoints = this.scoreBoard.get(i).getPoints();
                int nextScorePoints = this.scoreBoard.get(i + 1).getPoints();

                if (currentScorePoints > nextScorePoints) {
                    ranked = true;
                    this.scoreBoard.set(i, nextScore);
                    this.scoreBoard.set(i + 1, currentScore);

                }
            }

        }
        Collections.reverse(this.scoreBoard);
        EventLog.getInstance().logEvent(new Event("Scoreboard has been sorted according to points using "
                + "a sorting method!"));
    }

    // EFFECTS: returns the total number of times the game has been played by all users (the scoreboard size)
    public int scoreboardSize() {
        return this.scoreBoard.size();
    }


    // REQUIRES: scoreboard is not empty
    // MODIFIES: Score in scoreboard
    // EFFECTS: maps scores of scoreboards to a list of strings in a displayable format
    public List<String> mapScoresToScoreEntries() {

        List<String> entries = new ArrayList<>();

        for (Score s: this.scoreBoard) {
            entries.add(s.toString());
        }

        return entries;

    }

    // REQUIRES: Score cannot be null
    // MODIFIES: this
    /* EFFECTS: Determines the rank of the player in the scoreboard relative to other entries in the board after
     * re-sorting it
     */
    public int determineRank(Score score) {
        int rank = this.scoreBoard.indexOf(score) + 1;
        int users = this.scoreBoard.size();
        String userName = score.getName();
        EventLog.getInstance().logEvent(new Event("Rank of " + userName + " is " + rank + "/" + users + " "
                + "on the board!"));
        return rank;
    }

    // EFFECTS: returns a json object equivalent to scores in a given scoreboard
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("scores", scoresToJson());
        return json;
    }

    // EFFECTS: returns scores in this scoreboard as a JSON array
    public JSONArray scoresToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Score s : scoreBoard) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}

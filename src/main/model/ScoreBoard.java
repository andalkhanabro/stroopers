package model;

// a collection of Scores of users who play the game, sorted in order of decreasing points

import ui.ScoreboardUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreBoard {

    private final List<Score> scoreBoard;

    // EFFECTS: Creates an empty scoreboard for the game
    public ScoreBoard() {
        this.scoreBoard = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds the given score to the scoreboard
    public void addScore(Score s) {
        this.scoreBoard.add(s);
    }

    // REQUIRES: scoreboard is not empty
    // MODIFIES: this
    // EFFECTS: Deletes the most recently added score from the scoreboard
    public void deleteScore(boolean userWantsToDelete) {
        if (userWantsToDelete) {

            this.scoreBoard.remove(scoreBoard.size() - 1);
        }

    }

    // REQUIRES: scoreboard should not be empty
    // EFFECTS: returns a score from the scoreboard at a given index
    public Score getScore(int index) {
        return this.scoreBoard.get(index);
    }

    // REQUIRES: a non-empty scoreboard
    // MODIFIES: this
    // EFFECTS: ranks all scores present in scoreboard in order of decreasing point count

    public void rankScoreboard() {

        boolean swapped = true;

        while (swapped) {

            swapped = false;

            for (int i = 0; i < this.scoreboardSize() - 1; i++) {

                Score currentScore = this.scoreBoard.get(i);
                Score nextScore = this.scoreBoard.get(i + 1);
                int currentScorePoints = this.scoreBoard.get(i).getPoints();
                int nextScorePoints = this.scoreBoard.get(i + 1).getPoints();

                if (currentScorePoints > nextScorePoints) {
                    swapped = true;
                    this.scoreBoard.set(i, nextScore);
                    this.scoreBoard.set(i + 1, currentScore);

                }
            }

        }
        Collections.reverse(this.scoreBoard);
    }

    // EFFECTS: returns the total number of times the game has been played by all users (the scoreboard size)
    public int scoreboardSize() {
        return this.scoreBoard.size();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: displays the current Scoreboard after it has been sorted


    // REQUIRES: scoreboard is not empty
    // EFFECTS: maps scores of scoreboards to a list of strings in a displayable format
    public List<String> mapScoresToScoreEntries() {

        List<String> entries = new ArrayList<>();

        for (Score s: this.scoreBoard) {
            entries.add(s.toString());
        }

        return entries;
    }

    // EFFECTS: Determines the rank of the player in the scoreboard
    public int determineRank(Score s) {
        this.rankScoreboard();
        return this.scoreBoard.indexOf(s) + 1;
    }

}

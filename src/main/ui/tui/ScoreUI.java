package ui.tui;

import model.Score;

// a class with UI methods that deal directly with the Score class
public class ScoreUI {

    // REQUIRES: score cannot be null
    // EFFECTS: Prints game over message and points scored by the user in that game
    public void printScoreDetails(Score score) {
        System.out.println("\n\u001b[0m" +  "Oops! Game Over.");
        System.out.println("You scored " + score.getPoints() + " points.");
    }

}

package ui;

import model.Score;

public class ScoreUI {

    // EFFECTS: Prints game over message and points scored by the user in that game
    public void printScoreDetails(Score s) {
        System.out.println("\n\u001b[35m" +  "Oops! Game over.");
        System.out.println("You scored " + s.getPoints() + " points.");
    }

}

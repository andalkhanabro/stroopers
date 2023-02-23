package ui;

import model.Score;

public class ScoreUI {

    public void printScoreDetails(Score s) {
        System.out.println("\nOops! Game over.");
        System.out.println("You scored " + s.getPoints() + " points.");
    }



}

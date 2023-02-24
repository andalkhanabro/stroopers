package ui;

import model.Score;

public class ScoreUI {

    public void printScoreDetails(Score s) {
        System.out.println("\n\u001B[37m" +  "Oops! Game over.");
        System.out.println("You scored " + s.getPoints() + " points.");
    }



}

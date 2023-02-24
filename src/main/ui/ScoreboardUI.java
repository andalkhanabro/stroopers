package ui;

import java.util.List;
import java.util.Scanner;

public class ScoreboardUI {
    public void printEntries(String s) {
        System.out.println(s);
    }

    public void printScoreBoardOnTerminal() {
        System.out.println("\n       SCOREBOARD");
        System.out.println("");
    }

    public boolean userWantsToAdd() {
        System.out.print("\nDo you want to add your score? ");
        Scanner userInput = new Scanner(System.in);

        if (userInput.nextBoolean()) {
            return true;
        } else {
            System.out.println("Thank you for playing!");
            return false;
        }
    }

    public boolean userWantsToDelete() {
        System.out.print("\nDo you want to delete the score you just added? (Incorrect spelling, uncool nickname etc..)"
                + " ");
        Scanner userInput = new Scanner(System.in);

        return userInput.nextBoolean();

    }

    public String getNameFromUser() {

        Scanner name = new Scanner(System.in); // user enters name
        System.out.print("\nEnter your name: ");
        return name.next();

    }

    public void printPerformanceStats(int rank, int numberOfGames) {

        System.out.println("\nYour rank is " + rank + " amongst " + numberOfGames + " total games played.");

    }

    public void printScoreEntries(List<String> mappedEntries) {
        for (String s: mappedEntries) {
            System.out.println(s);
        }
    }

}

package ui;

import model.ScoreBoard;

import java.util.List;
import java.util.Scanner;

public class ScoreboardUI {

    // EFFECTS: Prints the scoreboard title on the terminal
    public void printScoreBoardOnTerminal() {
        System.out.println("\n       SCOREBOARD");
        System.out.println("");
    }

    // EFFECTS: Asks the user if they want to add their score after each game ends
    public boolean userWantsToAdd() {
        System.out.println("\nDo you want to add your score? \n-Enter true for yes, false for no. ");
        Scanner userInput = new Scanner(System.in);

        if (userInput.nextBoolean()) {
            return true;
        } else {
            System.out.println("Thank you for playing!");
            return false;
        }
    }

    // EFFECTS: Asks the user if they want to delete the score from the scoreboard
    public boolean userWantsToDelete() {
        System.out.print("\nDo you want to delete the score you just added? (Incorrect spelling, uncool nickname etc..)"
                + "\n-Enter true for yes, false for no. ");
        Scanner userInput = new Scanner(System.in);

        return userInput.nextBoolean();

    }


   // EFFECTS: Prints the scoreboard heading and entries together on the terminal
    public void printScoreBoardHelper(ScoreBoard sb) {

        new ScoreboardUI().printScoreBoardOnTerminal();
        List<String> entries = sb.mapScoresToScoreEntries();
        new ScoreboardUI().printScoreEntries(entries);

    }

    // EFFECTS: Asks the user for their name to make personalised score
    public String getNameFromUser() {

        Scanner name = new Scanner(System.in); // user enters name
        System.out.print("\nEnter your name: ");
        return name.next();
    }

    // EFFECTS: Asks the user if they want to know their rank to other games
    public boolean doesUserWantRank() {
        System.out.print("\nDo you want to know your rank in the scoreboard? \n-Enter true for yes, false for no. ");
        Scanner userRank = new Scanner(System.in);
        return userRank.nextBoolean();

    }

    // EFFECTS: Prints rank of user amongst total games played
    public void printPerformanceStats(int rank, int numberOfGames) {

        System.out.println("\nYour rank is " + rank + " amongst " + numberOfGames + " scores on the board.");

    }


    // EFFECTS: Prints score entries of the scoreboard on the terminal
    public void printScoreEntries(List<String> mappedEntries) {
        for (String s: mappedEntries) {
            System.out.println(s);
        }
    }

}

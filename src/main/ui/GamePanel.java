package ui;

import model.Answer;
import model.Score;
import model.ScoreBoard;
import model.Word;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// the GamePanel class which manages the main UI for Stroop Game
public class GamePanel {
    private static ScoreBoard scoreboard = new ScoreBoard();

    private static JsonWriter jsonWriter;

    private static JsonReader jsonReader;

    private static final String JSON_STORE = "./data/scoreboard.json"; // destination where scoreboard will be saved

    // REQUIRES: t should be greater than 0
    // EFFECTS: delays the execution of the program by t milliseconds
    private static void delayTime(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // EFFECTS: asks user if they want to play the game again as many times as they want
    private static boolean startNextGames() {
        System.out.print("\nDo you want to play again? ");
        Scanner askUser = new Scanner(System.in);
        return askUser.nextBoolean();
    }

    // EFFECTS: asks the user if they want to play the game for the first time and introduces the game
    private static boolean startFirstGame() {
        System.out.println("\nWelcome to Stroopers! \n\nA word will be displayed on the screen. It will spell a color "
                + "and " + "have a color. \nTo earn a point, you will have to enter the first letter of the true color."
                + " So, fight the confusion..  \n \n- Enter true to play! "
                + "\n- Enter false to exit!\n ");
        Scanner askUser = new Scanner(System.in);
        return askUser.nextBoolean();
    }

    // EFFECTS: asks user if they want to add a score and view a sorted scoreboard, and displays it with a rank if true.
    // EFFECTS: asks user if they want to delete the score and deletes it if true
    private static void displayScoreboard(String name, boolean userWantsToAdd, int currentPoints, ScoreBoard sb) {

        if (userWantsToAdd) {
            Score newScore = new Score(name, currentPoints);
            sb.addScore(newScore);

            Scanner doesUserWantDisplay = new Scanner(System.in);
            System.out.print("\nDo you want to view a sorted scoreboard with your score? \n-Enter true for yes, false"
                    + " for no. ");

            if (doesUserWantDisplay.nextBoolean()) {

                sb.sortScoreBoard();
                new ScoreboardUI().printScoreBoardHelper(sb);
                int rank = sb.determineRank(newScore);
                int allGames = sb.scoreboardSize();

                if (new ScoreboardUI().doesUserWantRank()) {

                    new ScoreboardUI().printPerformanceStats(rank, allGames);
                }
            }

            if (new ScoreboardUI().userWantsToDelete()) {
                sb.deleteScore(newScore);
                new ScoreboardUI().printScoreBoardHelper(sb);
            }

        }

    }


    /* EFFECTS: Runs the game by printing Stroop words on the screen and verifies each answer by the user. Finally, it
     *          asks the user if they want the scoreboard summary when the user gives an incorrect answer.
     *
     */
    private static void runStroopEffect() {

        jsonWriter = new JsonWriter(JSON_STORE);

        boolean keepGameGoing = true;
        Word w1 = new Word();
        Score currentScore = new Score();

        // Prints a word to the screen with a random colour and spelling and prompts user for answer

        while (keepGameGoing) {
            String color = w1.chooseRandomColorName();
            System.out.println("\n");
            delayTime(500);
            System.out.println(w1.ansiCodeOfColor(color) + w1.chooseSpellingOfColor());
            System.out.println("\n");
            Scanner userAnswer = new Scanner(System.in);

            String whatUserEntered = userAnswer.next();

            // Verifies user answer with correct answer

            if (new Answer().isUserAnswerCorrect(whatUserEntered, color)) {
                currentScore.updatePointCount();

                // If user answer is incorrect, allows user to view scoreboard and rank, and/or play again arbitrarily

            } else {
                keepGameGoing = false;
                new ScoreUI().printScoreDetails(currentScore);
                String name = new ScoreboardUI().getNameFromUser();
                GamePanel.displayScoreboard(name, new ScoreboardUI().userWantsToAdd(), currentScore.getPoints(),
                        scoreboard);
                makeNextGames();
            }
        }
    }

    // EFFECTS: Prints a countdown for the game before it starts
    private static void printCountdown() {
        System.out.println("3...");
        delayTime(1000);
        System.out.println("2...");
        delayTime(1000);
        System.out.println("1...");
        delayTime(100);
        System.out.println("\nGo!");
        delayTime(200);
    }

    // EFFECTS: Makes game if user wants to start it, else prints the game terminating message
    public static void makeFirstGame() throws FileNotFoundException {

        if (startFirstGame()) {
            jsonReader = new JsonReader(JSON_STORE);
            if (doesUserWantToLoad()) {
                System.out.println("\nThe old scoreboard is printed below. ");
                scoreboard = loadOldScoreboard();
                new ScoreboardUI().printScoreBoardHelper(loadOldScoreboard());
                delayTime(2000);
            } else {
                System.out.println("\nData was not loaded. A new scoreboard will be made. ");
            }
            System.out.println("\nOkay! Game starting in.. ");
            delayTime(300);
            printCountdown();
            runStroopEffect();

        } else {
            System.out.println("\nGame exited. Thank you for playing!");

        }

    }

    // EFFECTS: asks user if they want to load previous scoreboard, if any
    private static boolean doesUserWantToLoad() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nDo you want to load the scoreboard from the previous game? ");
        return scanner.nextBoolean();
    }

    // EFFECTS: Makes next games as long as the user wants to play, else prints the terminating message
    private static void makeNextGames() {

        if (startNextGames()) {
            System.out.println("\nOkay! Game starting in.. ");
            delayTime(300);
            printCountdown();
            runStroopEffect();

        } else {
            if (doesUserWantToSave()) {
                saveOldScoreboard();
                System.out.println("\nGame exited and data has been saved at" + JSON_STORE
                        + ". Thank you for playing!");
            } else {
                System.out.println("\nGame exited and data was not saved. Thank you for playing!");
            }
        }

    }

    // EFFECTS: asks the user if they want to save the current scoreboard before terminating the program
    private static boolean doesUserWantToSave() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nDo you want to save the current scoreboard before leaving? ");
        return sc.nextBoolean();
    }


    // EFFECTS: saves current scoreboard to file
    private static void saveOldScoreboard() {
        try {
            jsonWriter.open();
            jsonWriter.write(scoreboard);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file while saving to " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads old scoreboard from file
    private static ScoreBoard loadOldScoreboard() {
        try {
            scoreboard = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Cannot read from the given file path " + JSON_STORE);
        }
        return scoreboard;

    }

}

package ui;

import model.Answer;
import model.Score;
import model.ScoreBoard;
import model.Word;
import java.util.Scanner;

// the GamePanel class which manages the main UI for Stroop Game
public class GamePanel {
    private static ScoreBoard scoreboard = new ScoreBoard();

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
            Answer firstAnswer = new Answer();

            String whatUserEntered = userAnswer.next();

            // Verifies user answer with correct answer

            if (firstAnswer.isUserAnswerCorrect(whatUserEntered, color)) {
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
    public static void makeFirstGame() {

        if (startFirstGame()) {
            System.out.println("\nOkay! Game starting in.. ");
            delayTime(300);
            printCountdown();
            runStroopEffect();

        } else {
            System.out.println("\nGame exited. Thank you for playing!");

        }

    }

    // EFFECTS: Makes next games as long as the user wants to play, else prints the terminating message
    private static void makeNextGames() {

        if (startNextGames()) {
            System.out.println("\nOkay! Game starting in.. ");
            delayTime(300);
            printCountdown();
            runStroopEffect();

        } else {
            System.out.println("\nGame exited. Thank you for playing!");

        }

    }

}

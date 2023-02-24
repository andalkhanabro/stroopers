package ui;

import model.Answer;
import model.Score;
import model.ScoreBoard;
import model.Word;
import java.util.Scanner;


public class GamePanel {
    private static ScoreBoard scoreboard = new ScoreBoard();

    public static void delayTime(int t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean startGame() {
        System.out.print("\nDo you want to play again? ");
        Scanner askUser = new Scanner(System.in);
        return askUser.nextBoolean();
    }

    public static boolean startFirstGame() {
        System.out.println("\nWelcome to Stroopers! \n\nA word will be displayed on the screen. It will spell a color "
                + "and "
                + "have a color. \nTo earn a point, you will have to enter the first letter of the true color. "
                + "So, fight the confusion..  \n \n- Enter true to play! "
                + "\n- Enter false to exit! ");
        Scanner askUser = new Scanner(System.in);
        return askUser.nextBoolean();
    }

    public static void displayScoreboard(String name, boolean userWantsToAdd, int currentPoints, ScoreBoard sb) {

        if (userWantsToAdd) {
            Score newScore = new Score(name, currentPoints);
            sb.addScore(newScore);

            Scanner doesUserWantDisplay = new Scanner(System.in);
            System.out.print("\nDo you want to view a sorted scoreboard with your score? \n-Enter true for yes, false"
                    + " for no. ");

            if (doesUserWantDisplay.nextBoolean()) {

                sb.rankScoreboard();
                new ScoreboardUI().printScoreBoardHelper(sb);
                int rank = sb.determineRank(newScore);
                int allGames = sb.scoreboardSize();

                if (new ScoreboardUI().printRankIfUserAsks()) {

                    new ScoreboardUI().printPerformanceStats(rank, allGames);
                }
            }

            if (new ScoreboardUI().userWantsToDelete()) {
                sb.deleteScore(newScore);
                new ScoreboardUI().printScoreBoardHelper(sb);
            }

        }

    }


    public static void runStroopEffect() {

        boolean keepGameGoing = true;
        Word w1 = new Word();
        Score currentScore = new Score();

        while (keepGameGoing) {
            String color = w1.chooseRandomColorName();
            System.out.println("\n");
            delayTime(500);
            System.out.println(w1.ansiCodeOfColor(color) + w1.chooseSpellingOfColor());
            System.out.println("\n");
            Scanner userAnswer = new Scanner(System.in);
            Answer firstAnswer = new Answer();

            String whatUserEntered = userAnswer.next();

            if (firstAnswer.isUserAnswerCorrect(whatUserEntered, color)) {
                currentScore.updatePointCount();

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


    public static void printCountdown() {
        System.out.println("3...");
        delayTime(1000);
        System.out.println("2...");
        delayTime(1000);
        System.out.println("1...");
        delayTime(100);
        System.out.println("\nGo!");
        delayTime(200);
    }

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

    public static void makeNextGames() {

        if (startGame()) {
            System.out.println("\nOkay! Game starting in.. ");
            delayTime(300);
            printCountdown();
            runStroopEffect();

        } else {
            System.out.println("\nGame exited. Thank you for playing!");

        }

    }

}

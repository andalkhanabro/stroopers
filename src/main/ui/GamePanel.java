package ui;

import model.Answer;
import model.Score;
import model.ScoreBoard;
import model.WordColour;
import java.util.Scanner;

public class GamePanel {

    static ScoreBoard scoreboard = new ScoreBoard();

    public static void delayTime(int t) throws InterruptedException {
        Thread.sleep(t);

    }

    public static boolean startGame() {
        System.out.print("\nDo you want to play again? ");
        Scanner askUser = new Scanner(System.in);
        return askUser.nextBoolean();
    }

    public static boolean startGameFirst() {
        System.out.println("\nWelcome to Stroopers! Fight the confusion.. \n \n- Enter true to play! "
                + "\n- Enter false to exit! ");
        Scanner askUser = new Scanner(System.in);
        return askUser.nextBoolean();
    }


    public static void runStroopEffect() throws InterruptedException {

        boolean keepGameGoing = true;
        WordColour w1 = new WordColour();
        Score currentScore = new Score();

        while (keepGameGoing) {
            String color = w1.chooseRandomColorName();
            System.out.println("\n");
            delayTime(500);
            System.out.println(w1.ansiCodeOfColor(color) + w1.chooseSpellingOfColor());
            System.out.println("\n");
            Scanner userAnswer = new Scanner(System.in);
            Answer firstAnswer = new Answer();

            if (firstAnswer.isUserAnswerCorrect(userAnswer.next(), color)) {
                currentScore.updatePointCount();

            } else {
                keepGameGoing = false;
                new ScoreUI().printScoreDetails(currentScore);
                String name = new ScoreboardUI().getNameFromUser();

                scoreboard.askAfterGameEnds(name, new ScoreboardUI().userWantsToAdd(), currentScore.getPoints());
                makeNextGames();
            }
        }
    }

    public static void printCountdown() throws InterruptedException {
        System.out.println("3...");
        delayTime(1000);
        System.out.println("2...");
        delayTime(1000);
        System.out.println("1...");
        delayTime(100);
        System.out.println("\nGo!");
        delayTime(200);
    }

    public static void makeFirstGame() throws InterruptedException {

        if (startGameFirst()) {
            System.out.println("\nOkay! Game starting in.. ");
            Thread.sleep(300);
            printCountdown();
            runStroopEffect();

        } else {
            System.out.println("\nGame exited. Thank you for playing!");

        }

    }

    public static void makeNextGames() throws InterruptedException {

        if (startGame()) {
            System.out.println("\nOkay! Game starting in.. ");
            Thread.sleep(300);
            printCountdown();
            runStroopEffect();

        } else {
            System.out.println("\nGame exited. Thank you for playing!");

        }

    }

}

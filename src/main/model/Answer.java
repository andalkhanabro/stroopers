package model;

import java.lang.reflect.Array;

// An answer for each word of the Stroop Game

public class Answer {

    // REQUIRES: trueColor cannot be null
    // EFFECTS: Generates correct answer for word based on its true color
    public Character generateCorrectAnswer(String trueColor) {

        // creates an array of characters of the trueColor
        char[] asArray = trueColor.toCharArray();

        // returns the first letter of the trueColor
        return (Character) Array.get(asArray, 0);
    }

    // REQUIRES: userInput and trueColor cannot be null
    /* EFFECTS: Compares user answer to true answer and returns true if letter matches in lower/uppercase and is
    *          a single letter, else false
    */

    public boolean isUserAnswerCorrect(String userInput, String trueColor) {

        Character correctAnswer = (generateCorrectAnswer(trueColor));

        // checks if userInput is a single letter
        boolean isOneLetter = (userInput.length() == 1);

        // determines first letter of the userInput
        char first = userInput.charAt(0);

        return ((first == correctAnswer || first == Character.toUpperCase(correctAnswer)) && isOneLetter);

    }


}

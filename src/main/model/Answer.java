package model;

import java.lang.reflect.Array;

public class Answer {

    // EFFECTS: Generates correct answer for word on terminal
    public Character generateCorrectAnswer(String trueColor) { // the input to this would be chooseRandomColorName

        char[] asArray = trueColor.toCharArray();

        return (Character) Array.get(asArray, 0);
    }

    // EFFECTS: Compares user answer to true answer and returns true if answer matches, else false
    public boolean isUserAnswerCorrect(String userInput, String trueColor) {

        char first = userInput.charAt(0);
        return first == (generateCorrectAnswer(trueColor));

    }





}

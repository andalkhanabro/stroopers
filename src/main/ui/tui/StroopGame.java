package ui.tui;

import java.io.FileNotFoundException;

public class StroopGame {

    // EFFECTS: Prompts an instance of the Stroop Game
    public static void main(String[] args) {
        try {
            GamePanel.makeFirstGame();
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("No data has been saved previously! A new scoreboard will be made.");
        }

    }
}
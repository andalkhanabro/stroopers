package ui.gui;

import javax.swing.*;
import java.awt.*;

// A class to handle some scoreboard GUI components
public class ScoreBoardGUI {

    private static JButton proceedToScoreBoard;

    // MODIFIES: this
    // EFFECTS: returns a skip button to be displayed on the screen
    public static JButton makeProceedToScoreboardButton() {

        proceedToScoreBoard = new JButton();
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        proceedToScoreBoard.setFont(font);
        proceedToScoreBoard.setText("SKIP");
        proceedToScoreBoard.setBounds(220, 400, 150, 50);

        return proceedToScoreBoard;
    }















}

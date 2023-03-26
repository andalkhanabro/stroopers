package ui;

import javax.swing.*;
import java.awt.*;

public class ScoreBoardGUI {

    private static JButton proceedToScoreBoard;

    private static JButton determineRankButton;

    private static JList scoreboardDisplay;


    public static JButton makeProceedToScoreboardButton() {

        proceedToScoreBoard = new JButton();
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        proceedToScoreBoard.setFont(font);
        proceedToScoreBoard.setText("SKIP");
        proceedToScoreBoard.setBounds(220, 400, 150, 50); //TODO: fix the positioning on main frame

        return proceedToScoreBoard;
    }

    public static JButton determineRankButton() {

        determineRankButton = new JButton();
        Font font = new Font("Comic Sans", Font.BOLD, 20);
        determineRankButton.setFont(font);
        determineRankButton.setText("SKIP");
        determineRankButton.setBounds(200, 400, 150, 50); //TODO: fix the positioning on main frame

        return determineRankButton;
    }














}

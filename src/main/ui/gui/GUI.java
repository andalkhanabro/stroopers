package ui.gui;

import model.*;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

// A class which handles GUI for Stroopers
public class GUI implements KeyListener, WindowListener {
    private static JFrame frame;
    private static JButton playGameButton;
    private static JLabel gameName;
    private static JLabel backgroundImage;
    private static JLabel numberOne;
    private static JLabel currentWordLabel;
    private static JLabel numberThree;
    private static JLabel goLabel;
    private static JTextArea gameInstructions;
    private static JLabel numberTwo;
    private static JButton addButton;
    private static JButton loadGameButton;
    private static JTextField nameField;
    private static JLabel rulesIcon;
    private String correctAnswer;
    private Character singleKeyEquivalentString;
    private boolean keyEventSaved = true;
    private static ScoreBoard scoreboard = new ScoreBoard();
    private static Score score;
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private static JLabel bgImage;
    private static final String JSON_STORE = "./data/scoreboard.json"; // destination where scoreboard will be saved

    private String[] fontStyles = {"Veranda", "Times New Roman", "Sans Serif", "American TypeWriter", "Impact",
            "Zapfino", "NotoSansJavanese-Regular"};
    private static final Color orange = new Color(255,128,0);

    //private static EventLog myLog = EventLog.getInstance();



    // MODIFIES: this
    // EFFECTS: starts the game for the first time when the play button is pressed
    public void startApp() {

        jsonReader = new JsonReader(JSON_STORE);

        setupFrame();

        makePlayButtonWithIcon();

        processLoadButton();

        makeGameLabel();

        setBackGroundImage();

        circleIconForNumbers();

        addComponents();

        startWhenPlayButtonPressed();
    }


    // EFFECTS: starts game again if user presses play button again
    public void startAppAgain() {

        setupFrame();

        makePlayButtonWithIcon();

        makeGameLabel();

        setBackGroundImage();

        addComponents();

        startWhenPlayButtonPressed();

    }


    // MODIFIES: this
    // EFFECTS: sets up the frame and uses absolute positioning as a layout
    public void setupFrame() {
        frame = new JFrame("Stroop Game");
        frame.setSize(800,500); // sets size of window
      //  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // what happens when red cross is pressed
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); // absolute positioning of components on J_FRAME.
        frame.setResizable(false);
        frame.addWindowListener(this);
    }


    // EFFECTS: makes the play button for the screen and sets the play icon
    public static void makePlayButtonWithIcon() {
        playGameButton = new JButton();
        playGameButton.setText("PLAY");
        Font playGameFont = new Font("Comic Sans", Font.BOLD,22);
        playGameButton.setFont(playGameFont);
        playGameButton.setBounds(220,250,150, 50);
        Color custom = new Color(255,255,204);
        playGameButton.setForeground(Color.BLACK);
        playGameButton.setBackground(custom);
        playGameButton.setIcon(new ImageIcon("data/playButton2.jpg"));
    }


    // EFFECTS: makes the game label STROOPERS that is displayed at the start of the game
    public static void makeGameLabel() {
        gameName = new JLabel();
        gameName.setText("STROOPERS");
        Font gameNameFont = new Font("Comic Sans", Font.BOLD,80);

        gameName.setFont(gameNameFont);
        gameName.setForeground(Color.white);
        gameName.setBounds(160,130,500, 100);

    }


    // EFFECTS: makes a label to display the background image of the game
    public static void setBackGroundImage() {
        backgroundImage = new JLabel();
        backgroundImage.setBounds(0, 0, 800, 500);
        backgroundImage.setIcon(new ImageIcon("data/resizedStroopersBg.jpg"));

    }

    // EFFECTS: makes a label for the image of the circle to encircle the countdown numbers
    public static void circleIconForNumbers() {
        bgImage = new JLabel();
        ImageIcon circle = new ImageIcon("data/numberBg.jpg");
        bgImage.setIcon(circle);
        bgImage.setBounds(90,-50,600,552);
    }


    // MODIFIES: this
    // EFFECTS: adds all starting components and buttons to the frame
    public static void addComponents() {

        frame.add(backgroundImage);
        backgroundImage.add(playGameButton);
        backgroundImage.add(gameName);
        backgroundImage.add(loadGameButton);
        frame.setVisible(true);
        frame.setResizable(true);
    }


    // MODIFIES: this
    // EFFECTS: resets frame and repaints it to be a black screen
    public static void resetToInitialState() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.getContentPane().setBackground(Color.BLACK);
    }



    // MODIFIES: this
    // EFFECTS: shows game instructions, countdown numbers and starts displaying words after certain time delays in each
    public void displayCountDownToGame() throws InterruptedException {

        showGameInstructions();

        makeCountDownNumbers();

        Timer timer0 = new Timer();
        TimerTask task0 = new TimerTask() {
            @Override
            public void run() {
                frame.remove(gameInstructions);
                frame.remove(rulesIcon);
                frame.revalidate();
                frame.repaint();
                frame.add(bgImage);
                bgImage.add(numberThree);

            }
        };

        timer0.schedule(task0, 15000);

        addTwoRemoveThree();

        addOneRemoveTwo();

        makeGoLabel();

        removeOneAndGo();

        displayRandomStroopWord();

    }

    // EFFECTS: makes countdown numbers for the frame
    public static void makeCountDownNumbers() {

        numberThree = new JLabel("3");
        numberThree.setFont(new Font("Comic Sans", Font.BOLD, 70));
        numberThree.setForeground(orange);
        numberThree.setBounds(275,185, 200,200);

        numberTwo = new JLabel("2");
        numberTwo.setFont(new Font("Comic Sans", Font.BOLD, 70));
        numberTwo.setForeground(orange);
        numberTwo.setBounds(275,185, 200,200);

        numberOne = new JLabel("1");
        numberOne.setFont(new Font("Comic Sans", Font.BOLD, 70));
        numberOne.setForeground(orange);
        numberOne.setBounds(275,185, 200,200);

    }

    // MODIFIES: this
    // EFFECTS: removes the number three from the screen, repaints frame and displays the number three after some time
    public static void addTwoRemoveThree() {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                bgImage.remove(numberThree);
                resetToInitialState();
                frame.add(bgImage);
                bgImage.add(numberTwo);
            }
        };

        timer.schedule(task, 17000);

    }


    // MODIFIES: this
    // EFFECTS: removes the number two from the screen, repaints frame and displays the number two after some time
    public static void addOneRemoveTwo() {

        Timer timer2 = new Timer();

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                bgImage.remove(numberTwo);
                frame.remove(numberTwo);
                resetToInitialState();
                frame.add(bgImage);
                bgImage.add(numberOne);
            }
        };

        timer2.schedule(task2, 19000);

    }

    // EFFECTS: makes a go label for the frame
    public static void makeGoLabel() {

        goLabel = new JLabel("GO!");
        goLabel.setFont(new Font("Comic Sans", Font.BOLD, 50));
        goLabel.setBounds(350,200,100, 50);
        goLabel.setForeground(Color.YELLOW);

    }


    // MODIFIES: this
    // EFFECTS: removes the number one from the screen, repaints frame and adds the GO Label
    public static void removeOneAndGo() {

        Timer timer3 = new Timer();

        TimerTask task3 = new TimerTask() {
            @Override
            public void run() {
                bgImage.remove(numberOne);
                resetToInitialState();
                frame.revalidate();
                frame.repaint();
                frame.add(goLabel);
            }
        };

        timer3.schedule(task3, 21000);

    }


    // MODIFIES: this
    // EFFECTS: resets frame when play button is pressed and starts countdown for the game
    public void startWhenPlayButtonPressed() {

        playGameButton.addActionListener(e -> {
            resetToInitialState();
            frame.setResizable(false);
            try {
                displayCountDownToGame();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    // EFFECTS: returns an index for the font styles array randomly
    public int randomFontStyleIndex() {
        Random colorIndex = new Random();
        return colorIndex.nextInt(7);
    }


    // EFFECTS: chooses a font style at random
    public String chooseFontStyleAtRandom() {
        return (String) Array.get(fontStyles, randomFontStyleIndex());
    }


    // MODIFIES: this
    // EFFECTS: displays game instructions on the screen with a RULES heading
    public static void showGameInstructions() {

        gameInstructions = new JTextArea();
        gameInstructions.setText("A word will be displayed on the screen. It will spell a color and have a color."
                       + " To earn a point, you will have to enter the first letter of the true color."
                + " For example, if the word yellow is displayed in a red font color, "
                + "the actual answer would be the R key, not the Y key."
                + " Each correct answer will get you 1 point and the game will end on the first incorrect answer."
                + " The game will begin in 10 seconds. So, fight the confusion..");
        gameInstructions.setFont(new Font("Times New Roman", Font.BOLD, 25));
        gameInstructions.setBounds(100,170, 600, 400);
        gameInstructions.setLineWrap(true);
        gameInstructions.setWrapStyleWord(true);
        gameInstructions.setAlignmentX(100);
        gameInstructions.setAlignmentY(100);
        gameInstructions.setEditable(false);
        Color customColor = new Color(153,255,204);
        gameInstructions.setBackground(Color.black);
        gameInstructions.setForeground(customColor);

        ImageIcon rulesSticker = new ImageIcon("data/rulesImg.png");
        rulesIcon = new JLabel(rulesSticker);
        rulesIcon.setBounds(210,0,350,170);
        frame.add(rulesIcon);
        frame.add(gameInstructions);

    }


    // MODIFIES: this, currentWord
    // EFFECTS: displays words recursively with randomised font colors and spellings on the screen and generates
    // equivalent correct answers for each word
    public void displayRandomStroopWord() {

        jsonWriter = new JsonWriter(JSON_STORE);

        Word currentWord = new Word();
        String trickSpelling = currentWord.chooseRandomColorName();
        String trueSpelling = new MyCustomColors().chooseSpellingOfColor();

        currentWordLabel = new JLabel(trickSpelling);
        currentWordLabel.setFont(new Font("Comic Sans", Font.BOLD, 48));

        Color color = Color.decode(trueSpelling);
        currentWordLabel.setForeground(color);
        currentWordLabel.setBounds(310, 130, 200, 200);

        startProducingWords();

        correctAnswer = new MyCustomColors().getNameOfColor(trueSpelling);

        singleKeyEquivalentString = new Answer().generateCorrectAnswer(correctAnswer);

        score = new Score();

        frame.requestFocusInWindow();
        frame.addKeyListener(this);

    }


    // MODIFIES: this
    // EFFECTS: removes GO label and repaints frame to add the word label after a time delay
    public void startProducingWords() {

        Timer timer4 = new Timer();

        TimerTask task4 = new TimerTask() {
            @Override
            public void run() {
                frame.remove(goLabel);
                frame.revalidate();
                frame.repaint();
                frame.add(currentWordLabel);
            }
        };

        timer4.schedule(task4, 24000);

    }


    // EFFECTS:
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // MODIFIES: this
    // EFFECTS: checks if key event is saved, and then key event matches the correct answer, else prompts game to be
    // over
    public void keyPressed(KeyEvent e) {

        if (!keyEventSaved) {
            return;
        }

        keyEventSaved = false;

        boolean isMatching = (e.getKeyChar() == singleKeyEquivalentString);

        if (isMatching) {
            score.updatePointCount();
            resetToInitialState();
            nextWords();
        } else {
            frame.removeKeyListener(this);
            gameOverScreen();
        }

    }

    // EFFECTS: checks if key is released
    @Override
    public void keyReleased(KeyEvent e) {

        keyEventSaved = true;

    }

    // MODIFIES: this
    // EFFECTS: makes next words, checks user's key event with correct answer and displays words at random
    public void nextWords() {

        Word currentWord = new Word();
        String trickSpelling = currentWord.chooseRandomColorName();
        String trueSpelling = new MyCustomColors().chooseSpellingOfColor();

        currentWordLabel = new JLabel(trickSpelling);
        currentWordLabel.setFont(new Font(chooseFontStyleAtRandom(), Font.BOLD, 50));
        Color color = Color.decode(trueSpelling);
        currentWordLabel.setForeground(color);
        currentWordLabel.setBounds(310, 130, 200, 200);

        frame.add(currentWordLabel);

        String hexOfCorrect = new MyCustomColors().convertColorToHexaDecimal(color);
        correctAnswer = new MyCustomColors().getNameOfColor(hexOfCorrect);

        Answer currentAnswer = new Answer();

        singleKeyEquivalentString = currentAnswer.generateCorrectAnswer(correctAnswer);

        frame.requestFocusInWindow();

    }

    // MODIFIES: this
    // EFFECTS: displays game summary and ends game
    public void gameOverScreen() {
        resetToInitialState();
        int thisScore = score.getPoints();
        JLabel gameOverLabel = new JLabel("Game Over! " + "You scored " + thisScore + " points.");
        gameOverLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        gameOverLabel.setForeground(Color.PINK);
        gameOverLabel.setBounds(210, 130, 500, 200);
        frame.add(gameOverLabel);
        addUserAndSave();
    }


    // MODIFIES: this
    // EFFECTS: makes input field for user to enter name after game is over
    public void makeNameField() {

        nameField = new JTextField();
        nameField.setName("Enter your name below.");
        nameField.setHorizontalAlignment(JTextField.CENTER);
        TitledBorder enterName = BorderFactory.createTitledBorder("Enter your name: ");
        nameField.setBorder(enterName);
        nameField.setBounds(275,0, 200, 50);
        frame.add(nameField);

    }

    // MODIFIES: this
    // EFFECTS: makes add user and save button
    public void makeAddUserButton() {

        addButton = new JButton();
        addButton.setText("ADD USER AND SAVE");
        Font font = new Font("Comic Sans", Font.BOLD, 10);
        addButton.setFont(font);
        addButton.setBounds(380, 400, 150, 50);
        frame.add(addButton);

    }

    // MODIFIES: this
    // EFFECTS: adds user profile to board and saves scoreboard if button is pressed
    public void addUserAndSave() {

        makeNameField();
        makeAddUserButton();

        addButton.addActionListener(e -> {
            resetToInitialState();
            String userName = nameField.getText();
            score = new Score(userName, score.getPoints());
            scoreboard.addScore(score);
            scoreboard.sortScoreBoard();
            saveOldScoreboard();
            makeScoreBoardScreen();
        });

        makeSkipButton();

    }



    // MODIFIES: this
    // EFFECTS: skips adding user if pressed, resets frame and displays scoreboard screen
    public void makeSkipButton() {

        JButton proceed = ScoreBoardGUI.makeProceedToScoreboardButton();
        frame.add(proceed);
        proceed.addActionListener(e -> {
            resetToInitialState();
            makeScoreBoardScreen();

        });

    }


    // MODIFIES: this
    // EFFECTS: adds all buttons and their functionalities to make a scoreboard screen by calling other methods
    public void makeScoreBoardScreen() {
        displayScoreboardButton();
        makePlayAgainButton();
        determineRankButton();
        exitButton();
    }


    // MODIFIES: this
    // EFFECTS: makes display scoreboard button and displays board if pressed
    public static void displayScoreboardButton() {

        JButton displayScoreboardButton = new JButton();
        displayScoreboardButton.setText("DISPLAY BOARD");
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        displayScoreboardButton.setFont(font);
        displayScoreboardButton.setBounds(228, 400, 150, 50);
        frame.add(displayScoreboardButton);

        displayScoreboardButton.addActionListener(e -> makeScoreboardList()
        );

    }


    // MODIFIES: this
    // EFFECTS: makes play again button, and restarts game if user presses it
    public void makePlayAgainButton() {

        JButton playAgainButton = new JButton();
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        playAgainButton.setFont(font);
        playAgainButton.setText("PLAY AGAIN");
        playAgainButton.setBounds(59, 400, 150, 50);

        frame.add(playAgainButton);
        playAgainButton.addActionListener(e -> startAppAgain());
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

    // MODIFIES: scoreboard
    // EFFECTS: loads old scoreboard from file
    private static ScoreBoard loadOldScoreboard() {
        try {
            scoreboard = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Cannot read from the given file path " + JSON_STORE);
        }
        return scoreboard;

    }


    // MODIFIES: this
    // EFFECTS: makes a load game button, loads old scoreboard if pressed and displays confirmation message to user
    // on screen
    public static void processLoadButton() {

        loadGameButton = new JButton();

        loadGameButton.setText("LOAD GAME");
        Font loadGameFont = new Font("Comic Sans", Font.BOLD,20);
        loadGameButton.setFont(loadGameFont);
        loadGameButton.setBounds(380,250,170, 50);
        loadGameButton.setForeground(Color.BLACK);
        Color custom = new Color(255,255,204);
        loadGameButton.setBackground(custom);

        loadGameButton.addActionListener(e -> {
            loadOldScoreboard();
            JOptionPane.showMessageDialog(null, "The old scoreboard has been loaded!");
            loadGameButton.setEnabled(false);
        });

    }

    // MODIFIES: this
    // EFFECTS: maps scores to entries, and displays scoreboard as a scrollable table with a scoreboard heading
    public static void makeScoreboardList() {
        List<String> scoreboardEntries = scoreboard.mapScoresToScoreEntries();

        DefaultTableModel scoreboardModel = new DefaultTableModel(new Object[]{"SCOREBOARD"}, 0);

        JTable table = new JTable(scoreboardModel);

        table.setBackground(Color.YELLOW);

        for (String scoreEntry : scoreboardEntries) {
            ((DefaultTableModel) table.getModel()).addRow(new Object[]{scoreEntry});
        }

        table.setDefaultEditor(Object.class,null);

        JLabel header = (JLabel) table.getTableHeader().getDefaultRenderer();
        header.setHorizontalAlignment(JLabel.CENTER);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50,50,700,300);
        frame.add(scrollPane);
    }


    // MODIFIES: this
    // EFFECTS: makes a rank button, determines rank and displays rank on the screen if button is pressed
    public static void determineRankButton() {

        JButton determineRankButton = new JButton();
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        determineRankButton.setFont(font);
        determineRankButton.setText("RANK");
        determineRankButton.setBounds(396, 400, 150, 50);
        frame.add(determineRankButton);

        int totalPlayers = scoreboard.scoreboardSize();
        int rank = scoreboard.determineRank(score);

        String displayMessage = "Your rank is " + rank + " out of " + totalPlayers  + " players!";

        determineRankButton.addActionListener(e -> JOptionPane.showMessageDialog(null, displayMessage));


    }


    // EFFECTS: prints the events from the event log as strings to the console
    public static void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n\n");
        }
    }


    // MODIFIES: this
    // EFFECTS: makes an exit game button, and exits the program if user presses it
    public static void exitButton() {

        JButton exitGameButton = new JButton();
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        exitGameButton.setFont(font);
        exitGameButton.setText("EXIT GAME");
        exitGameButton.setBounds(566, 400, 150, 50);
        exitGameButton.setEnabled(false);
        frame.add(exitGameButton);
        exitGameButton.addActionListener(e -> {
            System.exit(0);
        });

    }


    @Override
    public void windowOpened(WindowEvent e) {

    }

    // EFFECTS: prints events to console when user quits the application
    @Override
    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
        System.exit(0);

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}

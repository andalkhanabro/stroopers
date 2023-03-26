package ui;

import model.Answer;
import model.Score;
import model.ScoreBoard;
import model.Word;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class GUI implements KeyListener {

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
    private Score score;
    private static JsonWriter jsonWriter;
    private static JsonReader jsonReader;
    private static final String JSON_STORE = "./data/scoreboard.json"; // destination where scoreboard will be saved

    public static void main(String[] args) {

        GUI myGUI = new GUI();
        myGUI.startApp();

    }

    public void startApp() {

        jsonReader = new JsonReader(JSON_STORE);
        new ScoreboardUI().printScoreBoardHelper(scoreboard);

        setupFrame();

        makePlayButtonWithIcon();

        processLoadButton();

        makeGameLabel();

        setBackGroundImage();

        addComponents();

        startWhenPlayButtonPressed();

    }


    public void startAppAgain() {

        setupFrame();

        makePlayButtonWithIcon();

        makeGameLabel();

        setBackGroundImage();

        addComponents();

        startWhenPlayButtonPressed();

    }

    public static void setupFrame() {
        frame = new JFrame("Stroop Game"); // makes main window
        frame.setSize(800,500); // sets size of window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // what happens when red cross is pressed
        frame.setLocationRelativeTo(null);
        frame.setLayout(null); // absolute positioning of components on J_FRAME.
        frame.setResizable(false);
    }

    public static void makePlayButtonWithIcon() {
        playGameButton = new JButton();
        playGameButton.setText("PLAY");
        Font playGameFont = new Font("Comic Sans", Font.BOLD,20);
        playGameButton.setFont(playGameFont);
        playGameButton.setBounds(220,250,150, 50);
        Color custom = new Color(255,255,204);
        playGameButton.setForeground(Color.BLACK);
        playGameButton.setBackground(custom);
        playGameButton.setIcon(new ImageIcon("data/playButton2.jpg"));
    }

    public static void makeGameLabel() {
        gameName = new JLabel();
        gameName.setText("STROOPERS");
        Color custom = new Color(255,255,204);
        Font gameNameFont = new Font("Comic Sans", Font.BOLD,80);

        gameName.setFont(gameNameFont);
        gameName.setForeground(custom);
        gameName.setBounds(160,130,500, 100);

    }

    public static void setBackGroundImage() {
        backgroundImage = new JLabel();
        backgroundImage.setBounds(0, 0, 800, 500);
        backgroundImage.setIcon(new ImageIcon("data/resizedStroopersBg.jpg"));

    }

    public static void addComponents() {

        frame.add(backgroundImage);
        backgroundImage.add(playGameButton);
        backgroundImage.add(gameName);
        backgroundImage.add(loadGameButton);
        frame.setVisible(true);
        frame.setResizable(true);
    }

    public static void resetToInitialState() {
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.getContentPane().setBackground(Color.BLACK);
    }

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
                frame.add(numberThree);
            }
        };

        timer0.schedule(task0, 15000);

        addTwoRemoveThree();

        addOneRemoveTwo();

        makeGoLabel();

        removeOneAndGo();

        displayRandomStroopWord();

    }

    public static void makeCountDownNumbers() {

        numberThree = new JLabel("3");
        numberThree.setFont(new Font("Comic Sans", Font.BOLD, 50));
        numberThree.setForeground(Color.PINK);
        numberThree.setBounds(380,130, 200,200);

        numberTwo = new JLabel("2");
        numberTwo.setFont(new Font("Comic Sans", Font.BOLD, 50));
        numberTwo.setForeground(Color.YELLOW);
        numberTwo.setBounds(380,130, 200,200);

        numberOne = new JLabel("1");
        numberOne.setFont(new Font("Comic Sans", Font.BOLD, 50));
        numberOne.setForeground(Color.BLUE);
        numberOne.setBounds(380,130, 200,200);

    }

    public static void addTwoRemoveThree() {

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                frame.remove(numberThree);
                frame.revalidate();
                frame.repaint();
                frame.add(numberTwo);
            }
        };

        timer.schedule(task, 17000);

    }

    public static void addOneRemoveTwo() {

        Timer timer2 = new Timer();

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                frame.remove(numberTwo);
                frame.revalidate();
                frame.repaint();
                frame.add(numberOne);
            }
        };

        timer2.schedule(task2, 19000);

    }

    public static void makeGoLabel() {

        goLabel = new JLabel("GO!");
        goLabel.setFont(new Font("Comic Sans", Font.BOLD, 50));
        goLabel.setBounds(350,200,100, 50);
        goLabel.setForeground(Color.YELLOW);

    }

    public static void removeOneAndGo() {

        Timer timer3 = new Timer();

        TimerTask task3 = new TimerTask() {
            @Override
            public void run() {
                frame.remove(numberOne);
                frame.revalidate();
                frame.repaint();
                frame.add(goLabel);
            }
        };

        timer3.schedule(task3, 21000);

    }


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

    public void displayRandomStroopWord() {

        jsonWriter = new JsonWriter(JSON_STORE);

        Word currentWord = new Word();
        String trickSpelling = currentWord.chooseRandomColorName();
        String trueSpelling = new MyCustomColors().chooseSpellingOfColor();

        currentWordLabel = new JLabel(trickSpelling);
        currentWordLabel.setFont(new Font("Comic Sans", Font.BOLD, 50));
        Color color = Color.decode(trueSpelling);
        currentWordLabel.setForeground(color);
        currentWordLabel.setBounds(310, 130, 200, 200);

        startProducingWords();

        correctAnswer = new MyCustomColors().getNameofColor(trueSpelling);

        singleKeyEquivalentString = new Answer().generateCorrectAnswer(correctAnswer);

        score = new Score();

        frame.requestFocusInWindow();
        frame.addKeyListener(this);

    }


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

    @Override
    public void keyTyped(KeyEvent e) {

    }

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

    @Override
    public void keyReleased(KeyEvent e) {

        keyEventSaved = true;

    }

    public void nextWords() {

        Word currentWord = new Word();
        String trickSpelling = currentWord.chooseRandomColorName();
        String trueSpelling = new MyCustomColors().chooseSpellingOfColor();

        currentWordLabel = new JLabel(trickSpelling);
        currentWordLabel.setFont(new Font("Comic Sans", Font.BOLD, 50));
        Color color = Color.decode(trueSpelling);
        currentWordLabel.setForeground(color);
        currentWordLabel.setBounds(310, 130, 200, 200);

        frame.add(currentWordLabel);

        String hexOfCorrect = new MyCustomColors().convertColorToHexaDecimal(color);
        correctAnswer = new MyCustomColors().getNameofColor(hexOfCorrect);

        Answer currentAnswer = new Answer();

        singleKeyEquivalentString = currentAnswer.generateCorrectAnswer(correctAnswer);

        frame.requestFocusInWindow();

    }


    public void gameOverScreen() {
        resetToInitialState();
        int thisScore = score.getPoints();
        JLabel gameOverLabel = new JLabel("Game Over! " + "You scored " + thisScore + " points.");
        gameOverLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
        gameOverLabel.setForeground(Color.PINK);
        gameOverLabel.setBounds(210, 130, 500, 200);
        frame.add(gameOverLabel);
        doesUserWantToAdd();
    }

    public void makeNameField() {

        nameField = new JTextField();
        nameField.setName("Enter your name below.");
        nameField.setHorizontalAlignment(JTextField.CENTER);
        TitledBorder enterName = BorderFactory.createTitledBorder("Enter your name: ");
        nameField.setBorder(enterName);
        nameField.setBounds(275,0, 200, 50);
        frame.add(nameField);

    }

    public void makeAddUserButton() {

        addButton = new JButton();
        addButton.setText("ADD USER AND SAVE");
        Font font = new Font("Comic Sans", Font.BOLD, 10);
        addButton.setFont(font);
        addButton.setBounds(380, 400, 150, 50);
        frame.add(addButton);


    }

    public void doesUserWantToAdd() {

        makeNameField();
        makeAddUserButton();

        addButton.addActionListener(e -> {
            resetToInitialState();
            String userName = nameField.getText();
            Score userScore = new Score(userName, score.getPoints());
            scoreboard.addScore(userScore);
            scoreboard.sortScoreBoard();
            saveOldScoreboard();
            new ScoreboardUI().printScoreBoardHelper(scoreboard);
            makeScoreBoardScreen();
        });

        makeSkipButton();


    }

    public void makeSkipButton() {

        JButton proceed = ScoreBoardGUI.makeProceedToScoreboardButton();
        frame.add(proceed);
        proceed.addActionListener(e -> {
            resetToInitialState();
            makeScoreBoardScreen();

        });

    }


    public void makeScoreBoardScreen() {
        makeExitButton();
        makePlayAgainButton();
    }


    public static void makeExitButton() {

        JButton exitButton = new JButton();
        exitButton.setText("EXIT GAME");
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        exitButton.setFont(font);
        exitButton.setBounds(380, 400, 150, 50);
        frame.add(exitButton);

        exitButton.addActionListener(e -> System.exit(0));

    }


    public void makePlayAgainButton() {

        JButton playAgainButton = new JButton();
        Font font = new Font("Comic Sans", Font.BOLD, 15);
        playAgainButton.setFont(font);
        playAgainButton.setText("PLAY AGAIN");
        playAgainButton.setBounds(220, 400, 150, 50);

        frame.add(playAgainButton);
        playAgainButton.addActionListener(e -> startAppAgain());
    }

    private static void saveOldScoreboard() {
        try {
            jsonWriter.open();
            jsonWriter.write(scoreboard);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file while saving to " + JSON_STORE);
        }

    }

    private static ScoreBoard loadOldScoreboard() {
        try {
            scoreboard = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Cannot read from the given file path " + JSON_STORE);
        }
        return scoreboard;

    }

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
            JOptionPane.showMessageDialog(null, "Scoreboard Loaded!");
            loadGameButton.setEnabled(false);
            new ScoreboardUI().printScoreBoardHelper(scoreboard);
        });


    }


    //TODO
    // make new screen displaying a scoreboard using JList. prompt that screen when adduser is pressed.
    // Make button to determine rank as a pop-up message in given scoreboard.
    //












}

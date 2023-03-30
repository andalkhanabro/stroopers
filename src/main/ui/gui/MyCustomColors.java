package ui.gui;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// A class to do color handling of words, randomize them and define new colors
public class MyCustomColors {

    // built-in java colors

    private static final String redHex = new MyCustomColors().convertColorToHexaDecimal(Color.RED);

    private static final String greenHex = new MyCustomColors().convertColorToHexaDecimal(Color.GREEN);

    private static final String whiteHex = new MyCustomColors().convertColorToHexaDecimal(Color.WHITE);

    private static final String blueHex = new MyCustomColors().convertColorToHexaDecimal(Color.BLUE);

    private static final String YellowHex = new MyCustomColors().convertColorToHexaDecimal(Color.YELLOW);

    private static final String cyanHex = new MyCustomColors().convertColorToHexaDecimal(Color.CYAN);


    // additional customized colors for variety using RGB values

    private static final Color orange = new Color(255,128,0);

    private static final Color brown = new Color(102,52,0);

    private static final Color purple = new Color(153,51,255);

    private static final Color pink = new Color(255,51,153);

    private static final Color gray = Color.darkGray;

    private static final String pinkHex = new MyCustomColors().convertColorToHexaDecimal(pink);

    private static final String orangeHex = new MyCustomColors().convertColorToHexaDecimal(orange);

    private static final String brownHex = new MyCustomColors().convertColorToHexaDecimal(brown);

    private static final String purpleHex = new MyCustomColors().convertColorToHexaDecimal(purple);

    private static final String grayHex = new MyCustomColors().convertColorToHexaDecimal(gray);

    private static final String[] colorHexaDecimals = {redHex, greenHex, whiteHex, blueHex, YellowHex,
            pinkHex, orangeHex, cyanHex, brownHex, purpleHex, grayHex};

    private static Map<String, String> hexadecimalsToName = new HashMap<>();

    static {

        hexadecimalsToName.put(redHex, "red");
        hexadecimalsToName.put(greenHex, "green");
        hexadecimalsToName.put(whiteHex, "white");
        hexadecimalsToName.put(blueHex, "blue");
        hexadecimalsToName.put(YellowHex, "yellow");
        hexadecimalsToName.put(pinkHex, "pink");
        hexadecimalsToName.put(cyanHex, "cyan");
        hexadecimalsToName.put(orangeHex, "orange");
        hexadecimalsToName.put(brownHex, "brown");
        hexadecimalsToName.put(purpleHex, "purple");
        hexadecimalsToName.put(grayHex, "gray");

    }

    // EFFECTS: returns an index for the color array at random
    public int randomColorIndex() {
        Random colorIndex = new Random();
        return colorIndex.nextInt(11);
    }

    // EFFECTS: chooses a spelling for the color at random
    public String chooseSpellingOfColor() {
        return (String) Array.get(colorHexaDecimals, randomColorIndex());
    }

    // EFFECTS: returns a hexadecimal representation of a Color object
    public String convertColorToHexaDecimal(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    // EFFECTS: gets name of color with equivalent hexadecimal code from defined color map
    public String getNameOfColor(String hexaDecimalCode) {
        return hexadecimalsToName.get(hexaDecimalCode);
    }







}

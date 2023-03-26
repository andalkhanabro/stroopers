package ui;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.awt.Color.*;

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

    private static final Color gray = new Color(160,160,160);

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


    public int randomColorIndex() {
        Random colorIndex = new Random();
        return colorIndex.nextInt(11);
    }

    public String chooseSpellingOfColor() {
        return (String) Array.get(colorHexaDecimals, randomColorIndex());
    }

    public String convertColorToHexaDecimal(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public String getNameofColor(String hexaDecimalCode) {
        return hexadecimalsToName.get(hexaDecimalCode);
    }







}

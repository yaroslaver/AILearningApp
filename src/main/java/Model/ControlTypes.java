package Model;

import Generator.LogWriter;

import javax.swing.*;
import javax.swing.text.Caret;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public enum ControlTypes {
    //types of controls
    CHECKBOX,
    TEXTFIELD,
    RADIOBUTTON,
    SPINNER,
    SLIDER,
    BUTTON;

    //number of types of controls
    private static final int SIZE = values().length;

    //bounds of number of symbols in control text
    private static final int MIN_CONTROL_VALUE_SIZE = 3;
    private static final int MAX_CONTROL_VALUE_SIZE = 12;

    //bounds of slider size
    private static final int MIN_SLIDER_WIDTH = 80;
    private static final int MAX_SLIDER_WIDTH = 130;
    private static final int MIN_SLIDER_HEIGHT = 40;
    private static final int MAX_SLIDER_HEIGHT = 60;

    //different styles of controls
    private static final String[] LOOK_AND_FEEL = new String[] {
            "com.sun.java.swing.plaf.motif.MotifLookAndFeel",
            "javax.swing.plaf.nimbus.NimbusLookAndFeel",
            "javax.swing.plaf.metal.MetalLookAndFeel",
            "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel",
            "com.alee.laf.WebLookAndFeel",
            "org.gtk.java.swing.plaf.gtk.GtkLookAndFeel",
            "com.birosoft.liquid.LiquidLookAndFeel",
    };

    //min, max and current values set to slider
    private ArrayList<Integer> sliderValues = new ArrayList<>();

    //set of chars to random string consisting of digits and english letters
    private final String LETTERS = "abcdefghijklmnopqrstuvxyz";
    private final char[] CHARS = (LETTERS + LETTERS.toUpperCase() + "0123456789").toCharArray();

    /**
     * @return Component corresponding to current element of enum
     * @param isThreaded is True when multithreading system is used
     */

    public Component getObject(boolean isThreaded) {
        //use different styles of controls only in single-thread processing
        if (!isThreaded) {
            try {
                UIManager.setLookAndFeel(LOOK_AND_FEEL[getRandomInt(0, LOOK_AND_FEEL.length - 1)]);
                System.out.println(UIManager.getLookAndFeel());
            } catch (ClassNotFoundException | InstantiationException
                    | IllegalAccessException | UnsupportedLookAndFeelException e) {
                LogWriter.log(e.getMessage());
            }
        }

        switch (this) {
            //return JCheckBox with random text and random state of selection
            case CHECKBOX: {
                return new JCheckBox(getRandomString(), getRandomBool());
            }

            //return JTextField with random text and random text selection
            case TEXTFIELD: {
                JTextField textField = new JTextField(getRandomString());
                int caretPosition = getRandomInt(0, textField.getText().length());
                textField.setCaretPosition(caretPosition);
                Caret caret = textField.getCaret();
                caret.setSelectionVisible(true);
                textField.setSelectionEnd(getRandomInt(caretPosition, textField.getText().length()));
                return textField;
            }

            //return JRadioButton with random text and random state of selection
            case RADIOBUTTON: {
                return new JRadioButton(getRandomString(), getRandomBool());
            }

            //return JSpinner with random value
            case SPINNER: {
                JSpinner spinner = new JSpinner();
                spinner.setValue(getRandomInt(Integer.MIN_VALUE, Integer.MAX_VALUE - 1));
                return spinner;
            }

            //return JSlider with random min, max and current values,
            //random state of visibility of labels and ticks, random size
            case SLIDER: {
                sliderValues.clear();
                for (int i = 5; i < 11; i += 5) {
                    sliderValues.add(getRandomInt(i - 5, i) * 10 + i * 2 - 10);
                }
                sliderValues.add(getRandomInt(sliderValues.get(0), sliderValues.get(1)));
                JSlider slider = new JSlider(JSlider.HORIZONTAL,
                        sliderValues.get(0), sliderValues.get(1), sliderValues.get(2));
                if (getRandomBool())
                {
                    if (sliderValues.get(1) - sliderValues.get(0) > 50) {
                        slider.setMajorTickSpacing(20);
                        slider.setMinorTickSpacing(10);
                    } else {
                        slider.setMajorTickSpacing(10);
                        slider.setMinorTickSpacing(5);
                    }
                    if (getRandomBool())
                    {
                        slider.setPaintTicks(true);
                    }
                    slider.setPaintLabels(true);
                }
                slider.setSize(getRandomInt(MIN_SLIDER_WIDTH, MAX_SLIDER_WIDTH),
                        getRandomInt(MIN_SLIDER_HEIGHT, MAX_SLIDER_HEIGHT));
                return slider;
            }

            //return JButton with random text
            case BUTTON: {
                return new JButton(getRandomString());
            }

            default: {
                return null;
            }
        }
    }

    /**
     * @return random int in bounds
     * @param min min bound
     * @param max max bound
     */
    public int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * @return random boolean
     */
    public boolean getRandomBool(){
        return ThreadLocalRandom.current().nextBoolean();
    }

    /**
     * @return random string consisting of digits and english letters with length from 3 to 12 symbols
     */
    public String getRandomString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < getRandomInt(MIN_CONTROL_VALUE_SIZE, MAX_CONTROL_VALUE_SIZE); i++) {
            result.append(CHARS[ThreadLocalRandom.current().nextInt(CHARS.length)]);
        }
        return result.toString();
    }

    /**
     * @return random element of enum
     */
    public ControlTypes getRandomObject() {
        return values()[getRandomInt(0, SIZE - 1)];
    }

    /**
     * @return string name of current element of enum
     */
    public String getFolderName() {
        return this.name().toLowerCase();
    }
}

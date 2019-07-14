package Generator;

import Model.ConstCollection;
import Model.ControlTypes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Generator {
    private int R = 0;
    private int G = 0;
    private int B = 0;
    boolean directoryCreated = false;

    public void createSamples(ControlTypes object, Integer amount, boolean contrast, boolean disabledControls, boolean noise, boolean isSorted) {
        if (object != null) {
            for (int i = 0; i < amount; i++) {
                generateImage(object, i, contrast, disabledControls, noise, isSorted);
            }
        } else {
            for (int i = 0; i < amount; i++) {
                ControlTypes newObject = ControlTypes.BUTTON; //Using any as default
                newObject = newObject.getRandomObject(); //Changing to really random object
                generateImage(newObject, i, contrast, disabledControls, isSorted);
                //todo: Make a void to count every single object generated
            }
        }
        //todo: Check valuable isSorted and write to log amount of files generated
    ...
        writeLog(object.getObject().getName());
    }

    private void generateImage(ControlTypes object, int i, boolean contrast, boolean disabledControls, boolean noise, boolean isSorted) {
        Component c = object.getObject(); //Create an object
        generateObject(c, contrast, disabledControls); //Void to set some basic object params
        JFrame frame = new JFrame();
        JPanel TempJPanel = new javax.swing.JPanel();
        int noiceAmount = 0;
        while (generateNumber(0, 3) == 2 && noise) {
            JPanel noice = generateNoice();
            Jpanel1.add(noice);
            noiceAmount++;
        }
        fillFrame(frame, TempJPanel);
        TempJPanel.add(c, noiceAmount + 1, 0); //Adding to our object JPanel
        if ((R + G + B) % 3 > 128) {
            generateRGB(177, 255, R, G, B);
        } else {
            generateRGB(0, 100, R, G, B);
        }
        TempJPanel.setBackground(new Color(R, G, B));
        frame.pack();
        BufferedImage bi = new BufferedImage(TempJPanel.getWidth(), TempJPanel.getHeight(), BufferedImage.TYPE_INT_ARGB); //Create an image of panel
        Graphics2D graphics2D = bi.createGraphics();
        TempJPanel.paint(graphics2D);
        createFolders();
        saveImage(bi, object, i, isSorted);
        frame.dispose();
    }

    private void generateObject(Component c, boolean contrast, boolean disabledControls) {
        Random rand = new Random();
        boolean b;
        if (disabledControls) {
            b = rand.nextBoolean();
        } else b = true;
        c.setEnabled(b);
        generateRGB(170, 255, R, G, B);
        c.setBackground(new Color(R, G, B));
        if ((R + G + B) % 3 > 128) {
            generateRGB(170, 255, R, G, B);
        } else {
            generateRGB(0, 80, R, G, B);
        }
        c.setForeground(new Color(R, G, B));
        int width = generateNumber(ConstCollection.MIN_OBJECT_WIDTH, ConstCollection.MAX_OBJECT_WIDTH);
        int height = generateNumber(ConstCollection.MIN_OBJECT_HEIGHT, ConstCollection.MAX_OBJECT_HEIGHT);
        c.setSize(width, height);
        if (c.getHeight() == 0 && c.getWidth() == 0) {
            int x = rand.nextInt(ConstCollection.IMAGE_WIDTH - width - 5) + 5;
            int y = rand.nextInt(ConstCollection.IMAGE_HEIGHT - 5) + height + 5;
            c.setLocation(x, y);
        }

    }

    private JPanel generateNoise() {
        JPanel temp = new JPanel();
        temp.setBounds(
                generateNumber(0, ConstCollection.IMAGE_WIDTH),
                generateNumber(0, ConstCollection.IMAGE_HEIGHT),
                generateNumber(ConstCollection.MIN_NOISE_WIDTH, ConstCollection.MAX_NOISE_WIDTH),
                generateNumber(ConstCollection.MIN_NOISE_HEIGHT, ConstCollection.MAX_NOISE_HEIGHT)
        );
        generateRGB(0, 255, R, G, B);
        temp.setBackground(new Color(R, G, B));
        return temp;
    }

    private void fillFrame(JFrame frame, JPanel TempJPanel) {
        frame.setPreferredSize(new Dimension(ConstCollection.IMAGE_WIDTH, ConstCollection.IMAGE_HEIGHT));
        frame.setBounds(0, 0, ConstCollection.IMAGE_WIDTH, ConstCollection.IMAGE_HEIGHT);
        frame.setUndecorated(true);
        TempJPanel.setLayout(null);
        frame.getContentPane().add(TempJPanel);
    }

    private Integer generateNumber(int min, int max) {
        Random rand = new Random();
        Integer number = rand.nextInt(max - min) + min;
        return number;
    }

    private void generateRGB(int min, int max, int R, int G, int B) {
        this.R = generateNumber(min, max);
        this.G = generateNumber(min, max);
        this.B = generateNumber(min, max);
    }

    private void createFolders() {
        if (directoryCreated == false) {
            File folder = new File(ConstCollection.PATH);
            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Directory Dataset is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            File folderUnsorted = new File(ConstCollection.PATH + "/" + "unsorted");
            if (!folderUnsorted.exists()) {
                if (folderUnsorted.mkdir()) {
                    System.out.println("Directory unsorted is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            File folderSorted = new File(ConstCollection.PATH + "/" + "sorted");
            if (!folderSorted.exists()) {
                if (folderSorted.mkdir()) {
                    System.out.println("Directory sorted is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            File folderCheckbox = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "checkbox");
            if (!folderCheckbox.exists()) {
                if (folderCheckbox.mkdir()) {
                    System.out.println("Directory checkbox is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            File folderTextField = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "textfield");
            if (!folderTextField.exists()) {
                if (folderTextField.mkdir()) {
                    System.out.println("Directory textfield is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            File folderRadioBtn = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "radiobutton");
            if (!folderRadioBtn.exists()) {
                if (folderRadioBtn.mkdir()) {
                    System.out.println("Directory radiobutton is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            File folderSpinner = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "spinner");
            if (!folderSpinner.exists()) {
                if (folderSpinner.mkdir()) {
                    System.out.println("Directory spinner is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            File folderSlider = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "slider");
            if (!folderSlider.exists()) {
                if (folderSlider.mkdir()) {
                    System.out.println("Directory slider is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            File folderButton = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "button");
            if (!folderButton.exists()) {
                if (folderButton.mkdir()) {
                    System.out.println("Directory button is created");
                } else {
                    System.out.println("Failed to create directory");
                }
            }
            directoryCreated = true;
        } else {
            return;
        }
    }

    private void saveImage(BufferedImage bi, ControlTypes object, int number, Boolean isSorted) {
        String id = Integer.toString(number);
        String newpath = "ConstCollection.PATH";
        try {
            if (isSorted) {
                File outputfile = new File(ConstCollection.PATH + "/" + "sorted" + "/" + object.getFolderName() + "/" + id + ".png");
                ImageIO.write(bi, "png", outputfile);
            } else {
                File outputfile = new File(ConstCollection.PATH + "/" + "unsorted" + "/" + object.getFolderName() + "_" + id + ".png");
                ImageIO.write(bi, "png", outputfile);
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void writeLog(String name, int amount) {
    ... //todo: Write a file in this folder in any txt format to show how many files of this type were generated
    }
}

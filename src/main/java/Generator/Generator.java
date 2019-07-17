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
  //private int countOfComponents = 6;
  //private int[] components = new int[6];
  boolean directoryCreated = false;
  private int threadNumber;
  private boolean isThreaded = false;
  private int cyclesForProgress = 0;


  public void createSamples(ControlTypes object, int minNumber, int maxNumber, boolean contrast, boolean disabledControls,
                            boolean noise, boolean isSorted, Integer threadNumber) {
    if (!threadNumber.equals(-1)) {
      this.threadNumber = threadNumber;
      isThreaded = true;
    } else {
      this.threadNumber = 1; //need to set this as 1 because it's used as a multiplier in fillFrame void
      isThreaded = false;
    }
    cyclesForProgress = (maxNumber - minNumber) % 100; //uses to update value of generated
    if (object != null) {
      //countControls(object.name(), maxNumber - minNumber);
      int currentCycle = 0;
      for (int i = minNumber; i < maxNumber; i++) {
        generateImage(object, i, contrast, disabledControls, noise, isSorted);
        currentCycle++;
        if (currentCycle == cyclesForProgress){
          GeneratorRetranslator.setProgress(currentCycle);
        }
      }
    } else {
      int currentCycle = 0;
      for (int i = minNumber; i < maxNumber; i++) {
        ControlTypes newObject = ControlTypes.BUTTON; //Using any as default
        newObject = newObject.getRandomObject(); //Changing to really random object
        generateImage(newObject, i, contrast, disabledControls, noise, isSorted);
        currentCycle++;
        if (currentCycle == cyclesForProgress){
          GeneratorRetranslator.setProgress(currentCycle);
        }
        //countControls(newObject.name(), 1);
      }
    }
    //writeAmountOfControlsToLog(components);
  }

  private void generateImage(ControlTypes object, int i, boolean contrast, boolean disabledControls, boolean noise, boolean isSorted) {
    Component c = object.getObject(isThreaded); //Create an object
    generateObject(c, contrast, disabledControls); //Void to set some basic object params
    JFrame frame = new JFrame();
    JPanel TempJPanel = new javax.swing.JPanel();
    int noiseAmount = 0;
    while (generateNumber(0, 3) == 2 && noise) {
      JPanel jNoise = generateNoise();
      TempJPanel.add(jNoise);
      noiseAmount++;
    }
    fillFrame(frame, TempJPanel);
    TempJPanel.add(c, noiseAmount + 1, 0); //Adding to our object JPanel
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
    generateRGB(0, 255, R, G, B);
    c.setBackground(new Color(R, G, B));
    if (contrast) {
      if (Math.round((R + G + B) % 3) > 128) {
        generateRGB(170, 255, R, G, B);
      } else {
        generateRGB(0, 80, R, G, B);
      }
    } else {
      generateRGB(0, 255, R, G, B);
    }
    c.setForeground(new Color(R, G, B));
    if (c.getHeight() == 0 && c.getWidth() == 0) {
      Integer height = generateNumber(ConstCollection.MIN_OBJECT_HEIGHT, ConstCollection.MAX_OBJECT_HEIGHT);
      Integer width = generateNumber(ConstCollection.MIN_OBJECT_WIDTH, ConstCollection.MAX_OBJECT_WIDTH);
      c.setBounds(generateNumber(5, ConstCollection.IMAGE_WIDTH - 5 - width),
              generateNumber(5, ConstCollection.IMAGE_HEIGHT - 5 - height),
              width,
              height);
    } else {
      c.setLocation(generateNumber(5, ConstCollection.IMAGE_WIDTH - 5 - c.getWidth()),
              generateNumber(5, ConstCollection.IMAGE_HEIGHT - 5 - c.getHeight()));
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
    frame.setBounds(
            generateNumber(150*threadNumber, 300 * threadNumber), //Position of invisible frame is set this way to prevent
            generateNumber(150*threadNumber, 300 * threadNumber), //two different frames being on the same position (controls may combine this way then)
            ConstCollection.IMAGE_WIDTH,
            ConstCollection.IMAGE_HEIGHT
    );
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
          LogWriter.log("Directory Dataset is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      File folderUnsorted = new File(ConstCollection.PATH + "/" + "unsorted");
      if (!folderUnsorted.exists()) {
        if (folderUnsorted.mkdir()) {
          LogWriter.log("Directory unsorted is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      File folderSorted = new File(ConstCollection.PATH + "/" + "sorted");
      if (!folderSorted.exists()) {
        if (folderSorted.mkdir()) {
          LogWriter.log("Directory sorted is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      File folderCheckbox = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "checkbox");
      if (!folderCheckbox.exists()) {
        if (folderCheckbox.mkdir()) {
          LogWriter.log("Directory checkbox is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      File folderTextField = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "textfield");
      if (!folderTextField.exists()) {
        if (folderTextField.mkdir()) {
          LogWriter.log("Directory textfield is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      File folderRadioBtn = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "radiobutton");
      if (!folderRadioBtn.exists()) {
        if (folderRadioBtn.mkdir()) {
          LogWriter.log("Directory radiobutton is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      File folderSpinner = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "spinner");
      if (!folderSpinner.exists()) {
        if (folderSpinner.mkdir()) {
          LogWriter.log("Directory spinner is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      File folderSlider = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "slider");
      if (!folderSlider.exists()) {
        if (folderSlider.mkdir()) {
          LogWriter.log("Directory slider is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      File folderButton = new File(ConstCollection.PATH + "/" + "sorted" + "/" + "button");
      if (!folderButton.exists()) {
        if (folderButton.mkdir()) {
          LogWriter.log("Directory button is created");
        } else {
          LogWriter.log("Failed to create directory");
        }
      }
      directoryCreated = true;
    } else {
      return;
    }
  }

  private void saveImage(BufferedImage bi, ControlTypes object, int number, Boolean isSorted) {
    String id = Integer.toString(number);
    String newpath = ConstCollection.PATH;
    try {
      if (isSorted) {
        File outputfile = new File(ConstCollection.PATH + "/" + "sorted" + "/" + object.getFolderName() + "/" + id + ".png");
        ImageIO.write(bi, "png", outputfile);
      } else {
        File outputfile = new File(ConstCollection.PATH + "/" + "unsorted" + "/" + object.getFolderName() + "_" + id + ".png");
        ImageIO.write(bi, "png", outputfile);
      }
    } catch (IOException ex) {
      LogWriter.log(ex.getMessage());
    }
  }

    /*
    private void countControls(String name, int amount) {
        switch (name) {
            case "CHECKBOX": {
                components[0] += amount;
            }
            case "TEXTFIELD": {
                components[1] += amount;
            }
            case "RADIOBUTTON": {
                components[2] += amount;
            }
            case "SPINNER": {
                components[3] += amount;
            }
            case "SLIDER": {
                components[4] += amount;
            }
            case "BUTTON": {
                components[5] += amount;
            }
        }
    }
     */

    /*
    private void writeAmountOfControlsToLog(int[] components) {
        String[] nameOfComponent = {"CHECKBOX", "TEXTFIELD", "RADIOBUTTON", "SPINNER", "SLIDER", "BUTTON"};
        String tempPath = ConstCollection.PATH + "/unsorted/result.txt";
        try {
            FileWriter writer = new FileWriter(tempPath);
            for (int i = 0; i < countOfComponents; i++) {
                if (components[i] != 0) {
                    writer.write(nameOfComponent[i] + " " + components[i] + "\r\n");
                }
            }
            writer.close();
        } catch (IOException ex) {
          LogWriter.log(ex.getMessage());
        }
    }
     */
}
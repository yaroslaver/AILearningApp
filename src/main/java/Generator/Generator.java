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
  private int threadNumber = generateNumber(0, 20);

  public void generateAll(ControlTypes object, Integer firstNumber, Integer LastNumber, Boolean contrast, boolean disabledControls, boolean isSorted, int threadNumber) {
    this.threadNumber = threadNumber;
    if (object != null) {
      for (int i = firstNumber; i < LastNumber; i++) {
        generateImage(object, i, contrast, disabledControls, isSorted);
      }
    } else {
      for (int i = firstNumber; i < LastNumber; i++) {
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

  private void generateImage(ControlTypes object, int i, Boolean contrast, boolean disabledControls, boolean isSorted) {
    Component c = object.getObject(); //Create an object
    generateObject(c, contrast, disabledControls); //Void to set some basic object params
    JFrame frame = new JFrame();
    JPanel TempJPanel = new javax.swing.JPanel();
    fillFrame(frame, TempJPanel);
    TempJPanel.add(c); //Adding to our object JPanel
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
    saveImage(bi, object, i, isSorted); //Void to save an image
    frame.dispose();

  }

  private void generateObject(Component c, boolean contrast, boolean disabledControls) {
    ... //todo: Make a generation of the object we need to draw
  }

  private void fillFrame(JFrame frame, JPanel TempJPanel) {
    frame.setPreferredSize(new Dimension(ConstCollection.IMAGE_WIDTH, ConstCollection.IMAGE_HEIGHT));
    frame.setBounds(0 ,0, ConstCollection.IMAGE_WIDTH, ConstCollection.IMAGE_HEIGHT);
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

  private void saveImage(BufferedImage bi, ControlTypes object, int number, Boolean isSorted) {
    ... //todo: Make an Image Generator, check if file was created, no errors
  }

  private void writeLog(String name, int amount){
    ... //todo: Write a file in this folder in any txt format to show how many files of this type were generated
  }
}
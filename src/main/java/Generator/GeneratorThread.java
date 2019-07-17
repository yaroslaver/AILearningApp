package Generator;

import Model.ConstCollection;
import Model.ControlTypes;

public class GeneratorThread extends Thread implements Runnable {

  private int minNumber = 0;
  private int maxNumber = 0;
  private ControlTypes type = null;
  private boolean contrast = false;
  private boolean disabledControls = false;
  private boolean isSorted = false;
  private int threadNumber;
  private boolean noise = false;

  /**
   * Generating thread
   *
   * @param min              -- min value
   * @param max              -- max value
   * @param type             -- control's type
   * @param contrast         -- if true -- high contrast algorithm should be enabled
   * @param disabledControls -- if true -- allows generator to create disabled controls or checked (in case of radiobutton/checkbox)
   * @param noise            -- if true -- add a chance to generate image with some random noise
   * @param isSorted         -- should be true if we generate sorted images and false if unsorted
   * @param threadNumber     -- number of thread
   */
  public GeneratorThread(int min, int max, ControlTypes type, boolean contrast, boolean disabledControls, boolean noise, boolean isSorted, int threadNumber) {
    this.minNumber = min;
    this.maxNumber = max;
    if (type != null) {
      this.type = type;
    }
    this.noise = noise;
    this.contrast = contrast;
    this.disabledControls = disabledControls;
    this.isSorted = isSorted;
    this.threadNumber = threadNumber + 1;
  }

  @Override
  public void run() {
    LogWriter.log("Thread " + this.threadNumber + " has started");
    Generator gen = new Generator();
    gen.createSamples(type, minNumber, maxNumber, contrast, disabledControls, noise, isSorted, threadNumber);
    LogWriter.log("Thread " + this.threadNumber + " has finished");
  }
}
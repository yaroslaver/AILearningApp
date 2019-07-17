package Generator;

import Model.ConstCollection;
import Model.ControlTypes;

import java.util.ArrayList;

public class GeneratorRetranslator {

    private static int progress = 0;
    /**
     * @param list all types of controls that should be generated, should be null in case of unsorted generation
     * @param amount amount of each control type to be generated, in case of unsorted generation - amount of all unsorted images
     * @param contrast if high contrast algorithm should be enabled or not
     * @param disabledControls if true allows generator to create disabled controls or checked (in case of radiobutton/checkbox)
     * @param noise if true add a chance to generate image with some random noise
     * @param isSorted should be true if we generate sorted images and false if unsorted
     * @param Threaded should be true if multithreading system should be used
     */
    public void startGenerator(ArrayList<ControlTypes> list, int amount, boolean contrast, boolean disabledControls,
                               boolean noise, boolean isSorted, boolean Threaded) {
        if (Threaded) {
            GeneratorThread[] Threads = new GeneratorThread[ConstCollection.THREAD_COUNT];
            LogWriter.log("Generation started in " + ConstCollection.THREAD_COUNT + " threads.");
            if (list == null) {
                int part = (int) amount / ConstCollection.THREAD_COUNT;
                int extra = 0;
                int additionalAmount = amount - part * ConstCollection.THREAD_COUNT;
                for (int i = 0; i < ConstCollection.THREAD_COUNT; i++) { //usable in case amount doesn't divine by thread numbers without any additional number
                    if (i == ConstCollection.THREAD_COUNT - 1) {         //in that case we'll just add additional amount to the last thread
                        extra = additionalAmount;
                    }
                    Threads[i] = new GeneratorThread(
                            part * i, //First number of control where thread should start
                            part * (i + 1) + extra, //last number of control
                            null,
                            contrast,
                            disabledControls,
                            noise,
                            isSorted,
                            i //Thread number
                    );
                    Threads[i].start();
                }
            } else {
                for (ControlTypes temp : list) {
                    int threadNumber = list.indexOf(temp);
                    Threads[threadNumber] = new GeneratorThread(
                            0,
                            amount,
                            temp,
                            contrast,
                            disabledControls,
                            noise,
                            isSorted,
                            threadNumber
                    );
                    Threads[threadNumber].start();
                }

                for (Thread currThread : Threads) {
                    while (currThread.isAlive()) {

                    }
                }
                LogWriter.log("Generation finished\r\n");
            }
        } else {
            LogWriter.log("Generation started in single thread.");
            Generator gen = new Generator();
            if (list == null) {
                gen.createSamples(null, 0,  amount, contrast, disabledControls, noise, isSorted, -1);
            } else {
                list.forEach((control) -> gen.createSamples(control, 0, amount, contrast, disabledControls, noise, isSorted, -1));
            }
            LogWriter.log("Generation finished\r\n");
        }
    }

    public static synchronized void setProgress(int amount){
        progress += amount;
    }

    public int getProgress(){
        return progress;
    }
}
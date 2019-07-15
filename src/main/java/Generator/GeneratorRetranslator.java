import java.util.ArrayList;

public class GeneratorRetranslator {

    public void startGenerator(ArrayList<ControlTypes> list, int amount, boolean contrast,  boolean disabledControls, boolean noise, boolean isSorted) {
        GeneratorThread[] Threads = new GeneratorThread[ConstCollection.THREAD_COUNT];
        if (list == null) {
            int part = (int) amount / ConstCollection.THREAD_COUNT;
            int extra = 0;
            int additionalAmount = amount - part * ConstCollection.THREAD_COUNT;
            for (int i = 0; i < ConstCollection.THREAD_COUNT; i++) {
                if (i == ConstCollection.THREAD_COUNT - 1) {
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
        }
    }
}
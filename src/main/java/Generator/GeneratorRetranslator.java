package Generator;

import Model.ControlTypes;

import java.util.ArrayList;

public class GeneratorRetranslator {

    public void startGenerator(ArrayList<ControlTypes> list, int amount, boolean contrast, boolean disabledControls, boolean noise, boolean isSorted) {
        Generator gen = new Generator();
        if (list == null) {
            gen.createSamples(null, amount, contrast, disabledControls, noise,isSorted);
        } else {
            list.forEach((control) -> gen.createSamples(control, amount, contrast, disabledControls, noise, isSorted));
        }
    }
}

package Model;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains tests for ControlTypes enum
 * @author Satyukov Egor
 * @since 1.0
 */
public class ControlTypesTest {
    /**
     * Check getObject() for every type of control and for both states of method argument 'isThreaded'
     * on not null return
     */
    @Test
    void getObjectNotNullReturnTest() {
        ControlTypes control = ControlTypes.CHECKBOX;
        assertNotNull(control.getObject(false));
        assertNotNull(control.getObject(true));
        control = ControlTypes.TEXTFIELD;
        assertNotNull(control.getObject(false));
        assertNotNull(control.getObject(true));
        control = ControlTypes.RADIOBUTTON;
        assertNotNull(control.getObject(false));
        assertNotNull(control.getObject(true));
        control = ControlTypes.SPINNER;
        assertNotNull(control.getObject(false));
        assertNotNull(control.getObject(true));
        control = ControlTypes.SLIDER;
        assertNotNull(control.getObject(false));
        assertNotNull(control.getObject(true));
        control = ControlTypes.BUTTON;
        assertNotNull(control.getObject(false));
        assertNotNull(control.getObject(true));
    }

    /**
     * Check getObject() for CheckBox and for both states of method argument 'isThreaded' on correct text filling
     * of control
     */
    @Test
    void getObjectCheckBoxWithSetValuesReturnTest() {
        ControlTypes control = ControlTypes.CHECKBOX;
        assertNotEquals("", ((JCheckBox) control.getObject(false)).getText());
        assertNotEquals("", ((JCheckBox) control.getObject(true)).getText());
    }

    /**
     * Check getObject() for TextField and for both states of method argument 'isThreaded' on correct text filling
     * of control
     */
    @Test
    void getObjectTextFieldWithSetValuesReturnTest() {
        ControlTypes control = ControlTypes.TEXTFIELD;
        assertNotEquals("", ((JTextField) control.getObject(false)).getText());
        assertNotEquals("", ((JTextField) control.getObject(true)).getText());
    }

    /**
     * Check getObject() for RadioButton and for both states of method argument 'isThreaded' on correct text filling
     * of control
     */
    @Test
    void getObjectRadioButtonWithSetValuesReturnTest() {
        ControlTypes control = ControlTypes.RADIOBUTTON;
        assertNotEquals("", ((JRadioButton) control.getObject(false)).getText());
        assertNotEquals("", ((JRadioButton) control.getObject(true)).getText());
    }

    /**
     * Check getObject() for Slider and for both states of method argument 'isThreaded' on correct setting
     * the orientation and size of control
     */
    @Test
    void getObjectSliderWithSetValuesReturnTest() {
        ControlTypes control = ControlTypes.SLIDER;
        assertEquals(JSlider.HORIZONTAL, ((JSlider) control.getObject(false)).getOrientation());
        assertNotEquals(new Dimension(0,0), control.getObject(false).getSize());
        assertEquals(JSlider.HORIZONTAL, ((JSlider) control.getObject(true)).getOrientation());
        assertNotEquals(new Dimension(0,0), control.getObject(true).getSize());
    }

    /**
     * Check getObject() for Button and for both states of method argument 'isThreaded' on correct text filling
     * of control
     */
    @Test
    void getObjectButtonWithSetValuesReturnTest() {
        ControlTypes control = ControlTypes.BUTTON;
        assertNotEquals("", ((JButton) control.getObject(false)).getText());
        assertNotEquals("", ((JButton) control.getObject(true)).getText());
    }

    /**
     * Check getRandomObject() for every type of control on not null return
     */
    @Test
    void getRandomObjectNotNullReturnTest() {
        ControlTypes control = ControlTypes.CHECKBOX;
        assertNotNull(control.getRandomObject());
        control = ControlTypes.TEXTFIELD;
        assertNotNull(control.getRandomObject());
        control = ControlTypes.RADIOBUTTON;
        assertNotNull(control.getRandomObject());
        control = ControlTypes.SPINNER;
        assertNotNull(control.getRandomObject());
        control = ControlTypes.SLIDER;
        assertNotNull(control.getRandomObject());
        control = ControlTypes.BUTTON;
        assertNotNull(control.getRandomObject());
    }

    /**
     * Check getRandomObject() for containing of method result in ControlTypes enum
     */
    @Test
    void getRandomObjectControlFromEnumReturnTest() {
        ControlTypes control = ControlTypes.BUTTON;
        ArrayList list = new ArrayList<>(Arrays.asList(ControlTypes.values()));
        assertTrue(list.contains(control.getRandomObject()));
    }

    /**
     * Check getFolderName() for every type of control on correct return
     */
    @Test
    void getFolderNameReturnTest() {
        ControlTypes control = ControlTypes.CHECKBOX;
        assertEquals("checkbox", control.getFolderName());
        control = ControlTypes.TEXTFIELD;
        assertEquals("textfield", control.getFolderName());
        control = ControlTypes.RADIOBUTTON;
        assertEquals("radiobutton", control.getFolderName());
        control = ControlTypes.SPINNER;
        assertEquals("spinner", control.getFolderName());
        control = ControlTypes.SLIDER;
        assertEquals("slider", control.getFolderName());
        control = ControlTypes.BUTTON;
        assertEquals("button", control.getFolderName());
    }
}

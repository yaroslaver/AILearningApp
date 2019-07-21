package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 * This class controls actions, which can happen in the manual window.
 * @author Yaroslav Erokhin
 * @author Revtova Natalya
 * @author Timoshenskiy Georgy
 * @since 1.1
 */
public class ControllerManual {

    @FXML
    private Button backButton;

    /**
     * Method is called, when manual window is open.
     */
    @FXML
    void initialize() {
        closeManual();
    }

    /**
     * Method attaches EventHandler to the backButton.
     * EventHandler closes window.
     */
    private void closeManual(){
        backButton.setOnAction(event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        });
    }
}

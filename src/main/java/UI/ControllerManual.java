package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerManual {

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        closeManual();
    }

    private void closeManual(){
        backButton.setOnAction(event -> {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        });
    }
}



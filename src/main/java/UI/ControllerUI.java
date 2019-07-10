package UI;

import Model.ControlTypes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class ControllerUI {

  @FXML
  private TextField quantityField;

  @FXML
  private Button generateButton;

  @FXML
  private Button helpButton;

  @FXML
  private ProgressBar progressBar;

  @FXML
  private CheckBox hasCheckBox;

  @FXML
  private CheckBox hasSpinner;

  @FXML
  private CheckBox hasRadioButton;

  @FXML
  private CheckBox hasButton;

  @FXML
  private CheckBox hasTextField;

  @FXML
  private CheckBox hasSlider;

  @FXML
  private CheckBox isDisabled;

  @FXML
  private CheckBox hasHighContrast;

  @FXML
  private FlowPane previewField = new FlowPane();


  @FXML
  private void initialize() {
    setSettingsToPreviewField();
    setGenerateButtonAction();
    showManual();
    setControlsActions();
  }

  @FXML
  private void showManual() {
    helpButton.setOnAction(event -> {
      try {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("manual.fxml")));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(((Node)event.getSource()).getScene().getWindow());
        stage.show();
      }
      catch (NullPointerException ex) {
        System.err.println("File .fxml was not found");
      }
      catch (IOException ex) {
        System.err.println(ex.getMessage());
      }
    });
  }

  private void setSettingsToPreviewField() {
    previewField.setHgap(5);
    previewField.setVgap(5);
    previewField.setOrientation(Orientation.VERTICAL);
    previewField.setAlignment(Pos.CENTER);
    previewField.setColumnHalignment(HPos.LEFT);
  }

  private void setControlsActions() {
    hasTextField.setOnAction(event -> {
      if (hasTextField.isSelected()) {
        previewField.getChildren().add(new TextField("TextField"));
      }
      else {
        previewField.getChildren().removeIf(n -> (n instanceof TextField));
      }
    });

    hasCheckBox.setOnAction(event -> {
      if (hasCheckBox.isSelected()) {
        previewField.getChildren().add(new CheckBox("CheckBox"));
      }
      else {
        previewField.getChildren().removeIf(n -> (n instanceof CheckBox));
      }
    });

    hasRadioButton.setOnAction(event -> {
      if (hasRadioButton.isSelected()) {
        previewField.getChildren().add(new RadioButton("RadioButton"));
      }
      else {
        previewField.getChildren().removeIf(n -> (n instanceof RadioButton));
      }
    });

    hasSlider.setOnAction(event -> {
      if (hasSlider.isSelected()) {
        previewField.getChildren().add(new Slider());
      }
      else {
        previewField.getChildren().removeIf(n -> (n instanceof Slider));
      }
    });

    hasButton.setOnAction(event -> {
      if (hasButton.isSelected()) {
        previewField.getChildren().add(new Button("Button"));
      }
      else {
        previewField.getChildren().removeIf(n -> (n instanceof Button));
      }
    });

    hasSpinner.setOnAction(event -> {
      if (hasSpinner.isSelected()) {
        previewField.getChildren().add(new Spinner<>());
      }
      else {
        previewField.getChildren().removeIf(n -> (n instanceof Spinner));
      }
    });
  }

  private void setGenerateButtonAction() {
    generateButton.setOnAction(event -> {
      quantityField.setText(quantityField.getText().trim());

      int inputQuantity = 0;

      try {
        inputQuantity = Integer.valueOf(quantityField.getText());
      }
      catch (NumberFormatException ex) {
        quantityField.setText("");
        // quantity of controls must be positive
        return;
      }
      if (inputQuantity < 1) {
        quantityField.setText("");
        // please, enter quantity of controls
        return;
      }

      ArrayList<ControlTypes> controlsList = new ArrayList<>(6);
      for (Node control: previewField.getChildren()) {
        if (control instanceof TextField) {
          controlsList.add(ControlTypes.TEXTFIELD);
        } else {
          if (control instanceof CheckBox) {
            controlsList.add(ControlTypes.CHECKBOX);
          } else {
            if (control instanceof RadioButton) {
              controlsList.add(ControlTypes.RADIOBUTTON);
            } else {
              if (control instanceof Slider) {
                controlsList.add(ControlTypes.SLIDER);
              } else {
                if (control instanceof  Button) {
                  controlsList.add(ControlTypes.BUTTON);
                } else {
                  if (control instanceof Spinner) {
                    controlsList.add(ControlTypes.SPINNER);
                  }
                }
              }
            }
          }
        }
      }
      if (controlsList.size() == 0) {
        // please, choose any controls
        return;
      }
    });
  }

}

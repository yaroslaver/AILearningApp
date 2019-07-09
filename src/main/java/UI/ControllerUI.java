package UI;

import Model.ConstCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerUI {

  private Region prevControl = null;

  @FXML
  private TextField quantityField;

  @FXML
  private Button generateButton;
  @FXML
  private Button helpButton;

  @FXML
  private AnchorPane previewField = new AnchorPane();


  @FXML
  private void initialize() {
//    formatOfImages.setItems(formatOfImagesList);
//    kindOfImages.setItems(kindOfImagesList);
//    setGenerateButtonAction();
//    setKindOfImagesAction();
      showManual();
  }
  @FXML
  private void showManual() {
    helpButton.setOnAction(event -> {
      quantityField.setText("Кнопка нажата");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("manual.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(helpButton.getScene().getWindow());
        stage.setScene(new Scene(root));
        stage.showAndWait();
//
//      try {
//          Stage stage = new Stage();
//          Parent root = FXMLLoader.load(getClass().getResource("manual.fxml"));
//          stage.setResizable(false);
//          stage.setScene(new Scene(root));
//          stage.initModality(Modality.WINDOW_MODAL);
////          stage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
//          stage.show();
//      }
//      catch (IOException e) {
//          e.printStackTrace();
//      }
    });


  }

  /**
   * Method gives correct parameter to method, which saves images
   */
//  private void setFormatOfImagesAction() {
//    formatOfImages.setOnAction(event -> {
//      switch (formatOfImages.getValue()) {
//        case "JPEG":
//          break;
//        case "PNG":
//          break;
//        case "GIF":
//          break;
//        case "BMP":
//          break;
//        case "WBMP":
//          break;
//      }
//    });
//  }

//  private void setKindOfImagesAction() {
//    kindOfImages.setOnAction(event -> {
//      switch (kindOfImages.getValue()) {
//        case "CheckBox":
//          deletePrevControl();
//          CheckBox checkBox = new CheckBox();
//          setOffsetsAndSizes(checkBox, ConstCollection.WIDTH_OF_CHECKBOX, ConstCollection.HEIGHT_OF_CHECKBOX);
//          prevControl = checkBox;
//          break;
//        case "TextField":
//          deletePrevControl();
//          Region textField = new TextField();
//          setOffsetsAndSizes(textField, ConstCollection.WIDTH_OF_TEXTFIELD, ConstCollection.HEIGHT_OF_TEXTFIELD);
//          prevControl = textField;
//          break;
//        case "Button":
//          deletePrevControl();
//          Region button = new Button();
//          setOffsetsAndSizes(button, ConstCollection.WIDTH_OF_BUTTON, ConstCollection.HEIGHT_OF_BUTTON);
//          prevControl = button;
//          break;
//        case "RadioButton":
//          deletePrevControl();
//          Region radioButton = new RadioButton();
//          setOffsetsAndSizes(radioButton, ConstCollection.WIDTH_OF_RADIOBUTTON, ConstCollection.HEIGHT_OF_RADIOBUTTON);
//          prevControl = radioButton;
//          break;
//        case "Slider":
//          deletePrevControl();
//          Region slider = new Slider();
//          setOffsetsAndSizes(slider, ConstCollection.WIDTH_OF_SLIDER, ConstCollection.HEIGHT_OF_SLIDER);
//          prevControl = slider;
//          break;
//        case "Spinner":
//          deletePrevControl();
//          Region spinner = new Spinner<>();
//          setOffsetsAndSizes(spinner, ConstCollection.WIDTH_OF_SPINNER, ConstCollection.HEIGHT_OF_SPINNER);
//          prevControl = spinner;
//          break;
//        case "Random":
//          deletePrevControl();
//          prevControl = null;
//          break;
//      }
//    });
//  }

  private void setOffsetsAndSizes(Region control, double prefWidth, double prefHeight) {
    AnchorPane.setLeftAnchor(control, ConstCollection.OFFSET_X_FOR_PREVIEW);
    AnchorPane.setTopAnchor(control, ConstCollection.OFFSET_Y_FOR_PREVIEW);
    control.setPrefSize(prefWidth, prefHeight);
    previewField.getChildren().add(control);
  }

  private void deletePrevControl() {
    if (prevControl != null) {
      previewField.getChildren().remove(prevControl);
    }
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
        return;
      }
      if (inputQuantity < 1) {
        quantityField.setText("");
        return;
      }
    });
  }



}

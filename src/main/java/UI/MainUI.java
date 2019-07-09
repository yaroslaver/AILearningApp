package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Objects;

public class MainUI extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main-ui.fxml")));
    stage.setTitle("AILearningApp");


    stage.setScene(new Scene(root));
    stage.setResizable(false);
    stage.setMaximized(false);
    stage.show();

  }


  public static void main(String[] args) {
    launch(args);
  }
}

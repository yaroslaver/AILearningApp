package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainUI extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main-ui.fxml"));
    stage.setTitle("AILearningApp");
    stage.setScene(new Scene(root));
    stage.setResizable(false);
    stage.setMaximized(true);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}

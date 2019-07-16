package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class MainUI extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    try {
      Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("main-ui.fxml")));
      stage.setTitle("AILearningApp");
      stage.setScene(new Scene(root));
      stage.setResizable(false);
      stage.setMaximized(false);
      stage.getIcons().add(new Image("images/icon.png"));
      stage.show();
    }
    catch (NullPointerException ex) {
      System.err.println("File .fxml was not found");
      System.exit(1);
    }
    catch (IOException ex) {
      System.err.println(ex.getMessage());
      System.exit(-1);
    }

  }

  public static void main(String[] args) {
    launch(args);
  }
}

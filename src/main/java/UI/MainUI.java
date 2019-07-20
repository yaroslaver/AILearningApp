package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

/**
 * This class launches application.
 * @author Yaroslav Erokhin
 * @author Revtova Natalya
 * @author Timoshevsky Georgy
 * @since 1.0
 */
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
    if (args.length == 0) {
      launch(args);
    } else {
      CommandLineParser parser = new CommandLineParser();
      try {
        parser.parse(args);
      }
      catch (NumberFormatException ex) {
        System.err.println("Incorrect type of value");
        System.out.println("Type 'help' as argument for help");
        System.exit(1);
      }
      catch (IllegalArgumentException ex) {
        System.err.println(ex.getMessage());
        System.out.println("Type 'help' as argument for help");
        System.exit(1);
      }
      System.exit(0);
    }
  }
}

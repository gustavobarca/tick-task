package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public Scene Scene(Parent root) {
        return new Scene(root, 400, 400, Color.WHITE);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/sample.fxml"));

        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

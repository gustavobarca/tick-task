package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ui/resources/home.fxml"));
        primaryStage.initStyle(StageStyle.UNIFIED);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root, 400, 400, Color.WHITE));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

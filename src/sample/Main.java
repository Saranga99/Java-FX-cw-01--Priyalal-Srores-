package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage PrimaryStage = new Stage();
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrimaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        PrimaryStage.setTitle("Login");
        PrimaryStage.setScene(new Scene(root, 550, 400));
        PrimaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package org.example.firstsemfptolayerd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("org/example/firstsemfptolayerd/assests/DashBoard.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Aquarium Management Project");
        stage.setScene(scene);
        stage.show();
    }
}
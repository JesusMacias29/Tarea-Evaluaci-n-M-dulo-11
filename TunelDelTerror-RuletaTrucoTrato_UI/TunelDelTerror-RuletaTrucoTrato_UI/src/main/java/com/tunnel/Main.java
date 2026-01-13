package com.tunnel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("entrada.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(getClass().getResource("halloween.css").toExternalForm());
        stage.setTitle("Túnel del Terror 🎃");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("pumpkin.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

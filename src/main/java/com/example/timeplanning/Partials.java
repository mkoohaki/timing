package com.example.timeplanning;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Partials {

    public static void windowOpen(String fileName, String title, int width, int height) throws Exception {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Partials.class.getResource(fileName + ".fxml")));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, height, width));
        primaryStage.setResizable(false);
//        root.getStylesheets().add("/CSS/stylesheet.css");
        primaryStage.show();
    }

    public static void windowClose(ActionEvent event) {

        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}

package com.example.timeplanning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Partials.windowOpen("enter", "Timing Plan", 320, 320);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
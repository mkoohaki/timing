package com.example.timeplanning;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.scene.control.TextField;

import java.util.Objects;

public class ScheduleController {

    @FXML
    TextField status1, start1, end1;

    @FXML
    AnchorPane scene;

    @FXML
    protected void add(ActionEvent event) throws Exception {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    TableView table;



}

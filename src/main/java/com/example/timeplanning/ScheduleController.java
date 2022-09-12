package com.example.timeplanning;

import com.example.timeplanning.tableView.Activity;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleController extends Application {

    @FXML
    TextField status1, start1, end1;

    @FXML
    AnchorPane scene;

    @FXML
    TableView table;

    @FXML
    protected void add(ActionEvent event) throws Exception {
        try {

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        TableColumn<Activity, String> activityColumn = new TableColumn<>("activity");
        activityColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));

        TableColumn<Activity, String> startColumn = new TableColumn<>("start");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<Activity, String> endColumn = new TableColumn<>("end");
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        table.getColumns().add(activityColumn);
        table.getColumns().add(startColumn);
        table.getColumns().add(endColumn);

        root.setCenter(table);
    }
}

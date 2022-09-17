package com.example.timeplanning;

import com.example.timeplanning.database.AccountDatabase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {

    @FXML
    TextField activityText, startText, endText;

    @FXML
    AnchorPane scene;

    @FXML
    Button add;

    TableView<Activity> table;
    final ObservableList<Activity> data = FXCollections.observableArrayList();

    @FXML
    protected void add(ActionEvent event) throws Exception {
        try {
            saveActivity(activityText.getText(), startText.getText(), endText.getText());
            activityText.setText("");
            startText.setText("");
            endText.setText("");
            refreshTable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        creatingTable();
    }

    public void creatingTable() {
        TableColumn<Activity, String> activityColumn = new TableColumn<>("Activity");
        activityColumn.setMinWidth(150);
        activityColumn.setCellValueFactory(new PropertyValueFactory<>("activity"));

        TableColumn<Activity, String> startColumn = new TableColumn<>("Start");
        activityColumn.setMinWidth(95);
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));

        TableColumn<Activity, String> endColumn = new TableColumn<>("End");
        activityColumn.setMinWidth(95);
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));

        TableColumn<Activity, Button> actionColumn = new TableColumn<>("Action");
        actionColumn.setMinWidth(80);

        table = new TableView<>();
        try {
            table.setItems(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.getColumns().addAll(activityColumn, startColumn, endColumn, actionColumn);
        scene.getChildren().addAll(table);
    }

    public ObservableList<Activity> getActivity() throws SQLException {

        ObservableList<Activity> activities = FXCollections.observableArrayList();

        AccountDatabase db = new AccountDatabase();
        String[][] rows = db.getAllRows();
        Arrays.sort(rows, new Comparator<>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[1];
                final String time2 = entry2[1];
                return time1.compareTo(time2);
            }
        });
        for(int i=0; i<rows.length; i++) {
            activities.add(new Activity(rows[i][0], rows[i][1], rows[i][2]));
        }
        return activities;
    }

    public void saveActivity(String activity, String start, String end) throws SQLException {
        AccountDatabase db = new AccountDatabase();
        db.insertRow(activity, start, end);
    }

    public void refreshTable() {
        data.clear();
        creatingTable();
    }
}

package com.example.timeplanning;

import com.example.timeplanning.database.AccountDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;

public class ScheduleController implements Initializable {

    @FXML
    TextField activityText, startText, endText;

    @FXML
    AnchorPane scene;

    TableView<Activity> table;
    final ObservableList<Activity> data = FXCollections.observableArrayList();
    Alert alart = new Alert(Alert.AlertType.NONE);
    private Button button;

    @FXML
    protected void add(ActionEvent event) throws Exception {
        try {

            if(checkActivity(activityText.getText(), startText.getText(), endText.getText())) {
                saveActivity(activityText.getText(), startText.getText(), endText.getText());
            }
            activityText.setText("");
            startText.setText("");
            endText.setText("");
            refreshTable();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    protected void back(ActionEvent event) throws Exception {
        try {
            Partials.windowOpen("enter", "Timing Plan", 320, 320);
            Partials.windowClose(event);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private boolean checkActivity(String activity, String start, String end) throws SQLException {

        AccountDatabase db = new AccountDatabase();
        String[][] rows = db.getAllRows();

        if(activity.equals("") || start.equals("") || end.equals("")) {
            alart.setAlertType(Alert.AlertType.ERROR);
            alart.setContentText("Please fill out the form");
            alart.show();
            return false;
        }
        for (String[] row : rows) {
            if (Objects.equals(row[0], activity)) {
                alart.setAlertType(Alert.AlertType.ERROR);
                alart.setContentText("Activity already exists");
                alart.show();
                return false;
            } else if (Objects.equals(row[1], start)) {
                alart.setAlertType(Alert.AlertType.ERROR);
                alart.setContentText("Time has interruption");
                alart.show();
                return false;
            } else if (Objects.equals(row[2], end)) {
                alart.setAlertType(Alert.AlertType.ERROR);
                alart.setContentText("Time has interruption");
                alart.show();
                return false;
            }
        }
        if (start.equals(end)) {
                alart.setAlertType(Alert.AlertType.ERROR);
                alart.setContentText("Time has interruption");
                alart.show();
                return false;
        }

        String[] arrOfStart = start.split(":");
        LocalTime timeStart = LocalTime.of(Integer.parseInt(arrOfStart[0]), Integer.parseInt(arrOfStart[1]));
        String[] arrOfSEnd = end.split(":");
        LocalTime timeEnd = LocalTime.of(Integer.parseInt(arrOfSEnd[0]), Integer.parseInt(arrOfSEnd[1]));
        String[] startRow, endRow;
        LocalTime sTime, eTime;

        for (String[] row : rows) {
            if (row[1] != null) {
                startRow = row[1].split(":");
                sTime = LocalTime.of(Integer.parseInt(startRow[0]), Integer.parseInt(startRow[1]));

                endRow = row[2].split(":");
                eTime = LocalTime.of(Integer.parseInt(endRow[0]), Integer.parseInt(endRow[1]));

                if (sTime.isBefore(timeStart) && eTime.isAfter(timeStart)) {
                    alart.setAlertType(Alert.AlertType.ERROR);
                    alart.setContentText("Time has interruption");
                    alart.show();
                    return false;
                }
                if (sTime.isBefore(timeEnd) && eTime.isAfter(timeEnd)) {
                    alart.setAlertType(Alert.AlertType.ERROR);
                    alart.setContentText("Time has interruption");
                    alart.show();
                    return false;
                }
                if (sTime.isAfter(timeStart) && eTime.isBefore(timeEnd)) {
                    alart.setAlertType(Alert.AlertType.ERROR);
                    alart.setContentText("Time has interruption");
                    alart.show();
                    return false;
                }
            }
        }

        return true;
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

        TableColumn<Activity, String> editColumn = new TableColumn<>("Edit");
        editColumn.setMinWidth(40);
        editColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));

        TableColumn<Activity, String> deleteColumn = new TableColumn<>("Delete");
        deleteColumn.setMinWidth(40);
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        table = new TableView<>();
        try {
            table.setItems(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.getColumns().addAll(activityColumn, startColumn, endColumn, editColumn, deleteColumn);
        scene.getChildren().addAll(table);
    }

    public ObservableList<Activity> getActivity() throws SQLException {

        try {
            ObservableList<Activity> activities = FXCollections.observableArrayList();
            AccountDatabase db = new AccountDatabase();
            String[][] rows = db.getAllRows();

            for(int i=0; i<rows.length; i++) {
                activities.add(new Activity(rows[i][0], rows[i][1], rows[i][2]));
            }
            return activities;
        } catch(Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    public void saveActivity(String activity, String start, String end) throws SQLException {
        AccountDatabase db = new AccountDatabase();
        db.insertRow(activity, start, end);
    }

    public void refreshTable() {
        data.clear();
        creatingTable();
    }

    @FXML
    protected void edit(ActionEvent event) throws Exception {
        try {
            Partials.windowOpen("enter", "Timing Plan", 320, 320);
            Partials.windowClose(event);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

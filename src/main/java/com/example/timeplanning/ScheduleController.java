package com.example.timeplanning;

import com.example.timeplanning.database.AccountDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.*;

public class ScheduleController implements Initializable {

    Alert alert = new Alert(Alert.AlertType.NONE);
    public static TableView<Activity> table_info_2;

    @FXML
    TextField activityText, startText, endText;

    @FXML
    private TableView<Activity> table_info;

    @FXML
    private TableColumn<Activity, String> col_activity;

    @FXML
    private TableColumn<Activity, String> col_start;

    @FXML
    private TableColumn<Activity, String> col_end;

    @FXML
    private TableColumn<Activity, Button> col_update;

    @FXML
    private TableColumn<Activity, Button> col_delete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        loadData();
    }

    private void initTable() {
        table_info_2 = table_info;
        initCols();
    }

    private void initCols() {
        col_activity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        col_start.setCellValueFactory(new PropertyValueFactory<>("start"));
        col_end.setCellValueFactory(new PropertyValueFactory<>("end"));
        col_update.setCellValueFactory(new PropertyValueFactory<>("update"));
        col_delete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        editTableCols();
    }

    private void editTableCols() {
        col_activity.setCellFactory(TextFieldTableCell.forTableColumn());
        col_activity.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setActivity(e.getNewValue());
        });

        col_start.setCellFactory(TextFieldTableCell.forTableColumn());
        col_start.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setStart(e.getNewValue());
        });

        col_end.setCellFactory(TextFieldTableCell.forTableColumn());
        col_end.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEnd(e.getNewValue());
        });

        table_info.setEditable(true);
    }

    private void loadData() {

        try {
            table_info.setItems(getActivity());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Activity> getActivity() throws SQLException {

        try {
            ObservableList<Activity> activities = FXCollections.observableArrayList();
            AccountDatabase db = new AccountDatabase();
            ArrayList<String[]> rows = db.getAllRows();

            for(int i=0; i<rows.size(); i++) {
                activities.add(new Activity(rows.get(i)[0], rows.get(i)[1], rows.get(i)[2], new Button("U"), new Button("D")));
            }
            return activities;
        } catch(Exception e) {
            System.out.println("Error: " + e);
            return null;
        }
    }

    @FXML
    protected void add(ActionEvent event) throws Exception {
        try {

            if(checkActivity(activityText.getText(), startText.getText(), endText.getText())) {
                saveActivity(activityText.getText(), startText.getText(), endText.getText());
            }
            activityText.setText("");
            startText.setText("");
            endText.setText("");
            loadData();
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
        ArrayList<String[]> rows = db.getAllRows();

        if(activity.equals("") || start.equals("") || end.equals("")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("Please fill out the form");
            alert.show();
            return false;
        }
        for (String[] row : rows) {
            if (Objects.equals(row[0], activity)) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Activity already exists");
                alert.show();
                return false;
            } else if (Objects.equals(row[1], start)) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Time has interruption");
                alert.show();
                return false;
            } else if (Objects.equals(row[2], end)) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Time has interruption");
                alert.show();
                return false;
            }
        }
        if (start.equals(end)) {
                alert.setAlertType(Alert.AlertType.ERROR);
                alert.setContentText("Time has interruption");
                alert.show();
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
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Time has interruption");
                    alert.show();
                    return false;
                }
                if (sTime.isBefore(timeEnd) && eTime.isAfter(timeEnd)) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Time has interruption");
                    alert.show();
                    return false;
                }
                if (sTime.isAfter(timeStart) && eTime.isBefore(timeEnd)) {
                    alert.setAlertType(Alert.AlertType.ERROR);
                    alert.setContentText("Time has interruption");
                    alert.show();
                    return false;
                }
            }
        }

        return true;
    }

    public void saveActivity(String activity, String start, String end) throws SQLException {
        AccountDatabase db = new AccountDatabase();
        db.insertRow(activity, start, end);
    }
}

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
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

public class ScheduleController implements Initializable {

    @FXML
    TextField activityText, startText, endText;

    @FXML
    AnchorPane scene;

    @FXML
    Button add;

    TableView<Activity> table;
    final ObservableList<Activity> data = FXCollections.observableArrayList();
    Alert a = new Alert(Alert.AlertType.NONE);

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

    private boolean checkActivity(String activity, String start, String end) throws SQLException {

        AccountDatabase db = new AccountDatabase();
        String[][] rows = db.getAllRows();

        if(activity.equals("") || start.equals("") || end.equals("")) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Please fill out the form");
            a.show();
            return false;
        }
        for (String[] row : rows) {
            if (Objects.equals(row[0], activity)) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Activity already exists");
                a.show();
                return false;
            } else if (Objects.equals(row[1], start)) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Time has interruption");
                a.show();
                return false;
            } else if (Objects.equals(row[2], end)) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Time has interruption");
                a.show();
                return false;
            }
        }
        if (start.equals(end)) {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Time has interruption");
                a.show();
                return false;
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

        try {
            ObservableList<Activity> activities = FXCollections.observableArrayList();

            AccountDatabase db = new AccountDatabase();
            String[][] rows = db.getAllRows();
            String[][] newRows = rows;

            System.out.println("1");
            TreeMap<LocalTime, Integer> map = new TreeMap<>();
            System.out.println("2");

            LocalTime time;
            System.out.println("3");

            for (int i=0; i<rows.length; i++) {

                time = LocalTime.of(Integer.parseInt(rows[i][1].substring(0,2)), Integer.parseInt(rows[i][1].substring(3)));

                map.put(time, i);
            }

//            String[][] rows1 = new String[rows.length][3];
//
//            for(int i = 0; i<rows.length; i++) {
//                for (int j = 0; j < rows.length; j++) {
//                    if(Objects.equals(list.get(i).toString(), rows[j][1])){
//                        rows1[i] = rows[j];
//                    }
//                }
//            }
//
//


//
//            final String[][] data = null;
//            for(int i =0; i<rows[i].length; i++){
//                data[i] = rows[i];
//            }
//            Arrays.sort(data, new Comparator<>() {
//
//                @Override
//                public int compare(String[] entry1, String[] entry2) {
//                    String time1 = entry1[0];
//                    String time2 = entry2[0];
//                    return time1.compareTo(time2);
//                }
//            });
            for (Map.Entry<LocalTime,Integer> entry : map.entrySet()) {
                System.out.println(entry);
                Integer value = entry.getValue();
                activities.add(new Activity(rows[value][0], rows[value][1], rows[value][2]));
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
}

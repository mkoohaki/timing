package com.example.timeplanning;

import com.example.timeplanning.database.AccountDatabase;
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

        try {
            ObservableList<Activity> activities = FXCollections.observableArrayList();

            AccountDatabase db = new AccountDatabase();
            String[][] rows = db.getAllRows();

            final String[][] data = null;
            for(int i =0; i<rows[i].length; i++){
                data[i] = rows[i];
            }
            Arrays.sort(data, new Comparator<>() {

                @Override
                public int compare(String[] entry1, String[] entry2) {
                    String time1 = entry1[0];
                    String time2 = entry2[0];
                    return time1.compareTo(time2);
                }
            });
            for (int i = 0; i < rows.length; i++) {
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
}

package com.example.timeplanning;

import com.example.timeplanning.database.AccountDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EnterController implements Initializable {
    @FXML
    private Label time, date, day, status;

    @FXML
    protected void setPlan(ActionEvent event) throws Exception {
        Partials.windowOpen("schedule", "Timing Plan", 620, 320);
        Partials.windowClose(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DateTimeFormatter timing = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter weekDay = DateTimeFormatter.ofPattern("EEEE");
        DateTimeFormatter dating = DateTimeFormatter.ofPattern("MMM.dd, yyyy");
        LocalDateTime now = LocalDateTime.now();

        day.setText(weekDay.format(now));
        date.setText(dating.format(now));
        time.setText(timing.format(now).substring(0, 5));

        try {
            AccountDatabase db = new AccountDatabase();
            String[][] rows = db.getAllRows();

            for(String[] row : rows) {
                if (row[1] != null) {
                    String[] startRow = row[1].split(":");
                    LocalTime sTime = LocalTime.of(Integer.parseInt(startRow[0]), Integer.parseInt(startRow[1]));
                    String[] endRow = row[2].split(":");
                    LocalTime eTime = LocalTime.of(Integer.parseInt(endRow[0]), Integer.parseInt(endRow[1]));

                    if (LocalTime.now().isAfter(sTime) && LocalTime.now().isBefore(eTime)) {
                        status.setText(row[0]);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

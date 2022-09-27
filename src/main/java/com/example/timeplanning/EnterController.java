package com.example.timeplanning;

import com.example.timeplanning.database.AccountDatabase;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EnterController implements Initializable {
    @FXML
    private Label time, date, day, status;

    private volatile boolean stop;

    @FXML
    protected void setPlan(ActionEvent event) throws Exception {
        Partials.windowOpen("schedule", "Timing Plan", 620, 320);
        stop = true;
        Partials.windowClose(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DateTimeFormatter weekDay = DateTimeFormatter.ofPattern("EEEE");
        DateTimeFormatter dating = DateTimeFormatter.ofPattern("MMM.dd, yyyy");
        LocalDateTime now = LocalDateTime.now();

        day.setText(weekDay.format(now));
        date.setText(dating.format(now));

        Timenow();
        try {
            AccountDatabase db = new AccountDatabase();
            ArrayList<String[]> rows = db.getAllRows();

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

    //Updating time
    private void Timenow() {
        Thread clock = new Thread( () -> {
            DateTimeFormatter timing = DateTimeFormatter.ofPattern("HH:mm:ss");
            while(!stop) {
                try{
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                LocalDateTime now = LocalDateTime.now();
                final String timenow = timing.format(now).substring(0, 8);
                Platform.runLater( () -> {
                    time.setText(timenow);
                });
            }
        });
        clock.start();
    }

    @FXML
    public void exitApplication(ActionEvent event) {
        stop = true;
        Platform.exit();
    }
}

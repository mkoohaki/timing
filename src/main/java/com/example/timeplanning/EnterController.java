package com.example.timeplanning;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
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

        String t1 = "07:00:00", t2 = "07:15:00", t3 = "08:15:00", t4 = "08:30:00", t5 = "09:30:00", t6 = "12:30:00",
                t7 = "12:45:00",t8 = "13:45:00", t9 = "15:00:00", t10= "16:30:00", t11 = "18:30:00", t12 = "18:45:00",
                t13 = "19:45:00", t14 = "21:00:00";
        if(LocalTime.now().isAfter(LocalTime.parse(t1)) && LocalTime.now().isBefore(LocalTime.parse(t2))) {
            status.setText("Wake up");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t2)) && LocalTime.now().isBefore(LocalTime.parse(t3))) {
            status.setText("Walking");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t3)) && LocalTime.now().isBefore(LocalTime.parse(t4))) {
            status.setText("Shower");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t4)) && LocalTime.now().isBefore(LocalTime.parse(t5))) {
            status.setText("Breakfast");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t5)) && LocalTime.now().isBefore(LocalTime.parse(t6))) {
            status.setText("Learning");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t6)) && LocalTime.now().isBefore(LocalTime.parse(t7))) {
            status.setText("Break");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t7)) && LocalTime.now().isBefore(LocalTime.parse(t8))) {
            status.setText("Coding");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t8)) && LocalTime.now().isBefore(LocalTime.parse(t9))) {
            status.setText("Job Search");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t9)) && LocalTime.now().isBefore(LocalTime.parse(t10))) {
            status.setText("Lunch");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t10)) && LocalTime.now().isBefore(LocalTime.parse(t11))) {
            status.setText("Learning");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t11)) && LocalTime.now().isBefore(LocalTime.parse(t12))) {
            status.setText("Break");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t12)) && LocalTime.now().isBefore(LocalTime.parse(t13))) {
            status.setText("Coding");
        } else if (LocalTime.now().isAfter(LocalTime.parse(t13)) && LocalTime.now().isBefore(LocalTime.parse(t14))) {
            status.setText("Job Search");
        }
    }
}

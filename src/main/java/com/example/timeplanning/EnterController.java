package com.example.timeplanning;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class EnterController implements Initializable {
    @FXML
    private Label time, date, day;

    @FXML
    protected void setPlan() {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));

        DateTimeFormatter timing = DateTimeFormatter.ofPattern("HH:mm:ss");
        DateTimeFormatter weekDay = DateTimeFormatter.ofPattern("EEEE");
        DateTimeFormatter dating = DateTimeFormatter.ofPattern("MMM.dd, yyyy");
        LocalDateTime now = LocalDateTime.now();


        day.setText(weekDay.format(now));
        date.setText(dating.format(now));
        time.setText(timing.format(now).substring(0, 5));
    }
}

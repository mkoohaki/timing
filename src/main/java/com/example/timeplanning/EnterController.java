package com.example.timeplanning;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EnterController {
    @FXML
    private Label welcomeText;


    @FXML
    protected void onHelloButtonClick() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));

        welcomeText.setText(dtf.format(now).substring(11, 16));
    }
}

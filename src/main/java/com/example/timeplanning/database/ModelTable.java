package com.example.timeplanning.database;

import javafx.scene.control.Button;
import java.sql.SQLException;

public class ModelTable {

    private String activity, start, end;
    private Button button;

    AccountDatabase db = new AccountDatabase();

    public ModelTable(String activity, String start, String end) throws SQLException {

        this.activity = activity;
        this.start = start;
        this.end = end;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Button getButton() {

        return button;
    }
}


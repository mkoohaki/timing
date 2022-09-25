package com.example.timeplanning;

import javafx.scene.control.Button;

public class Activity {

    private String activity;
    private String start;
    private String end;
    private Button editButton;
    private Button deleteButton;

    public Activity() {
        this.activity = "";
        this.start = "";
        this.end = "";
    }

    public Activity(String activity, String start, String end) {
        this.activity = activity;
        this.start = start;
        this.end = end;
        this.editButton = new Button("E");
        this.deleteButton = new Button("D");
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

    public Button getEditButton() {
        return editButton;
    }

    public void setEditButton(Button button) {
        this.editButton = editButton;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(Button deleteButton) {
        this.deleteButton = deleteButton; }
}

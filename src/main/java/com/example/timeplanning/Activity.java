package com.example.timeplanning;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class Activity {

    private String activity, start, end;
    private Button update, delete;

    public Activity() {
        this.activity = "";
        this.start = "";
        this.end = "";
    }

    public Activity(String activity, String start, String end, Button update, Button delete) {
        this.activity = activity;
        this.start = start;
        this.end = end;
        this.update = update;
        this.delete = delete;

        update.setStyle("-fx-background-color: yellow; " +
                "-fx-font-weight: 900; " +
                "-fx-min-width: 26px; " +
                "-fx-min-height: 26px; " +
                "-fx-background-radius: 13px;");
        delete.setStyle("-fx-background-color: red; " +
                "-fx-font-weight: 900; " +
                "-fx-min-width: 26px; " +
                "-fx-min-height: 26px; " +
                "-fx-background-radius: 13px;");
//        delete.setOnAction(e -> {
//            try {
//
//                ObservableList<Activity> activities = ScheduleController.getActivity();
//                assert activities != null;
//                for (Activity act : activities) {
//                    if (act.getDeleteButton() == delete) {
//                        System.out.println(act.getActivity());
//                    }
//                }
//                //System.out.println(activities.get(0).getActivity());
//                    //https://www.youtube.com/watch?v=5DjvFTa2Q8o&ab_channel=RashidIqbal
////                }
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//        });
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

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
}

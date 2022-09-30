package com.example.timeplanning;

import com.example.timeplanning.database.AccountDatabase;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.sql.SQLException;

public class Activity {

    private String activity, start, end;
    private Button update;

    public Activity() {
        this.activity = "";
        this.start = "";
        this.end = "";
    }

    public Activity(String activity, String start, String end, Button update) {
        this.activity = activity;
        this.start = start;
        this.end = end;
        this.update = update;

        update.setStyle("-fx-background-color: yellow; " +
                "-fx-font-weight: 900; " +
                "-fx-min-width: 26px; " +
                "-fx-min-height: 26px; " +
                "-fx-background-radius: 13px;");


        update.setOnAction(e -> {
            ObservableList<Activity> activities = ScheduleController.table_info_2.getSelectionModel().getSelectedItems();
            //String activitie = ScheduleController.table_info_2.getItems().get(getActivity());
            String newActivity = null, newStart = null, newEnd = null;
            for (Activity act : activities) {
                if(act.getUpdate() == update) {
                    newActivity = act.getActivity();
                    newStart = act.getStart();
                    newEnd = act.getEnd();
                }
            }
            System.out.println();
            String oldActivity = getActivity();
            if(newActivity != null && newStart != null && newEnd != null) {
                System.out.println(newActivity);
                System.out.println(newStart);
                System.out.println(newEnd);
                try {
                    AccountDatabase db = new AccountDatabase();
                    db.update(oldActivity, newActivity, newStart, newEnd);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }



//            ObservableList<Activity> activities = ScheduleController.table_info_2.getSelectionModel().getSelectedItems();
//            assert activities != null;
//
//            for (Activity act : activities) {
//                System.out.println(act.getUpdate());
//                if (act.getUpdate() == update) {
//                    System.out.println(act.getActivity());
//                }
//            }
            //System.out.println(activities.get(0).getActivity());
            //https://www.youtube.com/watch?v=5DjvFTa2Q8o&ab_channel=RashidIqbal
//                }
        });
//        delete.setOnAction(e -> {
//            ObservableList<Activity> activities = ScheduleController.table_info_2.getSelectionModel().getSelectedItems();
//            //ObservableList<Activity> activities = ScheduleController.table_info_2.getRowFactory();
//
//            String pkid = null;
//            for (Activity act : activities) {
//                if(act.getDelete() == delete) {
//                    pkid = act.getActivity();
//                }
//            }
//            System.out.println(pkid);
//            AccountDatabase db;
//            try {
//                db = new AccountDatabase();
//                db.delete(pkid);
//                Partials.windowOpen("schedule", "Timing Plan", 620, 320);
//                Partials.windowClose(e);
//            } catch (Exception ex) {
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
}

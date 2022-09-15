package com.example.timeplanning;

public class Activity {

    private String activity;
    private String start;
    private String end;

    public Activity() {
        this.activity = "";
        this.start = "";
        this.end = "";
    }

    public Activity(String activity, String start, String end) {
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
}

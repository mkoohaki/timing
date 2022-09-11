package com.example.timeplanning.database;

public class DBTest {

    public static void main(String[] args) {
        try {
            AccountDatabase db = new AccountDatabase();
            db.insertRow("Coding", "19:45", "21:00");
            System.out.println("Success!");
        }
        catch (java.sql.SQLException e) {
            System.out.println("Connection failed! " + e);
        }
    }
}

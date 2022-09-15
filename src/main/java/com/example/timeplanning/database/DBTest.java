package com.example.timeplanning.database;

import java.sql.SQLException;
import java.util.Arrays;

public class DBTest {

    public static void main(String[] args) {
        try {
            AccountDatabase db = new AccountDatabase();
            //db.insertRow("Coding", "19:45", "21:00");

            String[][] rows = db.getAllRows();
            int row = 0;
            for(String[] columns : rows) {
                row++;
                System.out.println("Row # " + row);
                int columnCounter = 0;
                for(String column : columns) {
                    System.out.printf("Column # %d has value of %s%n", columnCounter, column);
                }
            }
//            String[] column = db.getAllColumns();
//            System.out.println(Arrays.toString(column));
            System.out.println("Success!");
        }
        catch (SQLException | NullPointerException e) {
            System.out.println("Connection failed! " + e);
        }
    }
}

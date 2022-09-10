package com.example.timeplanning.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.example.timeplanning.ModelTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDatabase extends Database {
    private final String activityTable = "activity_info";
    private final int numColumns = 3;
    private final int maxRowsReturned = 9;
    Connection connection = getConnection();
    Statement statement = connection.createStatement();
    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();
    ResultSet resultSet;
}

package com.example.timeplanning.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AccountDatabase extends Database {
    private final String planTable = "activity_info";
    private final int numColumns = 3;
    private final int maxRowsReturned = 9;
    Connection connection = getConnection();
    Statement statement = connection.createStatement();
    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();
    ResultSet resultSet;

    public AccountDatabase() throws SQLException {
    }

    @Override
    public void insertRow(String... columns) throws SQLException {
        String sql = String.format("INSERT INTO `%s` (`activity`, `start`, `end`) VALUE (?, ?, ?)", planTable);

        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, columns[0]);
        prepareStatement.setString(2, columns[1]);
        prepareStatement.setString(3, columns[2]);
    }
}

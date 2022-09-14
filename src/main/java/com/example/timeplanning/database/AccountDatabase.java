package com.example.timeplanning.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AccountDatabase extends Database {
    private final String table = "activity_info";
    private final int numColumns = 4;
    private final int maxRowsReturned = 9;
    private final String pkId = "id";
    Connection connection = getConnection();
    Statement statement = connection.createStatement();
    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();
    ResultSet resultSet;

    public AccountDatabase() throws SQLException {
        super();
    }

    @Override
    public int insertRow(String... columns) throws SQLException {
        String sql = String.format("INSERT INTO `%s` (`activity`, `start`, `end`) VALUE (?, ?, ?)", table);

        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, columns[0]);
        prepareStatement.setString(2, columns[1]);
        prepareStatement.setString(3, columns[2]);

        int numRows = prepareStatement.executeUpdate();
        return numRows;
    }

    @Override
    public String[][] getAllRows(String... columns) throws SQLException {
        String[][] data = new String[maxRowsReturned][numColumns];
        String sql = String.format("SELECT * FROM `%s` ORDER BY `%s` LIMIT %d", table, pkId, maxRowsReturned);
        statement = connection.createStatement();
        statement.executeQuery(sql);

        int row = 0;
        while (resultSet.next()) {
            row++;
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                data[row - 1][i - 1] = resultSet.getObject(i).toString();

            }
        }
        return data;
    }
}

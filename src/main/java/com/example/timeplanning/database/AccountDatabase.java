package com.example.timeplanning.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountDatabase extends Database {
    private final String TABLE = "activity_info";
    private final int NUMBEROFCOLUMNS = 3;
    private final int MAXROWSRETURNED = 20;
    private final String ORDERING = "start";
    private final String PKID = "activity";
    Connection connection = getConnection();
    Statement statement = connection.createStatement();
    ObservableList<ModelTable> observableList = FXCollections.observableArrayList();
    ResultSet resultSet;

    public AccountDatabase() throws SQLException {
        super();
    }

    @Override
    public int insertRow(String... columns) throws SQLException {
        String sql = String.format("INSERT INTO `%s` (`activity`, `start`, `end`) VALUE (?, ?, ?)", TABLE);

        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, columns[0]);
        prepareStatement.setString(2, columns[1]);
        prepareStatement.setString(3, columns[2]);

        int numRows = prepareStatement.executeUpdate();
        return numRows;
    }

//    @Override
//    public String[][] getAllRows() throws SQLException {
//
//        String[][] data = new String[MAXROWSRETURNED][NUMBEROFCOLUMNS];
//        String sql = String.format("SELECT * FROM `%s` ORDER BY `%s`", TABLE, ORDERING);
//        statement = connection.createStatement();
//        resultSet = statement.executeQuery(sql);
//
//        int row = 0;
//        while (resultSet.next()) {
//            row++;
//            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
//                data[row - 1][i - 1] = resultSet.getObject(i).toString();
//            }
//        }
//        return data;
//    }

    @Override
    public ArrayList<String[]> getAllRows() throws SQLException {

        String[][] data = new String[MAXROWSRETURNED][NUMBEROFCOLUMNS];

        String sql = String.format("SELECT * FROM `%s` ORDER BY `%s`", TABLE, ORDERING);
        statement = connection.createStatement();
        resultSet = statement.executeQuery(sql);

        int row = 0;
        while (resultSet.next()) {
            row++;
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                data[row - 1][i - 1] = resultSet.getObject(i).toString();
            }
        }
        ArrayList<String[]> list = new ArrayList<>();
        for(String[] eachRow : data) {
            if(eachRow[0] != null)
                list.add(eachRow);
        }
        return list;
    }

    @Override
    public String[] getAllColumns() throws SQLException {
        String[] data = new String[NUMBEROFCOLUMNS];
//        String sql = "SELECT * FROM" + table + "ORDER By " + pkId + "LIMIT " + maxRowsReturned;
        String sql = String.format("SELECT * FROM `%s` ORDER BY `%s` LIMIT %d", TABLE, PKID, MAXROWSRETURNED);
        statement = connection.createStatement();
        statement.executeQuery(sql);

        while (resultSet.next()) {
            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                data[i - 1] = resultSet.getMetaData().getColumnName(i);
            }
        }
        return data;
    }

    @Override
    public void update(String... columns) throws SQLException {
        String sql = String.format("UPDATE `%s` SET `activity` = ?, `start` = ?, `end` = ? WHERE `activity` = ?", TABLE);

        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, columns[1]);
        prepareStatement.setString(2, columns[2]);
        prepareStatement.setString(3, columns[3]);
        prepareStatement.setString(4, columns[0]);
        prepareStatement.executeUpdate();
    }

    @Override
    public void delete(String activity) throws SQLException {
        String sql = String.format("DELETE FROM `%s` WHERE `activity` = ?", TABLE);
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        prepareStatement.setString(1, activity);
        prepareStatement.executeUpdate();
    }

}

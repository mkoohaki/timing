package com.example.timeplanning.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Database {
    private String username = "root", password ="";
    private String host = "localhost";
    private int port = 3306;
    private String database = "planning_sheet";
    private String connectionString = "jdbc:mysql://"+ host + ":" + port + "/" + database;
    private Connection connection;
    public Database() throws SQLException {

        connection = DriverManager.getConnection(connectionString, username, password);
    }
    public Connection getConnection() {
        return connection;
    }

    public abstract void insertRow(String...columns) throws SQLException;
}

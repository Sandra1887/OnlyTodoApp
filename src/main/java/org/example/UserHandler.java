/*package org.example;

import java.sql.*;

public class UserHandler implements iCrud, iTable {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private Helper helper;
    private Todo todo;

    public UserHandler(String dbName) throws SQLException {
        getConnection(dbName);
        helper = new Helper();
    }

    public Connection getConnection(String dbName) throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
            } catch (SQLException e) {
                System.out.println("Error connecting to database: " + e.getMessage());
            }
        }
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public void read() {

    }

    @Override
    public void update() {

    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public void createTable(String tableName) {

    }

    @Override
    public boolean searchForTable(String tableName) {
        return false;
    }
}*/
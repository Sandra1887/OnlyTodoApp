/*package org.example;

import java.sql.*;

public class UserHandler implements iCrud, iTable {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private Helper helper;
    private ToDo todo;

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
        String tableName = helper.askForTableName();

        if(searchForTable(tableName) == true) {
            ToDo todo = helper.askForAssignment();
            String sql = "INSERT INTO " + tableName + " (assignment, assignee, done) VALUES (?,?,?)"; // plus för att du har paramtrar!

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, todo.getAssignment());
                pstmt.setString(2, todo.getAssignee());
                pstmt.setString(3, todo.getDone());
                pstmt.executeUpdate();
                return true; //assignment added
            } catch (SQLException e) {
                System.out.println("Error creating todo: " + e.getMessage());
                return false; //assignment not added
            }
        } if (searchForTable(tableName) == false) {
            createTable(tableName); //skapa en ny tabell
            create();
        }
        return true;
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
}
*/
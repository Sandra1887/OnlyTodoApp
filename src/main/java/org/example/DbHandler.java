package org.example;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class DbHandler implements iCrud, iTable {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private Helper helper;
    private Scanner sc;
    private ToDo todo;

    //private List<ToDo> todos;
    public DbHandler(String dbName) throws SQLException {
        getConnection(dbName);
        helper = new Helper();
        sc = new Scanner(System.in);
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
    public void create() {
        String tableName = helper.askForTableName();

        if (searchForTable(tableName) == true) {
            ToDo todo = helper.askForAssignment();
            String sql = "INSERT INTO " + tableName + " (assignment, assignee, done) VALUES (?,?,?)"; // plus för att du har paramtrar!

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, todo.getAssignment());
                pstmt.setString(2, todo.getAssignee());
                pstmt.setString(3, todo.getDone());
                pstmt.executeUpdate();
                System.out.println("Todo added to table " + tableName);
            } catch (SQLException e) {
                System.out.println("Error creating todo: " + e.getMessage());
                //return false; //assignment not added
            }
        }
        if (searchForTable(tableName) == false) {
            createTable(tableName); //skapa en ny tabell
            create();
        }
        //return true;
    }

    @Override
    public void read() {
        String tableName = helper.askForTableName();
        String sql = "SELECT * FROM " + tableName;
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String assignment = rs.getString("assignment");
                String assignee = rs.getString("assignee");
                String done = rs.getString("done");
                System.out.println("Assignment: " + assignment + ". Assignee: " + assignee + ". Done: " + done);
            }
            System.out.println("--------------------------");
        } catch (SQLException e) {
            System.out.println("Error reading table: " + e.getMessage());
        }
    }
    /*public void updateText() throws SQLException {
        String tableName = helper.askForTableName();
        String sql = "SELECT * FROM " + tableName;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int counter = 1;
            while (rs.next()) {
                String assignment = rs.getString("assignment");
                String assignee = rs.getString("assignee");
                String done = rs.getString("done");
                System.out.println(counter + ". Assignment: " + assignment + ". Assignee: " + assignee + ". Done: " + done);
                counter++;
            }

            int choice = helper.askForId();
            rs.absolute(choice);
            String chosenAssignment = rs.getString("assignment");
            String chosenAssignee = rs.getString("assignee");
            String chosenDone = rs.getString("done");
            String newAssignment = helper.askForOnlyAssignment();
            String sqlUpdate = "UPDATE " + tableName + " SET assignment = ? WHERE assignment = ?, assignee = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sqlUpdate)) {
                pstmt.setString(1, newAssignment); //parametern i String sql
                pstmt.setString(2, chosenAssignment); //parametern i String sql
                pstmt.setString(3, chosenAssignee);
                pstmt.executeUpdate();
                System.out.println("Table updated successfully");
            } catch (SQLException e) {
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println();
        }
    }
    public boolean updateDone() {
        String tableName = helper.askForTableName();
        String newDone = helper.askForDone();
        int id = helper.askForId();
        String sql = "UPDATE " + tableName + " SET done = ? WHERE todo_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newDone);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Table updated successfully");
            return true;
        } catch (SQLException e) {
            System.out.println("Error updating table: " + e.getMessage());
            return false;
        }
    }*/
    public void updateText() {
        String tableName = helper.askForTableName();
        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM " + tableName;
            rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                int id = rs.getInt("todo_id");
                String assignment = rs.getString("assignment");
                String assignee = rs.getString("assignee");
                String done = rs.getString("done");

                System.out.println("ID: " + id);
                System.out.println("Assignment: " + assignment);
                System.out.println("Assignee: " + assignee);
                System.out.println("Done: " + done);
                System.out.println("-------------------------");
            }
            int chosenId = helper.askForId();
            String newAssignment = helper.askForOnlyAssignment();

            String sqlUpdate = "UPDATE " + tableName + " SET assignment = ? WHERE todo_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sqlUpdate);
            pstmt.setString(1, newAssignment);
            pstmt.setInt(2, chosenId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateDone() {
        String tableName = helper.askForTableName();
        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM " + tableName;
            rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                int id = rs.getInt("todo_id");
                String assignment = rs.getString("assignment");
                String assignee = rs.getString("assignee");
                String done = rs.getString("done");

                System.out.println("ID: " + id);
                System.out.println("Assignment: " + assignment);
                System.out.println("Assignee: " + assignee);
                System.out.println("Done: " + done);
                System.out.println("-------------------------");
            }
            int chosenId = helper.askForId();
            String newDone = helper.askForDone();

            String sqlUpdate = "UPDATE " + tableName + " SET done = ? WHERE todo_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sqlUpdate);
            pstmt.setString(1, newDone);
            pstmt.setInt(2, chosenId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*@Override
    public void update() {
        String tableName = helper.askForTableName();
        String sql = "SELECT * FROM " + tableName;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int counter = 1;
            while (rs.next()) {
                String assignment = rs.getString("assignment");
                String assignee = rs.getString("assignee");
                String done = rs.getString("done");
                System.out.println(counter + ". Assignment: " + assignment + ". Assignee: " + assignee + ". Done: " + done);
                counter++;
                //result = true;
            }
        } catch (SQLException e) {
            System.out.println();
        }

        int choice = helper.askForId();
        String answer = helper.askForUpdate();

        try {
            rs.absolute(choice);
            String chosenAssignment = rs.getString("assignment");
            String chosenAssignee = rs.getString("assignee");
            String chosenDone = rs.getString("done");

            if (answer.equalsIgnoreCase("assignment")) {
                String newAssignment = helper.askForOnlyAssignment();
                chosenAssignment = newAssignment;
            } else if (answer.equalsIgnoreCase("done")) {
                String newDone = helper.askForDone();
                chosenDone = newDone;
            } else {
                System.out.println("Invalid input");
                return;
            }
            String sqlUpdate = "UPDATE " + tableName + " SET assignment = ?, done = ? WHERE assignment = ? AND assignee = ?";
            PreparedStatement pstmt = connection.prepareStatement(sqlUpdate);
            pstmt.setString(1, chosenAssignment);
            pstmt.setString(2, chosenDone);
            pstmt.setString(3, chosenAssignment);
            pstmt.setString(3, chosenAssignee);
            pstmt.executeUpdate();
            System.out.println("Todo updated successfully");
        } catch (SQLException e) {
            System.out.println("Error updating todo");
        }
    }*/

    @Override
    public void delete() {
        String tableName = helper.askForTableName();
        try {
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * FROM " + tableName;
            rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                int id = rs.getInt("todo_id");
                String assignment = rs.getString("assignment");
                String assignee = rs.getString("assignee");
                String done = rs.getString("done");

                System.out.println("ID: " + id);
                System.out.println("Assignment: " + assignment);
                System.out.println("Assignee: " + assignee);
                System.out.println("Done: " + done);
                System.out.println("-------------------------");
            }
            System.out.println("Enter Id and press enter");
            int chosenId = sc.nextInt();
            sc.nextLine();
            String sql = "DELETE FROM " + tableName + " WHERE todo_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, chosenId);
            pstmt.executeUpdate();
            System.out.println("ToDo deleted successfully");
        } catch (SQLException e) {
            System.out.println("Error deleting todo: " + e.getMessage());
        }
    }

    public void createTable(String tableName) {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                "todo_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "assignment VARCHAR(50)," +
                "assignee VARCHAR(50)," +
                "done VARCHAR(10)" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }
    public boolean searchForTable(String tableName) {
        try {
            stmt = connection.createStatement();
            String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return true; //table exists
            } else {
                return false; //Table does not exist
            }
        } catch (SQLException e) {
            System.out.println("Error message: " + e.getMessage());
        }
        return false;
    }
}
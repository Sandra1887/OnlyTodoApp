package org.example;

import java.sql.*;
import java.util.Scanner;

public class DbHandler implements iCrud, iTable {
    private Connection connection;
    private Statement stmt;
    private ResultSet rs;
    private Helper helper;
    private Scanner sc;

    public DbHandler(String dbName) throws SQLException {
        getConnection(dbName);
        helper = new Helper();
        sc = new Scanner(System.in);
    }

    /***
     * Connects to SQLite and creats a new database which name is given through given the parameter.
     * @param dbName
     * @return Connection
     * @throws SQLException
     */
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

    /***
     * Method that asks the user for table name. If the table name exists it continues to ask for the Todo that
     * should be created and then adds it to the table. If the table does not exist it creates a new table.
     * @return boolean - in this case. For an easier unit testing.
     */
    @Override
    public boolean create() {
        String tableName = helper.askForTableName();
        boolean success = false;

        if (searchForTable(tableName) == true) {
            Todo todo = helper.askForTodo();
            String sql = "INSERT INTO " + tableName + " (assignment, assignee, done) VALUES (?,?,?)"; // plus för att du har paramtrar!

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, todo.getAssignment());
                pstmt.setString(2, todo.getAssignee());
                pstmt.setString(3, todo.getDone());
                pstmt.executeUpdate();
                System.out.println("Todo added to table " + tableName);
                success = true;
            } catch (SQLException e) {
                System.out.println("Error creating todo: " + e.getMessage());
                success = false;
            }
        }
        if (searchForTable(tableName) == false) {
            createTable(tableName); //skapa en ny tabell
            create();
            success = true;
        }
        return success;
    }

    /***
     * Method that asks the user if they want to read one todo or all todos.
     */
    @Override
    public void read() {
        int answer = helper.askForRead(); //Read all or one?
        switch(answer) {
            case 1 -> readOne();
            case 2 -> readAll();
            default -> {
                System.out.println("Wrong input. Try again");
                read();
            }
        }
    }

    /***
     * Method that asks for the table name and then the Todo ID. Then shows the matching Todo.
     * @return boolean - in this case. For an easier unit testing.
     */
    public boolean readOne() {
        String tableName = helper.askForTableName();
        int id = helper.askForId();
        boolean success = false;
        String sql = "SELECT * FROM " + tableName + " WHERE todo_id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while(rs.next()) {
                String assignment = rs.getString("assignment");
                String assignee = rs.getString("assignee");
                String done = rs.getString("done");
                System.out.println("Assignment: " + assignment + ". Assignee: " + assignee + ". Done: " + done);
            }
            success = true;
        } catch (SQLException e) {
            System.out.println("Error reading statement: " + e.getMessage());
            success = false;
        }
        return success;
    }

    /***
     * Method that shows all the existing Todos in the selected table.
     * @return boolean - in this case. For an easier unit testing.
     */
    public boolean readAll() {
        String tableName = helper.askForTableName();
        boolean success = false;
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
            success = true;
        } catch (SQLException e) {
            System.out.println("Error reading table: " + e.getMessage());
            success = false;
        }
        return success;
    }

    /***
     * Methods that asks if the user would like to update an "assignment" or a "done".
     */
    @Override
    public void update() {
        int answer = helper.askForUpdate();
        switch(answer) {
            case 1 -> updateText();
            case 2 -> updateDone();
            default -> {
                System.out.println("Wrong input. Try again");
                update();
            }
        }
    }

    /***
     * Method that asks the user for table name and then shows all the existing Todos in it. The user is then asked to
     * enter Todo ID and the new assignment.
     * @return boolean - in this case. For an easier unit testing.
     */
    public boolean updateText() {
        String tableName = helper.askForTableName();
        boolean success = false;
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
            success = true;
        } catch (SQLException e) {
            System.out.println("Error updating ToDo: " + e.getMessage());
            success = false;
        }
        return success;
    }

    /***
     * Method that asks the user for table name and then shows all the existing Todos in it. The user is then asked to
     * enter Todo ID and the new done.
     * @return
     */
    public boolean updateDone() {
        String tableName = helper.askForTableName();
        boolean success = false;
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
            success = true;
        } catch (SQLException e) {
            System.out.println("Error updating ToDo: " + e.getMessage());
            success = false;
        }
        return success;
    }

    /***
     * Method that asks for table name and shows all the existing Todos in it. The user is then asked to select and
     * enter the ID of the Todo that should be deleted.
     * @return boolean - in this case. For an easier unit testing.
     */
    @Override
    public boolean delete() {
        String tableName = helper.askForTableName();
        boolean success = false;
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
            String sql = "DELETE FROM " + tableName + " WHERE todo_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, chosenId);
            pstmt.executeUpdate();
            System.out.println("ToDo deleted successfully");
            success = true;
        } catch (SQLException e) {
            System.out.println("Error deleting todo: " + e.getMessage());
            success = false;
        }
        return success;
    }

    /***
     * Method that creates a table if the entered table in "create()" does not exist.
     * @param tableName
     */
    @Override
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

    /***
     * Method that searches for a table in the database. If the table exists it returns true. If it does not exist
     * it returns false.
     * @param tableName
     * @return
     */
    @Override
    public boolean searchForTable(String tableName) {
        try {
            stmt = connection.createStatement();
            String sql = "SELECT name FROM sqlite_master WHERE type='table' AND name='" + tableName + "'";
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error message: " + e.getMessage());
        }
        return false;
    }
}
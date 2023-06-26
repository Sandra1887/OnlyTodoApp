package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.*;

class DbHandlerTest {
    Connection connectionMock;
    PreparedStatement pstmtMock;
    Statement stmtMock;
    ResultSet rsMock;
    DbHandler dbHandlerMock;
    Helper helperMock;
    String dbName = "testDbName";

    String tableMock = "testTable";
    String assignmentMock = "assignmentMock";
    String assigneeMock = "assigneeMock";
    String doneMock = "doneMock";
    @BeforeEach
    public void setUp() {
        connectionMock = mock(Connection.class);
        pstmtMock = mock(PreparedStatement.class);

        helperMock = mock(Helper.class);
        dbHandlerMock = mock(DbHandler.class);
        connectionMock = mock(Connection.class);
        pstmtMock = mock(PreparedStatement.class);

        stmtMock = mock(Statement.class);
        rsMock = mock(ResultSet.class);
    }
    @Test
    public void testConnection() throws SQLException {
        //specify the behavior of the mock connection
        when(connectionMock.isValid(0)).thenReturn(true);
        //Create an instance of the DbHandler
        dbHandlerMock = new DbHandler(dbName);
        //set the mock connection
        dbHandlerMock.setConnection(connectionMock);
        //Retrieve the information and perform tests
        Connection connection = dbHandlerMock.getConnection(dbName);
        //Verify the behavior of the mocked connection
        assertTrue(connection.isValid(0));
    }
    @Test
    public void testCreate() throws SQLException {
        //specify the behavior of the mock connection
        when(helperMock.askForTableName()).thenReturn(tableMock);
        when(dbHandlerMock.searchForTable(tableMock)).thenReturn(false).thenReturn(true);
        //create an instance of a new Todo
        Todo mockToDo = new Todo(assignmentMock, assigneeMock, doneMock);
        when(helperMock.askForTodo()).thenReturn(mockToDo);

        when(connectionMock.prepareStatement(Mockito.anyString())).thenReturn(pstmtMock);
        when(pstmtMock.executeUpdate()).thenReturn(1);

        boolean result = dbHandlerMock.create();

        assertFalse(result);
    }

    @Test
    public void testReadOne() throws SQLException {
        when(helperMock.askForTableName()).thenReturn(tableMock);
        when(helperMock.askForId()).thenReturn(1);
        when(connectionMock.prepareStatement(anyString())).thenReturn(pstmtMock);
        when(pstmtMock.executeQuery()).thenReturn(rsMock);

        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getString("assignment")).thenReturn(assignmentMock);
        when(rsMock.getString("assignee")).thenReturn(assigneeMock);
        when(rsMock.getString("done")).thenReturn(doneMock);

        boolean result = dbHandlerMock.readOne();

        assertFalse(result);
    }
    @Test
    public void testReadAll() throws SQLException {
        when(helperMock.askForTableName()).thenReturn(tableMock);
        when(connectionMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.executeQuery(anyString())).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getString("assignment")).thenReturn(assignmentMock);
        when(rsMock.getString("assignee")).thenReturn(assigneeMock);
        when(rsMock.getString("done")).thenReturn(doneMock);
        boolean expected = true;
        when(dbHandlerMock.readAll()).thenReturn(expected);
        boolean actual = dbHandlerMock.readAll();

        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateText() throws SQLException {
        when(helperMock.askForTableName()).thenReturn(tableMock);
        when(helperMock.askForId()).thenReturn(1);
        when(helperMock.askForOnlyAssignment()).thenReturn("newAssignmentMock");

        when(connectionMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.executeQuery(anyString())).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getInt("todo_id")).thenReturn(1);
        when(rsMock.getString("assignment")).thenReturn("oldAssignmentMock");
        when(rsMock.getString("assignee")).thenReturn(assigneeMock);
        when(rsMock.getString("done")).thenReturn(doneMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(pstmtMock);
        when(pstmtMock.executeUpdate()).thenReturn(1);

        boolean result = dbHandlerMock.updateText();

        assertFalse(result);
    }
    @Test
    public void testUpdateDone() throws SQLException {
        when(helperMock.askForTableName()).thenReturn(tableMock);
        when(helperMock.askForId()).thenReturn(1);
        when(helperMock.askForDone()).thenReturn("newDoneMock");

        when(connectionMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.executeQuery(anyString())).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true, false);
        when(rsMock.getInt("todo_id")).thenReturn(1);
        when(rsMock.getString("assignment")).thenReturn(assignmentMock);
        when(rsMock.getString("assignee")).thenReturn(assigneeMock);
        when(rsMock.getString("done")).thenReturn("oldDoneMock");
        when(connectionMock.prepareStatement(anyString())).thenReturn(pstmtMock);
        when(pstmtMock.executeUpdate()).thenReturn(1);

        boolean result = dbHandlerMock.updateText();

        assertFalse(result);
    }
    @Test
    public void testDelete() throws SQLException {
        when(helperMock.askForTableName()).thenReturn(tableMock);
        when(helperMock.askForId()).thenReturn(1);

        when(connectionMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.executeQuery(anyString())).thenReturn(rsMock);


        when(rsMock.getInt("todo_id")).thenReturn(1);
        when(rsMock.getString("assignment")).thenReturn(assignmentMock);
        when(rsMock.getString("assignee")).thenReturn(assigneeMock);
        when(rsMock.getString("done")).thenReturn(doneMock);

        when(connectionMock.prepareStatement(anyString())).thenReturn(pstmtMock);
        when(pstmtMock.executeUpdate()).thenReturn(1);

        boolean result = dbHandlerMock.delete();

        assertFalse(result);
    }
}
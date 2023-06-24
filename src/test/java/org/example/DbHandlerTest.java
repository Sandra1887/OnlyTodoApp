package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DbHandlerTest {
    Connection connectionMock;
    PreparedStatement pstmtMock;
    Statement stmtMock;
    ResultSet rsMock;
    DbHandler dbHandlerMock;
    Helper helperMock;
    String dbName = "testDbName";
    @BeforeEach
    public void setUp() throws SQLException {
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
        //create a mock connection object
        //specify the behavior of the mock connection
        when(connectionMock.isValid(0)).thenReturn(true);
        //Create an instance of the DbHandler
        dbHandlerMock = new DbHandler(dbName); //<-- onödig?
        //set the mock connection
        dbHandlerMock.setConnection(connectionMock);
        //Retrieve the information and perform tests
        Connection connection = dbHandlerMock.getConnection(dbName);
        //Verify the behavior of the mocked connection
        assertTrue(connection.isValid(0));
    }
    @Test
    public void testCreate() throws SQLException {
        when(helperMock.askForTableName()).thenReturn("tableMock");
        when(dbHandlerMock.searchForTable("tableMock")).thenReturn(false).thenReturn(true);
        ToDo mockToDo = new ToDo("assignmentMock", "assigneeMock", "doneMock");
        when(helperMock.askForTodo()).thenReturn(mockToDo);

        when(connectionMock.prepareStatement(Mockito.anyString())).thenReturn(pstmtMock);
        when(pstmtMock.executeUpdate()).thenReturn(1);

        boolean result = dbHandlerMock.create();

        assertFalse(result);
    }

}
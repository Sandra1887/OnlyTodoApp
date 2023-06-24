package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HelperTest {
    Scanner mockScanner;
    Helper mockHelper;
    @BeforeEach
    void setUp() throws SQLException {
        mockScanner = mock(Scanner.class);
        mockHelper = new Helper();
        mockHelper.sc = mockScanner;
    }
    @Test
    public void testAskForDbName() {
        when(mockScanner.nextLine()).thenReturn("mockedDbName");
        String dbName = mockHelper.askForDbName();
        Mockito.verify(mockScanner).nextLine();
        assertEquals("mockedDbName", dbName);
    }
    @Test
    public void testAskForTableName() {
        when(mockScanner.nextLine()).thenReturn("mockedTableName");
        String tableName = mockHelper.askForTableName();
        Mockito.verify(mockScanner).nextLine();
        assertEquals("mockedTableName", tableName);
    }
    @Test
    public void testAskForOnlyAssignment() {
        when(mockScanner.nextLine()).thenReturn("mockedAssignment");
        String assignment = mockHelper.askForOnlyAssignment();
        Mockito.verify(mockScanner).nextLine();
        assertEquals("mockedAssignment", assignment);
    }
    @Test
    public void testAskForDone() {
        when(mockScanner.nextLine()).thenReturn("mockedDone");
        String done = mockHelper.askForDone();
        Mockito.verify(mockScanner).nextLine();
        assertEquals("mockedDone", done);
    }
    @Test
    public void testAskForTodo() {
        when(mockScanner.nextLine())
                .thenReturn("mockAssignment")
                .thenReturn("mockAssignee")
                .thenReturn("mockDone");

        ToDo result = mockHelper.askForTodo();

        assertEquals("mockAssignment", result.getAssignment());
        assertEquals("mockAssignee", result.getAssignee());
        assertEquals("mockDone", result.getDone());
    }
    @Test
    public void testAskForId() {
        when(mockScanner.nextInt()).thenReturn(1);
        int id = mockHelper.askForId();
        Mockito.verify(mockScanner).nextInt();
        assertEquals(1, id);
    }
}
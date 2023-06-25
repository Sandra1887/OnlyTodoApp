package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ToDoTest {
    ToDo todoMock;
    @BeforeEach
    public void setUp() {
        todoMock = mock(ToDo.class);
    }

    @Test
    public void testSetAssignment() {
        String expected = "mockedAssignment";
        todoMock.setAssignment(expected);

        Mockito.verify(todoMock).setAssignment("mockedAssignment");
    }
    @Test
    public void testGetAssignment() {
        //Arrange
        String expected = "mockedAssignment";
        when(todoMock.getAssignment()).thenReturn(expected);
        //Act
        String actual = todoMock.getAssignment();
        //Assert
        assertEquals(expected, actual);

    }
    @Test
    public void testSetAssignee() {
        String expected = "mockedAssignee";
        todoMock.setAssignee(expected);

        Mockito.verify(todoMock).setAssignee("mockedAssignee");
    }
    @Test
    public void testGetAssignee() {
        String expected = "mockedAssignee";
        when(todoMock.getAssignee()).thenReturn(expected);
        //Act
        String actual = todoMock.getAssignee();
        //Assert
        assertEquals(expected, actual);
    }
    @Test
    public void testSetDone() {
        String expected = "mockedDone";
        todoMock.setDone(expected);

        Mockito.verify(todoMock).setDone("mockedDone");
    }
    @Test
    public void testGetDone() {
        String expected = "mockedDone";
        when(todoMock.getDone()).thenReturn(expected);

        String actual = todoMock.getDone();

        assertEquals(expected, actual);
    }
}
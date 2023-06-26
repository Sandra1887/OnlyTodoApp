package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TodoTest {
    Todo todo;
    String assignment;
    String assignee;
    String done;

    @BeforeEach
    public void setUp() {
        todo = new Todo("Bicicleta", "Zlatan", "Yes");
        assignment = "Bicicleta";
        assignee = "Zlatan";
        done = "Yes";
    }
    @Test
    public void testGetAssignment() {
        assertEquals(assignment, todo.getAssignment());
    }
    @Test
    public void testGetAssignmentNotEquals() {
        String assignment = "Volley";
        assertNotEquals(assignment, todo.getAssignment());
    }

    @Test
    public void testSetAssignment() {
        todo.setAssignment(assignment);
        assertEquals(assignment, todo.getAssignment());
    }
    @Test
    public void testGetAssignee() {
        assertEquals(assignee, todo.getAssignee());
    }

    @Test
    public void testSetAssignee() {
        todo.setAssignee(assignee);
        assertEquals(assignee, todo.getAssignee());
    }

    @Test
    public void testGetDone() {
        assertEquals(done, todo.getDone());
    }
    @Test
    public void testGetDoneNotEquals() {
        String done = "Nope";
        assertNotEquals(done, todo.getDone());
    }

    @Test
    public void testSetDone() {
        todo.setDone(done);
        assertEquals(done, todo.getDone());
    }
    @Test
    public void testGetAssigneeNotEquals() {
        String assignee = "Beckham";
        assertNotEquals(assignee, todo.getAssignee());
    }
}

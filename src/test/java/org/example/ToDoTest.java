package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ToDoTest {

    @Test
    public void testGetAssignment() {
        String assignment = "Finish homework";
        ToDo todo = new ToDo(assignment, "John Doe", "No");
        Assertions.assertEquals(assignment, todo.getAssignment());
    }

    @Test
    public void testSetAssignment() {
        String assignment = "Finish project";
        ToDo todo = new ToDo("Do laundry", "Jane Smith", "Yes");
        todo.setAssignment(assignment);
        Assertions.assertEquals(assignment, todo.getAssignment());
    }

    @Test
    public void testGetAssignee() {
        String assignee = "John Doe";
        ToDo todo = new ToDo("Write report", assignee, "No");
        Assertions.assertEquals(assignee, todo.getAssignee());
    }

    @Test
    public void testSetAssignee() {
        String assignee = "Jane Smith";
        ToDo todo = new ToDo("Clean the house", "John Doe", "Yes");
        todo.setAssignee(assignee);
        Assertions.assertEquals(assignee, todo.getAssignee());
    }

    @Test
    public void testGetDone() {
        String done = "No";
        ToDo todo = new ToDo("Buy groceries", "Jane Smith", done);
        Assertions.assertEquals(done, todo.getDone());
    }

    @Test
    public void testSetDone() {
        String done = "Yes";
        ToDo todo = new ToDo("Submit report", "John Doe", "No");
        todo.setDone(done);
        Assertions.assertEquals(done, todo.getDone());
    }
}

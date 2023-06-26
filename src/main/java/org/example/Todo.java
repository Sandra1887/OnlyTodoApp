package org.example;

public class Todo {
    private String assignment;
    private String assignee;
    private String done;

    public Todo(String assignment, String assignee, String done) {
        setAssignment(assignment);
        setAssignee(assignee);
        setDone(done);
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
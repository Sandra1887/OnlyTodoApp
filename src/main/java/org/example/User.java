package org.example;

import java.util.List;

//Ej kopplad till app eller testad

public class User {
    private int id;
    private String name;
    private int age;
    private List<Todo> todos;

    public User(int id, String name, int age, List<Todo> todos) {
        setId(id);
        setName(name);
        setAge(age);
        setTodos(todos);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }
}

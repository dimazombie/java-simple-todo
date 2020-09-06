package org.example.model;

public class Task {
    public int id;
    public String title;
    public Boolean isDone = false;

    public Task(String title) {
        this.title = title;
    }

    public Task(int id, String title, Boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }
}

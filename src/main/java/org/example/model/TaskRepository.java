package org.example.model;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    static List<Task> tasks = new ArrayList<>();

    static int count = 0;

    DataSource dataSource;

    public TaskRepository() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource)ic.lookup("java:comp/env/jdbc/ds");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getById(int id) {
        for (Task task : tasks) {
            if(task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public Task persist(Task task) {
        if(task.getId() == 0) {
            task.id = ++count;
            tasks.add(task);
        } else {
            tasks.remove(task);
            tasks.add(task);
        }
        return task;
    }

    public void delete(Task task) {
        tasks.remove(task);
    }
}

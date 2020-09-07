package org.example.model;

import java.util.List;

public interface TaskRepository  {
    List<Task> getAllTasks();

    Task getById(int id);

    void persist(Task task);

    Task insert(Task task);

    void delete(Task task);
}

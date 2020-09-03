package org.example.rest;

import org.example.model.Task;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Path("task")
@Produces("application/json")
public class TaskResource {

    static List<Task> tasks = new ArrayList<>(
            asList(new Task("task 1"), new Task("task 2")));

    @GET
    public List<Task> getAllTasks() {
        return tasks;
    }

    @POST
    public Task createTask(@FormParam("title") String title) {
        Task task = new Task(title);
        tasks.add(task);
        return task;
    }

    @PUT
    public void toggle(@FormParam("id") int id) {
        Task task = tasks.get(id-1);
        task.setIsDone(!task.getIsDone());
    }
}

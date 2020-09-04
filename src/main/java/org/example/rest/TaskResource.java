package org.example.rest;

import org.example.model.Task;
import org.example.model.TaskRepository;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Path("task")
@Produces("application/json")
public class TaskResource {

    TaskRepository repo = new TaskRepository();

    @GET
    public List<Task> getAllTasks() {
        return repo.getAllTasks();
    }

    @POST
    public Task createTask(@FormParam("title") String title) {
        return repo.persist(new Task(title));
    }

    @PUT
    public void toggle(@FormParam("id") int id) {
        Task task = repo.getById(id);
        task.setIsDone(!task.getIsDone());
        repo.persist(task);
    }

    @DELETE
    public void delete(@FormParam("id") int id) {
        Task task = repo.getById(id);
        repo.delete(task);
    }
}

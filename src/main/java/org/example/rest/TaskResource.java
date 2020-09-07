package org.example.rest;

import org.example.model.Task;
import org.example.model.TaskRepository;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("task")
@Produces("application/json")
public class TaskResource {

    @Inject TaskRepository repo;

    @GET
    public List<Task> getAllTasks() {
        return repo.getAllTasks();
    }

    @POST
    public Task createTask(@FormParam("title") String title) {
        return repo.insert(new Task(title));
    }

    @PUT
    public void toggle(@FormParam("id") int id) {
        Task task = repo.getById(id);
        task.isDone ^= true;
        repo.persist(task);
    }

    @DELETE
    public void delete(@FormParam("id") int id) {
        Task task = repo.getById(id);
        repo.delete(task);
    }
}

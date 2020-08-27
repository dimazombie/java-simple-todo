package org.example.rest;

import org.example.model.Task;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

import static java.util.Arrays.asList;

@Path("task")
@Produces("application/json")
public class TaskResource {
    @GET
    public List<Task> getAllTasks() {
        return asList(new Task("task 1"), new Task("task 2"));
    }
}

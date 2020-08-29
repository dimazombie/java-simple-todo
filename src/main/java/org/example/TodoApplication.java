package org.example;

import org.example.rest.TaskResource;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class TodoApplication extends Application {
    HashSet<Object> singletons = new HashSet<Object>();

    public TodoApplication() {
        singletons.add(new TaskResource());
    }

    @Override
    public Set<Class<?>> getClasses() {
        HashSet<Class<?>> set = new HashSet<Class<?>>();
        return set;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}

package org.example;

import org.example.util.DataSourceProvider;
import org.example.model.JDBCTaskRepository;
import org.example.model.TaskRepository;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;
import javax.sql.DataSource;

public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
            register(new AbstractBinder() {
                @Override
                protected void configure() {
                    bind(JDBCTaskRepository.class).to(TaskRepository.class).in(RequestScoped.class);
                    bind(new DataSourceProvider().provide()).to(DataSource.class);
                }
            });
    }
}

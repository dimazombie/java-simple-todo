package org.example.model;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTaskRepository implements TaskRepository {

    @Inject DataSource dataSource;

    public List<Task> getAllTasks() {
        Connection conn = null;
        try {
            List<Task> tasks = new ArrayList<Task>();
            conn = dataSource.getConnection();
            ResultSet rs = conn.prepareStatement("select * from TASK").executeQuery();
            while (rs.next()) {
                tasks.add(new Task(rs.getInt("id"), rs.getString("title"),
                        rs.getBoolean("isDone")));
            }
            return tasks;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeSilently(conn);
        }
    }

    public Task getById(int id) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("select t.* from TASK t where t.id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new Task(rs.getInt("id"), rs.getString("title"),
                        rs.getBoolean("isDone"));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeSilently(conn);
        }
        return null;
    }

    public void persist(Task task) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "update TASK t set t.title = ?, t.isdone = ? where t.id = ?");
            ps.setString(1, task.title);
            ps.setBoolean(2, task.isDone);
            ps.setInt(3, task.id);
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeSilently(conn);
        }
    }

    public Task insert(Task task) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                    "insert into TASK(title) values(?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.title);
            ps.execute();
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    task.id = (generatedKeys.getInt("id"));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeSilently(conn);
        }
        return task;
    }

    public void delete(Task task) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement("delete from TASK t where t.id = ?");
            ps.setInt(1, task.id);
            ps.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            closeSilently(conn);
        }
    }

    private void closeSilently(Connection conn) {
        try {
            if (conn != null) conn.close();
        }
        catch (SQLException ignore) {
        }
    }
}

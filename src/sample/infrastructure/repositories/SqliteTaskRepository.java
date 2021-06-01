package sample.infrastructure.repositories;

import sample.domain.task.Task;
import sample.domain.task.TaskRepository;
import sample.domain.task.TaskStatus;
import sample.infrastructure.factories.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SqliteTaskRepository implements TaskRepository {
    @Override
    public Task find(UUID id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tasks WHERE id = ?");
        stmt.setString(1, id.toString());
        ResultSet result = stmt.executeQuery();

        return new Task(
            UUID.fromString(result.getString("id")),
            result.getInt("status"),
            result.getString("title"),
            result.getDouble("estimated_time")
        );
    }

    @Override
    public List<Task> findAllByStatus(TaskStatus status) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tasks WHERE status = ?");
        stmt.setString(1, status.name());
        ResultSet result = stmt.executeQuery();

        List<Task> tasks = new ArrayList<Task>();

        while (result.next()) {
            tasks.add(new Task(
                UUID.fromString(result.getString("id")),
                result.getInt("status"),
                result.getString("title"),
                result.getDouble("estimated_time")
            ));
        }

        return tasks;
    }

    @Override
    public void store(Task task) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO tasks (id, title, status, estimated_time) VALUES (?, ?, ?, ?)");
        stmt.setString(1, task.getId().toString());
        stmt.setString(2, task.getTitle());
        stmt.setInt(3, Integer.parseInt(task.getStatus().name()));
        stmt.setDouble(4, task.getEstimatedTime());
        stmt.executeQuery();
    }

    @Override
    public void delete(UUID id) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
        stmt.setString(1, id.toString());
        ResultSet result = stmt.executeQuery();
    }

    @Override
    public void update(Task task) {

    }
}

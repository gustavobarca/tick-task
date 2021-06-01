package sample.domain.task;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public interface TaskRepository {
    Task find(UUID id) throws SQLException;

    List<Task> findAllByStatus(TaskStatus status) throws SQLException;

    void store(Task task) throws SQLException;

    void delete(UUID id) throws SQLException;

    void update(Task task);
}

package sample.domain;

import java.io.IOException;
import java.util.List;

public interface TaskRepository {
    Task find(int id) throws IOException;

    List<Task> findAll() throws IOException;

    List<Task> findAllByStatus(TaskStatus status) throws IOException;

    void store(Task task) throws IOException;

    void delete(int taskId) throws IOException;

    void update(Task task) throws IOException;
}

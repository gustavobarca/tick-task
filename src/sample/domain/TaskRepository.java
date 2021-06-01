package sample.domain;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface TaskRepository {
    Task find(UUID id) throws IOException;

    List<Task> findAllByStatus(TaskStatus status) throws IOException;

    void store(Task task) throws IOException;

    void delete(Task task) throws IOException;

    void update(Task task);
}

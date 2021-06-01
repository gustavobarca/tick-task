package sample.application;

import sample.domain.tag.Tag;
import sample.domain.tag.TagRepository;
import sample.domain.task.Task;
import sample.domain.task.TaskRepository;
import sample.domain.task.TaskStatus;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class TimeTrackingService {
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;

    public TimeTrackingService(TaskRepository taskRepository, TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.tagRepository = tagRepository;
    }

    public void addEntry(UUID taskId) throws SQLException {
        Task task = taskRepository.find(taskId);

        if (task == null)
            throw new InvalidParameterException("The task does not exist");

        task.addEntry();
        taskRepository.store(task);
    }

    public void deleteTask(UUID taskId) throws SQLException {
        taskRepository.delete(taskId);
    }

    public void updateTask(UUID taskId, String title, double estimatedTime) throws SQLException {
        Task task = taskRepository.find(taskId);

        if (task == null)
            throw new InvalidParameterException("The task does not exist");

        task.setTitle(title);
        task.setEstimatedTime(estimatedTime);

        taskRepository.update(task);
    }

    public List<Task> getAllTasksToDo() throws SQLException {
        return taskRepository.findAllByStatus(TaskStatus.TO_DO);
    }

    public List<Task> getAllTasksDone() throws SQLException {
        return taskRepository.findAllByStatus(TaskStatus.DONE);
    }

    public void createTag(String tagName) {
        tagRepository.store(new Tag(tagName));
    }

    public void setTagToTask(UUID tagId, UUID taskId) throws SQLException {
        Task task = taskRepository.find(taskId);

        if (task == null)
            throw new InvalidParameterException("The task does not exist");

        Tag tag = tagRepository.find(tagId);
        task.setTag(tag);
        taskRepository.update(task);
    }
}

package sample.application;

import sample.domain.Task;
import sample.domain.TaskRepository;
import sample.domain.TaskStatus;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

public class TimeTrackingService {
    private final TaskRepository taskRepository;

    public TimeTrackingService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addEntry(UUID taskId) throws IOException {
        Task task = taskRepository.find(taskId);

        if (task == null)
            throw new InvalidParameterException("The task does not exist");

        task.addEntry();
        taskRepository.store(task);
    }

    public void deleteTask(Task task) throws IOException {
        taskRepository.delete(task);
    }

    public void updateTask(UUID taskId, String title, double estimatedTime) throws IOException {
        Task task = taskRepository.find(taskId);

        if (task == null)
            throw new InvalidParameterException("The task does not exist");

        task.setTitle(title);
        task.setEstimatedTime(estimatedTime);

        taskRepository.update(task);
    }

    public List<Task> getAllTasksToDo() throws IOException {
        return taskRepository.findAllByStatus(TaskStatus.TO_DO);
    }

    public List<Task> getAllTasksDone() throws IOException {
        return taskRepository.findAllByStatus(TaskStatus.DONE);
    }
}

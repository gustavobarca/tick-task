package sample.application;

import sample.domain.Task;
import sample.domain.TaskRepository;
import sample.domain.TaskStatus;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TimeTrackingService {
    private final TaskRepository taskRepository;

    public TimeTrackingService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void addEntryToTask(int taskId) throws IOException {
        Task task = taskRepository.find(taskId);

        if (task == null)
            throw new InvalidParameterException("The task does not exist");

        task.addEntry();
        taskRepository.update(task);
    }

    public void addTask(String name, int hours, int minutes, int seconds) throws IOException {
        taskRepository.store(new Task(name, hours, minutes, seconds));
    }

    public void deleteTask(int taskId) throws IOException {
        taskRepository.delete(taskId);
    }

    public List<Task> getAllTasksToDo() throws IOException {
        List<Task> tasks = taskRepository.findAllByStatus(TaskStatus.TO_DO);
        return tasks;
    }

    public List<Task> getAllTasksDone() throws IOException {
        return taskRepository.findAllByStatus(TaskStatus.DONE);
    }

    public void finishTask(int taskId) throws Exception {
        Task task = taskRepository.find(taskId);

        if (task == null) {
            throw new Exception("The task does not exists!");
        }

        task.finish();
        taskRepository.update(task);
    }

    public List<Task> getAllDoingTasks() throws IOException {
        return taskRepository.findAllByStatus(TaskStatus.DOING);
    }
}

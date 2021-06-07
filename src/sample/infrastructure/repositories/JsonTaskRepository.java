package sample.infrastructure.repositories;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import sample.domain.Task;
import sample.domain.TaskRepository;
import sample.domain.TaskStatus;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonTaskRepository implements TaskRepository {
    private static String databasePath = "src/sample/infrastructure/repositories/database.json";

    private List<Task> getTasks() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(databasePath)));
        Gson gson = new Gson();
        List<Task> tasks = gson.fromJson(contents, new TypeToken<List<Task>>(){}.getType());
        return tasks == null ? new ArrayList<>() : tasks;
    }

    private void writeTasks(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(databasePath);
        Gson gson = new Gson();
        String list = gson.toJson(tasks);
        writer.write(list);
        writer.close();
    }

    @Override
    public Task find(int id) throws IOException {
        Task returnTask = null;
        List<Task> tasks = getTasks();

        for (Task task: tasks) {
            if (task.getId() == id) {
                returnTask = task;
                break;
            }
        }

        return returnTask;
    }

    @Override
    public List<Task> findAll() throws IOException {
        return getTasks();
    }

    @Override
    public List<Task> findAllByStatus(TaskStatus status) throws IOException {
        List<Task> tasksByStatus = new ArrayList<>();
        for (Task task : getTasks()) {
            if (task.getStatus() == status) {
                tasksByStatus.add(task);
            }
        }

        return tasksByStatus;
    }

    @Override
    public void store(Task task) throws IOException {
        List<Task> tasks = getTasks();

        int id = 1;

        if (tasks.size() > 0) {
            Task last = tasks.get(tasks.size() - 1);
            id = last.getId() + 1;
        }

        task.setId(id);
        tasks.add(task);
        writeTasks(tasks);
    }

    @Override
    public void delete(int taskId) throws IOException {
        List<Task> storedTasks = getTasks();

        for (int i = 0; i < storedTasks.size(); i++) {
            Task task = storedTasks.get(i);
            if (task.getId() == taskId) storedTasks.remove(task);
        }

        writeTasks(storedTasks);
    }

    @Override
    public void update(Task taskToUpdate) throws IOException {
        List<Task> storedTasks = getTasks();

        for (int i = 0; i < storedTasks.size(); i++) {
            Task task = storedTasks.get(i);

            if (task.getId() == taskToUpdate.getId()) {
                storedTasks.set(i, taskToUpdate);
            }
        }

        writeTasks(storedTasks);
    }
}

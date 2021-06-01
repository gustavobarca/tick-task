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
import java.util.UUID;

public class JsonTaskRepository implements TaskRepository {
    private static String databasePath = "../../../../db/databse.json";

    private List<Task> getTasks() throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get(databasePath)));
        Gson gson = new Gson();
        return gson.fromJson(contents, new TypeToken<List<Task>>(){}.getType());
    }

    private void writeJson(String jsonString) throws IOException {
        FileWriter writer = new FileWriter(databasePath);
        writer.write(jsonString);
    }

    @Override
    public Task find(UUID id) throws IOException {
        Task returnTask = null;
        for (Task task: getTasks()) {
            if (task.getId() == id) {
                returnTask = task;
                break;
            }
        }
        return returnTask;
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
        tasks.add(task);
        writeJson(tasks.toString());
    }

    @Override
    public void delete(Task task) throws IOException {
        List<Task> tasks = getTasks();
        tasks.remove(task);
        writeJson(tasks.toString());
    }

    @Override
    public void update(Task task) {

    }
}

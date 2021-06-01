//package sample.infrastructure.repositories;
//
//import sample.domain.Entry;
//import sample.domain.Task;
//import sample.domain.TaskRepository;
//import sample.domain.TaskStatus;
//import sample.infrastructure.factories.ConnectionFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Stack;
//import java.util.UUID;
//
//public class SqliteTaskRepository implements TaskRepository {
//    @Override
//    public Task find(UUID id) throws SQLException {
//        Connection connection = ConnectionFactory.getConnection();
//
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tasks WHERE id = ?");
//        stmt.setString(1, id.toString());
//        ResultSet result = stmt.executeQuery();
//
//        Task task = new Task(
//            UUID.fromString(result.getString("id")),
//            result.getInt("status"),
//            result.getString("title"),
//            result.getDouble("estimated_time")
//        );
//
//        PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM entries WHERE task_id = ?");
//        stmt2.setString(1, id.toString());
//        ResultSet entriesResult = stmt2.executeQuery();
//
//        Stack<Entry> entries = new Stack<Entry>();
//
//        while (entriesResult.next()) {
//            entries.push(new Entry(
//                UUID.fromString(entriesResult.getString("id")),
//                entriesResult.getInt("is_start") == 1,
//                entriesResult.getDate("datetime")
//            ));
//        }
//
//        task.setEntries(entries);
//        return task;
//    }
//
//    @Override
//    public List<Task> findAllByStatus(TaskStatus status) throws SQLException {
//        Connection connection = ConnectionFactory.getConnection();
//
//        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM tasks WHERE status = ?");
//        stmt.setString(1, status.name());
//        ResultSet result = stmt.executeQuery();
//
//        List<Task> tasks = new ArrayList<Task>();
//
//        PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM entries WHERE task_id = ?");
//        stmt2.setString(1, id.toString());
//        ResultSet entriesResult = stmt2.executeQuery();
//
//        Stack<Entry> entries = new Stack<Entry>();
//
//        while (entriesResult.next()) {
//            entries.push(new Entry(
//                    UUID.fromString(entriesResult.getString("id")),
//                    entriesResult.getInt("is_start") == 1,
//                    entriesResult.getDate("datetime")
//            ));
//        }
//
//        while (result.next()) {
//            tasks.add(new Task(
//                UUID.fromString(result.getString("id")),
//                result.getInt("status"),
//                result.getString("title"),
//                result.getDouble("estimated_time")
//            ));
//        }
//
//        return tasks;
//    }
//
//    @Override
//    public void store(Task task) throws SQLException {
//        Connection connection = ConnectionFactory.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("INSERT INTO tasks (id, title, status, estimated_time) VALUES (?, ?, ?, ?)");
//        stmt.setString(1, task.getId().toString());
//        stmt.setString(2, task.getTitle());
//        stmt.setInt(3, Integer.parseInt(task.getStatus().name()));
//        stmt.setDouble(4, task.getEstimatedTime());
//        stmt.executeQuery();
//    }
//
//    @Override
//    public void delete(UUID id) throws SQLException {
//        Connection connection = ConnectionFactory.getConnection();
//        PreparedStatement stmt = connection.prepareStatement("DELETE FROM tasks WHERE id = ?");
//        stmt.setString(1, id.toString());
//        ResultSet result = stmt.executeQuery();
//    }
//
//    @Override
//    public void update(Task task) {
//
//    }
//}

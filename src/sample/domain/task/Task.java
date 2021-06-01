package sample.domain.task;

import sample.domain.tag.Tag;

import java.util.Stack;
import java.util.UUID;

public class Task {
    private UUID id;
    private String title;
    private TaskStatus status;
    private double estimatedTime;
    private Stack<Entry> entries;
    private Tag tag;

    public Task(String title, double estimatedTime) {
        id = UUID.randomUUID();
        status = TaskStatus.TO_DO;
        this.estimatedTime = estimatedTime;
        this.title = title;
    }

    public Task(UUID id, int status, String title, double estimatedTime) {
        id = id;
        this.status = TaskStatus.TO_DO;
        this.estimatedTime = estimatedTime;
        this.title = title;
    }

    public UUID getId() { return id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public double getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(double estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void addEntry() {
        Entry entry = new Entry(true);

        if (!entries.isEmpty()) {
            Entry lastEntry = entries.peek();
            entry = new Entry(!lastEntry.isStart());
        }

        entries.push(entry);
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public double getElapsedTime() {
        return 0.0;
    }
}

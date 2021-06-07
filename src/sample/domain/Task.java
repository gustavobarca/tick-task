package sample.domain;

import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class Task {
    private int id;
    private String title;
    private TaskStatus status;
    private String estimatedTime;
    private Stack<Entry> entries;

    public Task(String title, int hours, int minutes, int seconds) {
        status = TaskStatus.TO_DO;
        this.setEstimatedTime(hours, minutes, seconds);
        this.entries = new Stack<Entry>();
        this.title = title;
    }

    public Task(int status, String title, int hours, int minutes, int seconds) {
        this.status = TaskStatus.TO_DO;
        this.setEstimatedTime(hours, minutes, seconds);
        this.entries = new Stack<Entry>();
        this.title = title;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(int hours, int minutes, int seconds) {
        this.estimatedTime = String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    }

    public void addEntry() {
        Entry entry = new Entry(true);

        if (!entries.isEmpty()) {
            Entry lastEntry = entries.peek();
            entry = new Entry(!lastEntry.isStart());
        }

        if (entry.isStart()) {
            this.status = TaskStatus.DOING;
        } else {
            this.status = TaskStatus.TO_DO;
        }

        entries.push(entry);
    }

    public void setEntries(Stack<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public String getElapsedTime() {
        long totalHours = 0;
        long totalMinutes = 0;
        long totalSeconds = 0;

        for (int i = 0; i < this.entries.size(); i++) {
            Entry currentEntry = this.entries.get(i);

            if (i == this.entries.size() - 1) break;

            Entry nextEntry = this.entries.get(i + 1);

            if (currentEntry.isStart() && (nextEntry != null && !nextEntry.isStart())) {
                long diff = nextEntry.getDatetime().getTime() - currentEntry.getDatetime().getTime();
                totalHours += diff / (60 * 60 * 1000);
                totalMinutes += (diff / (60 * 1000) % 60);
                totalSeconds += diff / 1000 % 60;
            }
        }

        return String.format("%02d", totalHours) + ":" + String.format("%02d", totalMinutes) + ":" + String.format("%02d", totalSeconds);
    }

    public void finish() {
        this.status = TaskStatus.DONE;
    }
}

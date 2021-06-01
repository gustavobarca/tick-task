package sample.domain.task;

import java.util.Date;
import java.util.UUID;

public class Entry {
    private UUID id;
    private Date datetime;
    private boolean isStart;

    public Entry(boolean isStart) {
        this.isStart = isStart;
        this.id = UUID.randomUUID();
        this.datetime = new Date();
    }

    public boolean isStart() {
        return isStart;
    }

    public Date getDatetime() {
        return datetime;
    }
}

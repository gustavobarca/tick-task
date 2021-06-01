package sample.domain;

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

    public Entry(UUID id, boolean isStart, Date datetime) {
        this.id = UUID.randomUUID();
        this.isStart = isStart;
        this.datetime = datetime;
    }

    public boolean isStart() {
        return isStart;
    }

    public Date getDatetime() {
        return datetime;
    }
}

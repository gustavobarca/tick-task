package sample.domain.tag;

import java.util.UUID;

public class Tag {
    private UUID id;
    private String name;

    public Tag(String name) {
        id = UUID.randomUUID();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

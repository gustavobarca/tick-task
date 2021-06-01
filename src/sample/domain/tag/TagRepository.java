package sample.domain.tag;

import java.util.UUID;

public interface TagRepository {
    Tag find(UUID id);

    void store(Tag task);
}

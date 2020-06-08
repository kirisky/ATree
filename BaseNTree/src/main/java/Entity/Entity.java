package Entity;

import java.util.HashSet;
import java.util.Map;

public class Entity extends AbstractEntity {

    public Entity(String id, Map<String, String> data) {
        super(id, new HashSet<>(), data);
    }

}

package Entity;

import java.util.Map;
import java.util.Set;

public abstract class AbstractEntity implements IEntity {
    private final String id;
    private final Set<IEntity> subEntities;
    private final Map<String, String> data;

    public AbstractEntity(String id, Set<IEntity> entities, Map<String, String> data) {
        this.id = id;
        this.subEntities = entities;
        this.data = data;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Set<IEntity> getSubEntities() {
        return subEntities;
    }

    @Override
    public Map<String, String> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id='" + id + '\'' +
                ", subEntities=" + subEntities +
                ", data=" + data +
                '}';
    }
}

package Entity;

import java.util.Map;
import java.util.Set;

public interface IEntity {
    // Returns a unique identifier
    String getId();

    // Returns the sub-entities of the entity
    Set<IEntity> getSubEntities();

    // Returns a set of key-value data belonging to this entity
    Map<String,String> getData();
}

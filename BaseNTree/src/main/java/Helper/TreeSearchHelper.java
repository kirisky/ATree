package Helper;

import Entity.IEntity;

import java.util.Set;

public class TreeSearchHelper {

    public static IEntity findEntity(String id, Set<IEntity> entities) {
        if (entities.isEmpty()) {
            return null;
        }

        for (IEntity entity : entities) {
            if (entity.getId().equals(id)) {
                return entity;
            }

            var targetEntity = findEntity(id, entity.getSubEntities());
            if (targetEntity != null) {
                return targetEntity;
            }
        }

        return null;
    }
}

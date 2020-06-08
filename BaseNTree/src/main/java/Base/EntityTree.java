package Base;

import Entity.Entity;
import Entity.IEntity;
import Helper.TreeSearchHelper;

import java.util.Map;
import java.util.UUID;

public class EntityTree {
    private IEntity root = null;

    public IEntity readEntity(String id) {
        if (isEmptyRoot()) {
            return null;
        }

        if (isRootId(id)) {
            return root;
        }

        return findEntityInSubEntities(id);
    }

    public String listEntries() {
        if (isEmptyRoot()) {
            return null;
        }

        return root.toString();
    }


    public boolean addEntity(String parentId, Map<String, String> data) {
        var entity = createNewEntity(data);

        if (isEmptyRoot()) {
            root = entity;
            return true;
        }

        if (isRootId(parentId)) {
            root.getSubEntities().add(entity);
            return true;
        }

        var targetEntity = TreeSearchHelper.findEntity(parentId, root.getSubEntities());

        if (targetEntity == null) {
            return false;
        }

        targetEntity.getSubEntities().add(entity);
        return true;
    }

    private boolean isEmptyRoot() {
        return root == null;
    }

    private boolean isRootId(String id) {
        return root.getId().equals(id);
    }

    private IEntity findEntityInSubEntities(String id) {
        return TreeSearchHelper.findEntity(id, root.getSubEntities());
    }

    private IEntity createNewEntity(Map<String, String> data) {
        return new Entity(UUID.randomUUID().toString(), data);
    }
}

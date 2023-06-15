package cn.edu.whut.soar.db;

import cn.edu.whut.soar.entity.ItemEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ItemStore {
    static int nextId = 1;

    static HashMap<Integer, ItemEntity> itemMap = new HashMap<>();

    public ItemEntity getItem(int id) {
        return itemMap.get(id);
    }

    public List<ItemEntity> getItemsByOwner(String ownerType, int ownerId) {
        List<ItemEntity> result = new ArrayList<>();

        for (ItemEntity item : itemMap.values()) {
            if (item.getOwnerType().equals(ownerType) && item.getOwnerId() == ownerId) {
                result.add(item);
            }
        }

        return result;
    }

    public void addItem(ItemEntity item) {
        if (item.getId() == 0) {
            item.setId(nextId++);
        }

        itemMap.put(item.getId(), item);
    }
}

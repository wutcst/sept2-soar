package cn.edu.whut.soar.service;

import cn.edu.whut.soar.store.ItemStore;
import cn.edu.whut.soar.entity.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemStore itemStore;
    /**
     * 得到物品的信息
     * @param itemId 物品的id
     * @return 物品的信息
     */
    public ItemEntity getItemInfo(int itemId) {
        return itemStore.getItem(itemId);
    }
}

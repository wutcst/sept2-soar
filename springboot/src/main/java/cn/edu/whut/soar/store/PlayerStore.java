package cn.edu.whut.soar.store;

import cn.edu.whut.soar.entity.PlayerEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerStore {
    static double defaultMaxCarryWeight = 100;

    static PlayerEntity playerEntity = new PlayerEntity(1, "小小英雄", defaultMaxCarryWeight, 1);

    /**
     * 得到玩家的实体
     * @return 玩家的实体
     */
    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }
}

package cn.edu.whut.soar.db;

import cn.edu.whut.soar.entity.PlayerEntity;
import org.springframework.stereotype.Repository;

@Repository
public class PlayerStore {
    static double defaultMaxCarryWeight = 100;

    static PlayerEntity playerEntity = new PlayerEntity(1, "小小英雄", defaultMaxCarryWeight, 1);

    // 现在仅支持一个用户.
    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }
}

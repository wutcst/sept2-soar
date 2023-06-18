package cn.edu.whut.soar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 *设计玩家类
 */
@Data
@AllArgsConstructor
public class PlayerEntity {
    private int id;

    private String name;

    private double maxCarryWeight;

    private int currentRoomId;
}

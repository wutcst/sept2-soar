package cn.edu.whut.soar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerEntity {
    private int id;

    private String name;

    private double maxCarryWeight;

    private int currentRoomId;
}

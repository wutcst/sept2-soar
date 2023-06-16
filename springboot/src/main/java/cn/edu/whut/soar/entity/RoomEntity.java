package cn.edu.whut.soar.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class RoomEntity {
    public RoomEntity(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.exitRoomIdMap = new HashMap<>();
    }

    private int id;

    private String name;

    private String description;

    private HashMap<String, Integer> exitRoomIdMap;
}

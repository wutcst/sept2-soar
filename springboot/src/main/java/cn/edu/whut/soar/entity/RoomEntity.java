package cn.edu.whut.soar.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class RoomEntity {
    private int id;

    private String name;

    private String description;

    private boolean[] exits;

    private ArrayList<ItemEntity> items;
}

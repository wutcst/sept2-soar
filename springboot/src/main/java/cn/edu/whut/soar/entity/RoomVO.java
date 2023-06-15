package cn.edu.whut.soar.entity;

import cn.edu.whut.soar.service.Item;
import lombok.Data;

import java.util.ArrayList;


@Data
public class RoomVO {
    String name;
    String description;
    boolean[] exits;
    ArrayList<Item> items;
}

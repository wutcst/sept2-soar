package cn.edu.whut.soar.entity;

import cn.edu.whut.soar.service.Item;
import cn.edu.whut.soar.service.Room;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class RoomVO {
    String description;
    HashMap<String, Room> exits;
    List<Item> items;
    Boolean isTransfer;
}

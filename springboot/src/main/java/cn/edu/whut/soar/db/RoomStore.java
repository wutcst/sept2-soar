package cn.edu.whut.soar.db;

import cn.edu.whut.soar.entity.ItemEntity;
import cn.edu.whut.soar.entity.RoomEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RoomStore {
    static int nextId = 1;

    static HashMap<Integer, RoomEntity> roomMap = new HashMap<>();

    public RoomEntity getRoom(int id) {
        return roomMap.get(id);
    }

    public void addItem(RoomEntity room) {
        if (room.getId() == 0) {
            room.setId(nextId++);
        }

        roomMap.put(room.getId(), room);
    }
}

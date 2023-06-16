package cn.edu.whut.soar.db;

import cn.edu.whut.soar.entity.RoomEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class RoomStore {
    static private int nextId = 1;

    static private final HashMap<Integer, RoomEntity> roomMap = new HashMap<>();

    public RoomEntity getRoom(int id) {
        return roomMap.get(id);
    }

    public void addRoom(RoomEntity room) {
        if (room.getId() == 0) {
            room.setId(nextId++);
        }

        roomMap.put(room.getId(), room);
    }
}

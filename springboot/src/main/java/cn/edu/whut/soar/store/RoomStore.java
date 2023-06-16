package cn.edu.whut.soar.store;

import cn.edu.whut.soar.entity.RoomEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Random;

@Repository
public class RoomStore {
    static private int nextId = 1;

    static private final HashMap<Integer, RoomEntity> roomMap = new HashMap<>();

    static private final Random random = new Random();

    public int getNextId() {
        return nextId++;
    }

    public RoomEntity getRoom(int id) {
        return roomMap.get(id);
    }

    public int getRandomRoomId() {
        return random.nextInt(roomMap.size())+1;
    }

    public void addRoom(RoomEntity room) {
        if (room.getId() == 0) {
            room.setId(nextId++);
        }

        roomMap.put(room.getId(), room);
    }
}

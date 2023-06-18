package cn.edu.whut.soar.service;

import cn.edu.whut.soar.store.ItemStore;
import cn.edu.whut.soar.store.RoomStore;
import cn.edu.whut.soar.entity.ItemEntity;
import cn.edu.whut.soar.entity.RoomEntity;
import cn.edu.whut.soar.model.GetRoomInfoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private static final String ownerTypeRoom = "Room";

    private final RoomStore roomStore;

    private final ItemStore itemStore;
    /**
     * 生成房间和房间里的物品
     * @param roomStore 存储房间
     * @param itemStore 存储物品
     */
    public RoomService(RoomStore roomStore, ItemStore itemStore) {
        this.roomStore = roomStore;
        this.itemStore = itemStore;

        RoomEntity outside = new RoomEntity(roomStore.getNextId(), "outside", "outside the main entrance of the university");
        RoomEntity theater = new RoomEntity(roomStore.getNextId(), "theater", "in a lecture theater");
        RoomEntity pub = new RoomEntity(roomStore.getNextId(), "pub", "in the campus pub");
        RoomEntity lab = new RoomEntity(roomStore.getNextId(), "lab", "in a computing lab");
        RoomEntity office = new RoomEntity(roomStore.getNextId(), "office", "in the computing admin office");
        RoomEntity library = new RoomEntity(roomStore.getNextId(), "library", "in the school's library");

        outside.getExitRoomIdMap().put("east", theater.getId());
        outside.getExitRoomIdMap().put("south", lab.getId());
        outside.getExitRoomIdMap().put("west", pub.getId());
        outside.getExitRoomIdMap().put("north", 0);

        theater.getExitRoomIdMap().put("west", outside.getId());
        theater.getExitRoomIdMap().put("south", office.getId());

        pub.getExitRoomIdMap().put("east", outside.getId());
        pub.getExitRoomIdMap().put("north", library.getId());

        lab.getExitRoomIdMap().put("north", outside.getId());
        lab.getExitRoomIdMap().put("east", office.getId());

        office.getExitRoomIdMap().put("west", lab.getId());
        office.getExitRoomIdMap().put("north", theater.getId());

        library.getExitRoomIdMap().put("south", pub.getId());
        library.getExitRoomIdMap().put("east", 0);

        roomStore.addRoom(outside);
        roomStore.addRoom(theater);
        roomStore.addRoom(pub);
        roomStore.addRoom(lab);
        roomStore.addRoom(office);
        roomStore.addRoom(library);

        itemStore.addItem(new ItemEntity(0, "apple", 10, ownerTypeRoom, outside.getId()));
        itemStore.addItem(new ItemEntity(0, "dancer", 100, ownerTypeRoom, outside.getId()));
        itemStore.addItem(new ItemEntity(0, "beer", 20, ownerTypeRoom, pub.getId()));
        itemStore.addItem(new ItemEntity(0, "computer", 20, ownerTypeRoom, office.getId()));
        itemStore.addItem(new ItemEntity(0, "library", 20, ownerTypeRoom, library.getId()));
    }

    /**
     * 得到房间的信息
     * @param roomId 房间的id
     * @return 房间的信息
     */
    public GetRoomInfoResponse getRoomInfo(int roomId) {
        RoomEntity room = roomStore.getRoom(roomId);

        return new GetRoomInfoResponse(
                room.getName(),
                room.getDescription(),
                room.getExitRoomIdMap()
        );
    }

    /**
     * 得到房间里的物品
     * @param roomId 房间的id
     * @return 房间里物品的信息
     */
    public List<ItemEntity> getItemsInRoom(int roomId) {
        return itemStore.getItemsByOwner(ownerTypeRoom, roomId);
    }
}

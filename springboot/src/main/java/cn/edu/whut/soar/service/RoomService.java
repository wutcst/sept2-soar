package cn.edu.whut.soar.service;

import cn.edu.whut.soar.store.ItemStore;
import cn.edu.whut.soar.store.RoomStore;
import cn.edu.whut.soar.entity.ItemEntity;
import cn.edu.whut.soar.entity.RoomEntity;
import cn.edu.whut.soar.model.GetRoomInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private static final String ownerTypeRoom = "Room";

    @Autowired
    private RoomStore roomStore;

    @Autowired
    private ItemStore itemStore;

    public RoomService() {
        RoomEntity outside = new RoomEntity(0, "outside", "outside the main entrance of the university");
        RoomEntity theater = new RoomEntity(0, "theater", "in a lecture theater");
        RoomEntity pub = new RoomEntity(0, "pub", "in the campus pub");
        RoomEntity lab = new RoomEntity(0, "lab", "in a computing lab");


        Room lab = new Room("", "");
        Room office = new Room("office", "in the computing admin office");
        Room library = new Room("library", "in the school's library");
        Room tpRoom = new Room("tpRoom", "You are about to be randomly teleported!");

        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", tpRoom);

        theater.setExit("west", outside);
        theater.setExit("south", office);

        pub.setExit("east", outside);
        pub.setExit("north", library);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("north", theater);

        library.setExit("south", pub);
        library.setExit("east", tpRoom);

        tpRoom.setExit("0", outside);
        tpRoom.setExit("1", theater);
        tpRoom.setExit("2", pub);
        tpRoom.setExit("3", lab);
        tpRoom.setExit("4", office);
        tpRoom.setExit("5", library);

        ItemEntity apple = new ItemEntity(0, "apple", 1, ownerTypeRoom, outsid)

        outside.setItem();

        theater.setItem(new ItemEntity("dancer_theater", 2));

        pub.setItem(new ItemEntity("beer_pub", 3));

        lab.setItem(new ItemEntity("lab_outside", 4));

        office.setItem(new ItemEntity("computer_office", 5));

        library.setItem(new ItemEntity("book_library", 4));
    }

    public GetRoomInfoResponse getRoomInfo(int roomId) {
        RoomEntity room = roomStore.getRoom(roomId);

        return new GetRoomInfoResponse(
                room.getName(),
                room.getDescription(),
                room.getExits(),
                room.getItems()
        );
    }

    public RoomEntity toVO(Room room) {
        RoomEntity vo = new RoomEntity();
        vo.setName(room.getName());
        vo.setDescription(room.getDescription());
        boolean[] direction = new boolean[]{false, false, false, false};
        if (room.getExit("north") != null) direction[0] = true;
        if (room.getExit("east") != null) direction[1] = true;
        if (room.getExit("south") != null) direction[2] = true;
        if (room.getExit("west") != null) direction[3] = true;
        vo.setExits(direction);
        vo.setItems(room.getItems());
        return vo;
    }
}

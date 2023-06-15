package cn.edu.whut.soar.service;

import cn.edu.whut.soar.db.RoomStore;
import cn.edu.whut.soar.entity.RoomEntity;
import cn.edu.whut.soar.model.GetRoomInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomStore roomStore;

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

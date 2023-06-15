package cn.edu.whut.soar.service;

import cn.edu.whut.soar.entity.Room;
import cn.edu.whut.soar.entity.RoomVO;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    public RoomVO toVO(Room room){
        RoomVO vo = new RoomVO();
        vo.setName(room.getName());
        vo.setDescription(room.getDescription());
        boolean[] direction = new boolean[]{false,false,false,false};
        if (room.getExit("east")!=null) direction[0] = true;
        if (room.getExit("south")!=null) direction[1] = true;
        if (room.getExit("west")!=null) direction[2] = true;
        if (room.getExit("north")!=null) direction[3] = true;
        vo.setExits(direction);
        vo.setItems(room.getItems());
        return vo;
    }
}

package cn.edu.whut.soar.service;

import cn.edu.whut.soar.entity.RoomVO;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    public RoomVO toJson(Room room){
        RoomVO vo = new RoomVO();
        vo.setDescription(room.getLongDescription());
        vo.setExits(room.getExits());
        vo.setItems(room.getItems());
        vo.setIsTransfer(room.getLongDescription().equals("tpRoom"));
        return vo;
    }
}

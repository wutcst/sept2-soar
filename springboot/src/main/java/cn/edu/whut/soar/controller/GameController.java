package cn.edu.whut.soar.controller;


import cn.edu.whut.soar.entity.RoomVO;
import cn.edu.whut.soar.service.Game;

import cn.edu.whut.soar.service.Room;
import cn.edu.whut.soar.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GameController {
    RoomService roomService ;
    @RequestMapping ("/createGame")
    @ResponseBody
    public RoomVO CreateGame(){
        Game game = new Game();
        Room startRoom =  game.getCurrentRoom();
        return roomService.toJson(startRoom);
    }

}

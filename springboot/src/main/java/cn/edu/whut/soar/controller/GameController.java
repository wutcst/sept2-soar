package cn.edu.whut.soar.controller;


import cn.edu.whut.soar.entity.RoomVO;
import cn.edu.whut.soar.service.Game;

import cn.edu.whut.soar.entity.Room;
import cn.edu.whut.soar.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Stack;


@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class GameController {

    Game game = new Game();
    @Autowired
    RoomService roomService ;



    @RequestMapping ("/createGame")
    @ResponseBody
    public RoomVO CreateGame(){
        Room startRoom =  game.getCurrentRoom();
        return roomService.toJson(startRoom);
    }



    @RequestMapping("/go")
    @ResponseBody
    public RoomVO Go(@RequestParam("direction") String direction){
        Room currentRoom = game.getCurrentRoom();
        Stack path = game.getPath();
        path.push(currentRoom);
        Room nextRoom = currentRoom.getExit(direction);
        return roomService.toJson(nextRoom);
    }


}

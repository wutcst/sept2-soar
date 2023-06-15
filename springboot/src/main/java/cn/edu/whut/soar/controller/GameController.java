package cn.edu.whut.soar.controller;


import cn.edu.whut.soar.entity.RoomEntity;
import cn.edu.whut.soar.service.Game;

import cn.edu.whut.soar.service.Room;
import cn.edu.whut.soar.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.Stack;


@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class GameController {
    Game game = new Game();

    @Autowired
    RoomService roomService;

    //请求开始
    @RequestMapping("/createGame")
    @ResponseBody
    public RoomEntity CreateGame() {
        return roomService.toVO(game.getCurrentRoom());
    }

    @RequestMapping("/go")
    @ResponseBody
    public RoomEntity Go(@RequestParam("direction") String direction) {
        Room currentRoom = game.getCurrentRoom();
        Stack path = game.getPath();
        path.push(currentRoom);
        Room nextRoom = currentRoom.getExit(direction);
        game.setCurrentRoom(nextRoom);
        return roomService.toVO(nextRoom);
    }

    @RequestMapping("/back")
    @ResponseBody
    public RoomEntity Back() {
        Stack path = game.getPath();
        if (!path.empty()) {
            Room backRoom = (Room) game.getPath().pop();
            game.setCurrentRoom(backRoom);
        }
        return roomService.toVO(game.getCurrentRoom());
    }

    @RequestMapping("/transfer")
    @ResponseBody
    public RoomEntity TP() {
        Stack path = game.getPath();
        path.clear();
        Random random = new Random();
        //随机房间的出口有5个
        game.setCurrentRoom(game.getCurrentRoom().getExit(String.valueOf(random.nextInt(6))));
        return roomService.toVO(game.getCurrentRoom());
    }


}

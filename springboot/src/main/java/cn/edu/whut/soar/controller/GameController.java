package cn.edu.whut.soar.controller;


import cn.edu.whut.soar.entity.ItemEntity;
import cn.edu.whut.soar.model.GetPlayerInfoResponse;
import cn.edu.whut.soar.model.GetRoomInfoResponse;
import cn.edu.whut.soar.model.StatusResponse;

import cn.edu.whut.soar.service.PlayerService;
import cn.edu.whut.soar.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class GameController {
    @Autowired
    RoomService roomService;

    @Autowired
    PlayerService playerService;

    @GetMapping("/player")
    @ResponseBody
    public GetPlayerInfoResponse getPlayerInfo(@RequestParam("roomId") int roomId) {
        return playerService.getPlayerInfo();
    }

    @GetMapping("/room")
    @ResponseBody
    public GetRoomInfoResponse getRoomInfo(@RequestParam("roomId") int roomId) {
        return roomService.getRoomInfo(roomId);
    }

    @GetMapping("/room/{id}/items")
    @ResponseBody
    public List<ItemEntity> getItemsInRoom(@PathVariable("id") int id) {
        return roomService.getItemsInRoom(id);
    }

    @GetMapping("/player/items")
    @ResponseBody
    public List<ItemEntity> getItemsOfPlayer() {
        return playerService.getItems();
    }

    @PostMapping("/move")
    @ResponseBody
    public StatusResponse move(@RequestParam("direction") String direction) {
        return playerService.move(direction);
    }

    @RequestMapping("/back")
    @ResponseBody
    public StatusResponse back() {
        return playerService.back();
    }
}

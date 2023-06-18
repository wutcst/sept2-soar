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

/**
 *该类用于响应前后端请求
 */
@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class GameController {
    @Autowired
    RoomService roomService;

    @Autowired
    PlayerService playerService;

    @GetMapping("/player")
    @ResponseBody
    public GetPlayerInfoResponse getPlayerInfo() {
        return playerService.getPlayerInfo();
    }

    @GetMapping("/room/{id}")
    @ResponseBody
    public GetRoomInfoResponse getRoomInfo(@PathVariable("id") int id) {
        return roomService.getRoomInfo(id);
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

    @GetMapping("/move/{direction}")
    @ResponseBody
    public StatusResponse move(@PathVariable("direction") String direction) {
        return playerService.move(direction);
    }

    @RequestMapping("/back")
    @ResponseBody
    public StatusResponse back() {
        return playerService.back();
    }

    @RequestMapping("/take/{id}")
    @ResponseBody
    public StatusResponse take(@PathVariable("id") int id){
        return playerService.takeItem(id);
    }

    @RequestMapping("/drop/{id}")
    @ResponseBody
    public StatusResponse drop(@PathVariable("id") int id){
        return playerService.dropItem(id);
    }

}

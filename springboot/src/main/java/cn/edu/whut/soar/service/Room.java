package cn.edu.whut.soar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashMap;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.

    private ArrayList<Item> items;

    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * 设置房间内的物品
     * @param item 房间内的物品
     */
    public void setItem(Item item) {
        items.add(item);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * 显示房间内的物品
     * @return 返回所有物品的描述和重量
     */
    private String getItemsString() {
        String returnString="Items:";
        for(Item item:items) {
            returnString += " "+item.getDescription()+"-"+item.getWeight();
        }
        return returnString;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public HashMap<String, Room> getExits(){return exits;}

    public List<Item> getItems(){return items;}

    /**
     * 获取所有的物品信息
     * @return 描述物品的字符串
     */
    public String getItem() {
        return getItemsString();
    }
}



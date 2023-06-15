package cn.edu.whut.soar.service;

import java.util.ArrayList;
import java.util.HashMap;
//设置房间类
public class Room
{
    private String name;
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.

    private ArrayList<Item> items;

    public Room(String name,String description)
    {
        this.name = name;
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

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public HashMap<String, Room> getExits(){return exits;}

    public ArrayList<Item> getItems(){return items;}

}



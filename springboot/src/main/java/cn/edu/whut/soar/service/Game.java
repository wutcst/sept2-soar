/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 */
package cn.edu.whut.soar.service;

import cn.edu.whut.soar.entity.ItemEntity;

import java.util.Stack;

public class Game
{
    private Room currentRoom;

    private Stack path = new Stack<Room>(); //back的路径

    public Game()
    {
        createRooms();
    }

    private void createRooms()
    {
        Room outside, theater, pub, lab, office,library,tpRoom;

        // create the rooms
        outside = new Room("outside","outside the main entrance of the university");
        theater = new Room("theater","in a lecture theater");
        pub = new Room("pub","in the campus pub");
        lab = new Room("lab","in a computing lab");
        office = new Room("office","in the computing admin office");
        library =new Room("library","in the school's library");
        tpRoom = new Room("tpRoom","You are about to be randomly teleported!");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", tpRoom);

        theater.setExit("west", outside);
        theater.setExit("south", office);

        pub.setExit("east", outside);
        pub.setExit("north", library);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);
        office.setExit("north", theater);

        library.setExit("south",pub);
        library.setExit("east",tpRoom);

        tpRoom.setExit("0",outside);
        tpRoom.setExit("1",theater);
        tpRoom.setExit("2",pub);
        tpRoom.setExit("3",lab);
        tpRoom.setExit("4",office);
        tpRoom.setExit("5",library);

        //初始化房间物品
        outside.setItem(new ItemEntity("apple_outside",1));

        theater.setItem(new ItemEntity("dancer_theater",2));

        pub.setItem(new ItemEntity("beer_pub",3));

        lab.setItem(new ItemEntity("lab_outside",4));

        office.setItem(new ItemEntity("computer_office",5));

        library.setItem(new ItemEntity("book_library",4));


        currentRoom = outside;  // start game outside
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    public Stack getPath() { return path; }
}

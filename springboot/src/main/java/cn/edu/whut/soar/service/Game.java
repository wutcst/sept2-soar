/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.soar.service;

import cn.edu.whut.soar.entity.Room;

import java.util.Stack;

public class Game
{
    private Parser parser;
    private Room currentRoom;

    private Stack path = new Stack<Room>(); //back的路径

    public Game()
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        Room outside, theater, pub, lab, office, tpRoom;

        // create the rooms
        outside = new Room("outside","outside the main entrance of the university");
        theater = new Room("theater","in a lecture theater");
        pub = new Room("pub","in the campus pub");
        lab = new Room("lab","in a computing lab");
        office = new Room("office","in the computing admin office");
        tpRoom = new Room("tpRoom","你即将被随机传送！");

        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("north", tpRoom);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        tpRoom.setExit("0",outside);
        tpRoom.setExit("1",theater);
        tpRoom.setExit("2",pub);
        tpRoom.setExit("3",lab);
        tpRoom.setExit("4",office);

        //初始化房间物品
        outside.setItem(new Item("item_outside",1));

        theater.setItem(new Item("item_theater",2));

        pub.setItem(new Item("item_pub",3));

        lab.setItem(new Item("lab_outside",4));

        office.setItem(new Item("item_office",5));


        currentRoom = outside;  // start game outside
    }

    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            if(command == null) {
                System.out.println("I don't understand...");
            } else {
                finished = command.execute(this);
            }
        }

        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getDescription());
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room){
        this.currentRoom = room;
    }

    public Stack getPath() { return path; }
}

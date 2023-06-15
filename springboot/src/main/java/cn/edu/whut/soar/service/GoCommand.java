package cn.edu.whut.soar.service;

import cn.edu.whut.soar.entity.Room;

import java.util.Random;
import java.util.Stack;

public class GoCommand extends Command
{
    public boolean execute(Game game)
    {
        if(!hasSecondWord()) {
            System.out.println("Go where?");
        }

        String direction = getSecondWord();
        Room currentRoom = game.getCurrentRoom();

        Room nextRoom = game.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            Stack path = game.getPath();
            path.push(currentRoom);

            if (nextRoom.getName().equals("tpRoom")){
                System.out.println(nextRoom.getName());
                path.clear();
                Random random = new Random();
                game.setCurrentRoom(nextRoom.getExit(String.valueOf(random.nextInt(5))));
                System.out.println("你已到达新的房间，使用look命令看看你在哪吧！");
            }else {
                game.setCurrentRoom(nextRoom);
                System.out.println(nextRoom.getDescription());
            }
        }

        return false;
    }


}

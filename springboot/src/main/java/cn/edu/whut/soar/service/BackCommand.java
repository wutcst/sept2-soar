package cn.edu.whut.soar.service;

import java.util.Stack;

public class BackCommand extends Command{

    @Override
    public boolean execute(Game game) {
        Stack path = game.getPath();
        if (path.empty()){
            System.out.println("已回到起点或被传送，无法返回！");
        }
        else {
            Room backRoom = (Room) game.getPath().pop();
            game.setCurrentRoom(backRoom);
            System.out.println("你已经回到"+backRoom.getShortDescription());
        }

        return false;
    }
}

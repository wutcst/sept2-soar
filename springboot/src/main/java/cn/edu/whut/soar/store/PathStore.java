package cn.edu.whut.soar.store;

import org.springframework.stereotype.Repository;

import java.util.Stack;

@Repository
public class PathStore {

    private Stack<Integer> path = new Stack<Integer>();

    public void addPath(int roomId){
        path.add(roomId);
    }

    public int drawPath(){
        return path.pop();
    }

    public boolean isPathEmpty(){
        return path.isEmpty();
    }
}

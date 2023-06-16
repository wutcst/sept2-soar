package cn.edu.whut.soar.model;

import cn.edu.whut.soar.entity.ItemEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@AllArgsConstructor
public class GetRoomInfoResponse {
    private String name;

    private String description;

    private HashMap<String, Integer> exitRoomIdMap;
}

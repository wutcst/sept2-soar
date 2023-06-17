package cn.edu.whut.soar.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class GetRoomInfoResponse {
    private String name;

    private String description;

    private HashMap<String, Integer> exitRoomIdMap;
}

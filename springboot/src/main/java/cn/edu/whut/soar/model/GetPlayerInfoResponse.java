package cn.edu.whut.soar.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetPlayerInfoResponse {
    private String name;
    private double maxCarryWeight;
    private int currentRoomId;
}

package cn.edu.whut.soar.model;

import lombok.AllArgsConstructor;
import lombok.Data;
/**
 * 该类用于整理返回的响应信息
 */
@Data
@AllArgsConstructor
public class StatusResponse {
    private String status;
}

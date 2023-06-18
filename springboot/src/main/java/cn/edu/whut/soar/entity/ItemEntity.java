package cn.edu.whut.soar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ItemEntity {
    private int id;

    private String description;

    private double weight;

    private String ownerType;

    private int ownerId;
}

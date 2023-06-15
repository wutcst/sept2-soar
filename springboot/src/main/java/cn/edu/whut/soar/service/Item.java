/**
 * 房间内的物品
 */
package cn.edu.whut.soar.service;

public class Item {
    private String description;
    private double weight;

    /**
     * 物品初始化
     */
    public Item(String description,double weight){
        this.description = description;
        this.weight = weight;
    }
    /**
     * @return 物品描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return 物品重量
     */
    public double getWeight() {
        return weight;
    }
}

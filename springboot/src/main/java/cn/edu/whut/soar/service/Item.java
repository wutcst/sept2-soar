/**
 * 房间内的物品
 */
package cn.edu.whut.soar.service;

public class Item {
    private String description;
    private double weight;

    /**
     * 对于物品的初始化
     */
    public Item(String description,double weight){
        this.description = description;
        this.weight = weight;
        this.weight = weight;
    }
    /**
     * 对于@return物品进行描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 对于@return 物品重量
     */
    public double getWeight() {
        return weight;
    }
}

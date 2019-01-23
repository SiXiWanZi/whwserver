package com.whw.model;

import java.util.List;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2018/12/06
 *     desc   : 保存路径及权值
 *     version: 1.0
 * </pre>
 */
public class NavPath {
    // 路径上的各个节点对应的数组下标（从起点到终点）
    // 每条路径上，有很多个节点
    public List<Node> nodeList;
    // 路径总权值（路径长度和）
    public double weight;
    // 路径上节点个数：通过path.size()得到

    // 路径偏好
    public double pref;
    // 路径成本
    public double cost;
    // 路径可靠性
    public double reliable;


    public NavPath() {
    }

    public NavPath(List<Node> nodeList, double weight) {
        this.nodeList = nodeList;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "NavPath{" +
                "nodeList=" + nodeList +
                ", weight=" + weight +
                '}';
    }

}

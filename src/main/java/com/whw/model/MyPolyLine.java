package com.whw.model;

import java.util.List;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2019/01/16
 *     desc   : 和数据库表中的折线表结构不太一样的一个实体
 *     version: 1.0
 * </pre>
 */
public class MyPolyLine {
    private String lineId;

    /**
     * 构成折线的各个节点points
     */
    private List<Node> points;

    @Override
    public String toString() {
        return "MyPolyLine{" +
                "lineId='" + lineId + '\'' +
                ", points=" + points +
                '}';
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public List<Node> getPoints() {
        return points;
    }

    public void setPoints(List<Node> points) {
        this.points = points;
    }
}

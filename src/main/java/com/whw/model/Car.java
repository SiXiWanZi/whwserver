package com.whw.model;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2019/01/08
 *     desc   : 车辆相关属性
 *     version: 1.0
 * </pre>
 */
public class Car implements Serializable,Comparable<Car>{
    // 车牌号
    public String carId;
    // 起点Id
    public String startNodeId;
    // 终点Id
    public String endNodeId;
    // 作业类型：装货,1/卸货,0
    public int type;
    // 车载货物量
    public double loadCapacity;

    // 候选路径集
    public List<NavPath> cadidatePaths;

    // 试探性最佳路径在候选路径集中的下标
    public int beatPathIndex;

    // 试探性最佳路径对应的行驶时间
    public double sumTime;

    // 试探性最佳路径的效用
    public double utility;

    // 理想效用：通过候选路径集中时间最短的路径计算相应效用，并作为期望效用
    public double expectedUtility;

    // 严丽平算法中最佳响应概率中的u取值
    public double u=1;


    // 满足效用
    public double satisfyUtility;

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", startNodeId='" + startNodeId + '\'' +
                ", endNodeId='" + endNodeId + '\'' +
                ", type=" + type +
                ", loadCapacity=" + loadCapacity +
                ", beatPathIndex=" + beatPathIndex +
                ", sumTime=" + sumTime +
                ", utility=" + utility +
                ", expectedUtility=" + expectedUtility +
                ", u=" + u +
                ", satisfyUtility=" + satisfyUtility +
                '}';
    }

    public double getSatisfyUtility() {
        return satisfyUtility;
    }

    public void setSatisfyUtility(double satisfyUtility) {
        this.satisfyUtility = satisfyUtility;
    }

    public double getExpectedUtility() {
        return expectedUtility;
    }

    public void setExpectedUtility(double expectedUtility) {
        this.expectedUtility = expectedUtility;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public double getSumTime() {
        return sumTime;
    }

    public void setSumTime(double sumTime) {
        this.sumTime = sumTime;
    }

    public double getUtility() {
        return utility;
    }

    public void setUtility(double utility) {
        this.utility = utility;
    }

    public int getBeatPathIndex() {
        return beatPathIndex;
    }

    public void setBeatPathIndex(int beatPathIndex) {
        this.beatPathIndex = beatPathIndex;
    }

    public String getStartNodeId() {
        return startNodeId;
    }

    public void setStartNodeId(String startNodeId) {
        this.startNodeId = startNodeId;
    }

    public String getEndNodeId() {
        return endNodeId;
    }

    public void setEndNodeId(String endNodeId) {
        this.endNodeId = endNodeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public List<NavPath> getCadidatePaths() {
        return cadidatePaths;
    }

    public void setCadidatePaths(List<NavPath> cadidatePaths) {
        this.cadidatePaths = cadidatePaths;
    }



    @Override
    public int compareTo(Car o) {
        if (o == null) {
            return -1;
        }
        double a = utility - expectedUtility;// 当前效用-期望效用
        double b = o.utility - o.expectedUtility;
        if (a > b) {
            return 1;
        } else if (a < b) {
            return -1;
        }else{
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return carId.equals(car.carId);
    }

    @Override
    public int hashCode() {
        return carId.hashCode();
    }
}

package com.whw.model;

import java.util.List;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2019/01/08
 *     desc   : 保存一趟迭代的结果
 *     version: 1.0
 * </pre>
 */
public class NavigationResult {

    // 车辆的选路结果
    public List<Car> cars;
    // 此选路结果对应的系统收益
    public double Q;
    // 期望系统收益
    public double expectedQ;

    // 达到的车辆个数
    public int satisfactionNum;

    // 集群大小选取
    public int clusterSize;

    @Override
    public String toString() {
        return "NavigationResult{" +
                "cars=" + cars +
                ", Q=" + Q +
                ", expectedQ=" + expectedQ +
                ", satisfactionNum=" + satisfactionNum +
                ", clusterSize=" + clusterSize +
                '}';
    }

    public NavigationResult(List<Car> cars, double q, double expectedQ) {
        this.cars = cars;
        Q = q;
        this.expectedQ = expectedQ;
    }

    public NavigationResult() {
    }
    public NavigationResult(NavigationResult o) {
        this.cars = o.cars;
        Q = o.Q;
        this.expectedQ = o.expectedQ;
        this.satisfactionNum = o.satisfactionNum;
        this.clusterSize = o.clusterSize;
    }


}

package com.whw.model;

import java.util.List;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2019/01/08
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MyPath {


    // 路径上的各个节点对应的数组下标（从起点到终点）
    public List<Integer> path;
    // 路径总权值
    public double weight;
    // 路径上节点个数：通过path.size()得到


    public MyPath() {
    }

    public MyPath(List<Integer> path, double weight) {
        this.path = path;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "MyPath{" +
                "path=" + path +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyPath path1 = (MyPath) o;
        return path != null ? path.equals(path1.path) : path1.path == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = path != null ? path.hashCode() : 0;
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

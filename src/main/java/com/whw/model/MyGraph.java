package com.whw.model;

import java.util.Arrays;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2018/12/06
 *     desc   : 图
 *     version: 1.0
 * </pre>
 */
public class MyGraph {
    // 节点个数
    public int n;
    // 边个数
    public int e;
    // 结点集合
    public Node[] nodes;
    // 所有节点发出的所有边（按照特定的顺序排列起来）
    public int[] linkedEdges;
    // 边集合
    public Edge[] edges;

    public MyGraph(){}

    @Override
    public String toString() {
        return "MyGraph{" +
                "n=" + n +
                ", e=" + e +
                ", nodes=" + Arrays.toString(nodes) +
                ", linkedEdges=" + Arrays.toString(linkedEdges) +
                ", edges=" + Arrays.toString(edges) +
                '}';
    }

    public MyGraph(int n, int e){
        this.n=n;
        this.e=e;
        // 从数组下标0开始存储
        nodes=new Node[n];
        edges=new Edge[e];
        linkedEdges=new int[e];
    }
}

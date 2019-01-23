package com.whw.util;


import com.whw.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2018/11/15
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NavigationUtil {

    /**
     * 得到编号为vNum的节点在数组nodes中存储的下标（二分查找）
     *
     * @param g
     * @param vId：节点编号
     * @return
     */
    public static int getIndex(MyGraph g, String vId) {
        if (vId == null || "".equals(vId)) {
            return -1;
        }
        Node[] nodes = g.nodes;
        for (int i = 0; i < nodes.length; i++) {
            if (vId.equals(nodes[i].getNodeId())) {
                return i;
            }
        }

//        int low = 0;
//        int high = g.nodes.length - 1;
//        while (low <= high) {
//            int mid = (low + high) / 2;
//            if (g.nodes[mid].getNodeId() == vNum) {
//                return mid;
//            } else if (g.nodes[mid].getNodeId() < vNum) {
//                low = mid + 1;
//            } else {
//                high = mid - 1;
//            }
//        }
        return -1;
    }

    /**
     * 判断图g中从节点a到节点b是否连通
     *
     * @param g
     * @param aIndex:数组下标
     * @param bIndex：数组下标
     * @return
     */
    public static boolean isConnected(MyGraph g, int aIndex, int bIndex) {
        int begin = g.nodes[aIndex].getLinkEdgesBeginIndex();
        int count = g.nodes[aIndex].getCount();
        for (int i = begin; i < begin + count; i++) {
            // 遍历linkedEdges数组
            if (g.edges[i].getExitNode().getNodeId() == g.nodes[bIndex].getNodeId()) {
                // 连通
                return true;
            }
        }
        return false;
    }

    /**
     * 从图中得到节点a到b的路径权值
     *
     * @param g
     * @param aIndex：（边的起始节点）的数组下标
     * @param bIndex：（边的终止节点）的数组下标
     * @return
     */
    public static double getEdgeWight(MyGraph g, int aIndex, int bIndex) {
        int begin = g.nodes[aIndex].getLinkEdgesBeginIndex();
        int count = g.nodes[aIndex].getCount();
        for (int i = begin; i < begin + count; i++) {
            // 遍历由a发出的所有边
            if (g.edges[i].getExitNode().getNodeId().equals(g.nodes[bIndex].getNodeId())) {
                // 连通
//                return g.edges[i].getWeight();
                return g.edges[i].getLength();// 将路段长度作为权重
            }
        }
//        throw new Exception("从"+a+"到"+b+"不连通");
        return Double.MAX_VALUE;
    }

    /**
     * 得到以aIndex为起点（数组下标）、bIndex为终点（数组下标）的路段在edge[]数组中的下标
     *
     * @param g
     * @param aIndex
     * @param bIndex
     * @return
     */
    public static int getEdgeIndex(MyGraph g, int aIndex, int bIndex) {
        int begin = g.nodes[aIndex].getLinkEdgesBeginIndex();
        int count = g.nodes[aIndex].getCount();
        for (int i = begin; i < begin + count; i++) {
            // 遍历由a发出的所有边
            if (getIndex(g, g.edges[i].getExitNode().getNodeId()) == bIndex) {
                return i;
            }
        }
        return -1;
    }

    public static MyGraph createMyGraph() {
        // 节点数
        int N = 8;
        // 边数
//        int M = 16;
        int M = 14;
        MyGraph g = new MyGraph(N, M);
        // 节点信息
        String[] nodeInfo = {"10 C 0 0",
                "11 D 1 2",
                "12 E 2 3",
                "13 F 3 4",
                "14 G 4 4",
                "15 H 5 5",
                "16 I 2 1",
                "17 J 3 2"};
        for (int i = 0; i < N; i++) {
            Node node = new Node();
            String[] info = nodeInfo[i].split(" ");
            node.setNodeId(info[0]);
            node.setNodeName(info[1]);
            node.setLatitude(Double.parseDouble(info[2]));
            node.setLongtitude(Double.parseDouble(info[3]));
            g.nodes[i] = node;
        }
        // 边信息
        String[] edgeInfo = {"10 11 2",
                "11 10 2",
                "11 12 1",
                "11 16 1",
                "12 11 1",
                "12 13 1",
                "13 12 1",
                "13 14 0.5",
                "14 13 0.5",
                "14 17 2",
                "14 15 1",
                "15 14 1",
                "16 11 1",
                "16 17 1",
                "17 16 1",
                "17 14 2"};
        for (int i = 0; i < M; i++) {
            String[] info = edgeInfo[i].split(" ");
            String id1 = info[0];
            String id2 = info[1];
            double weight = Double.parseDouble(info[2]);
            Edge edge = new Edge();
            edge.setEnterNode(new Node(id1));
            edge.setExitNode(new Node(id2));
            edge.setLength(weight);
            g.edges[i] = edge;
        }
        // 边关联信息
        // 将节点按照节点编号升序排列，边集按照起始节点的编号升序排列
        Arrays.sort(g.edges);

        int k = 0;// linkedEdges[]中可用的最新位置
        int index = 0;// 上一节点搜索结束后在弧集中的索引
        for (int i = 0; i < N; i++) {
            // 对于每一个节点：从弧集中找出从该节点发出的弧
            Node node = g.nodes[i];
            int count = 0;
            int beginIndex = k;
            while (index < g.edges.length && g.edges[index].getEnterNode().getNodeId().equals(node.getNodeId())) {
                // 如果是该节点发出的弧
                count++;// 个数加1
//                g.linkedEdges[k++]=g.edges[index].edgeId;// 将该弧存起来
                g.linkedEdges[k++] = index;// 将该弧在数组中的下标存起来
                index++;// 判断下一个弧如何
            }
            // 所有的弧都判断完后
            if (count == 0) {
                // 该节点没有任何弧发出
                node.setCount(0);
                node.setLinkEdgesBeginIndex(-1);
            } else {
                node.setCount(count);
                node.setLinkEdgesBeginIndex(beginIndex);
            }
        }
        return g;

    }

    /**
     * 把Mypath转化为NavPath
     *
     * @param g
     * @param myPath
     * @return
     */
    public static NavPath myPathToNavPath(MyGraph g, MyPath myPath) {
        if (myPath == null || g == null) {
            return null;
        }
        NavPath result = new NavPath();
        result.weight = myPath.weight;
        List<Node> navPathNodes = new ArrayList<>();
        for (Integer nodeId : myPath.path) {
            navPathNodes.add(g.nodes[nodeId]);
        }
        result.nodeList = navPathNodes;
        return result;
    }

    /**
     * 根据BPR计算路段行驶时间
     * @param initialTime：自由流行驶时间
     * @param initalCarMembers :初始通行能力
     * @param capacity：当前路段容量
     * @return
     */
    public static double getEdgeRunTime(double initialTime, int initalCarMembers, int capacity) {
        if (initalCarMembers == 0) {
            return initialTime;
        }
        return initialTime * (1 + 0.15 * Math.pow(capacity / initalCarMembers, 4));
    }

    /**
     * 根据轮盘赌算法，返回被选中的某个染色体的数组下标
     *
     * @param selectionProbability
     * @return
     */
    public static int getSelectedOne_byRoulette(double[] selectionProbability) {
        Random random = new Random();
        double probTotal = random.nextDouble();
        double sum = 0.0;
        for (int i = 0; i < selectionProbability.length; i++) {
            sum += selectionProbability[i];
            if (sum >= probTotal) {
                return i;
            }
        }
        return 0;
    }
}

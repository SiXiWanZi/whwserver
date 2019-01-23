package com.whw.util;

import com.whw.model.MyGraph;
import com.whw.model.MyPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2019/01/07
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class DijkstraUtil {

    /**
     * 用Dijkstra算法得到从startIndex到endIndex的一条最短路径
     *
     * @param g
     * @param startIndex                               起始节点的数组下标
     * @param endIndex                                 终止节点的数组下标
     * @param unavailableNodeIndexs：求最短路径时不可用的节点（数组下标）
     * @param unavailableEdges：求最短路径时不可用的边
     * @return
     */
    public static MyPath getSingleShortestPath_dijkstra(MyGraph g, int startIndex, int endIndex,
                                                        List<Integer> unavailableNodeIndexs, List<MyPath> unavailableEdges) {
        if (startIndex == -1) {
//            throw new Exception("getSingleShortestPath_dijkstra()起始点编号输入错误");
        }
        if (endIndex == -1) {
//            throw new Exception("getSingleShortestPath_dijkstra()终止点编号输入错误");
        }
        int[] set = new int[g.n];// 是否已并入集合，该点是否已找到最短路径
        // s到i的最短路径长度
        double[] dist = new double[g.n];
        // s到i的最短路径上i的前一个节点编号
        int[] path = new int[g.n];

        // 初始化数组
        set[startIndex] = 1;
        for (int i = 0; i < g.n; i++) {
            if (i == startIndex) {// 源点
                dist[i] = 0;
                path[i] = -1;
            } else {
                if (NavigationUtil.isConnected(g, startIndex, i)) {
                    dist[i] = NavigationUtil.getEdgeWight(g, startIndex, i);
                    path[i] = startIndex;
                } else {
                    dist[i] = Double.MAX_VALUE;
                    path[i] = -1;
                }
            }
        }

        // 不能走的边
        if (unavailableEdges != null && unavailableEdges.size() != 0) {
            for (MyPath p : unavailableEdges) {
                int index = p.path.indexOf(startIndex);
                if (index >= 0 && (index + 1) >= 0) {
                    dist[p.path.get(index + 1)] = Double.MAX_VALUE;
                    path[p.path.get(index + 1)] = -1;
                }
            }
        }

        // 不能走的点
        if (unavailableNodeIndexs != null && unavailableNodeIndexs.size() != 0) {
            for (Integer point : unavailableNodeIndexs) {
                set[point] = 1;
            }
        }

        // 需进行n-2轮循环
        for (int i = 0; i < g.n - 2; i++) {
            int k = -1;
            double min = Double.MAX_VALUE;
            // 找出dist[]中最小的（太贪心了）
            for (int j = 0; j < g.n; j++) {
                if (set[j] == 1) {
                    continue;
                }
                if (dist[j] < min) {
                    min = dist[j];
                    k = j;
                }
            }
            if (k == -1) {
                // 说明从源点出发与其余节点不连通，无法再向下进行扩展
                break;
            }
            set[k] = 1;// 把节点k并入
            // 修改dist[]、path[]
            for (int j = 0; j < g.n; j++) {
                if (set[j] == 1) {
                    continue;
                }
                if (NavigationUtil.isConnected(g, k, j)) {
                    double temp = dist[k] + NavigationUtil.getEdgeWight(g, k, j);
                    if (temp < dist[j]) {
                        dist[j] = temp;
                        path[j] = k;
                    }
                }
            }
        }

        // 输出
        if (dist[endIndex] == Double.MAX_VALUE) {
            return null;
        } else {
            MyPath result = new MyPath();
            result.path = getMinimumPath(g, startIndex, endIndex, path);
            result.weight = dist[endIndex];
            return result;
        }
    }


    /**
     * 输出从节点S到节点T的最短路径
     *
     * @param sIndex：起始节点在数组中下标
     * @param tIndex：终止节点在数组中下标
     */
    private static List<Integer> getMinimumPath(MyGraph g, int sIndex, int tIndex, int[] path) {
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(tIndex);
        int i = path[tIndex];
        while (i != -1) {
            stack.push(i);
            i = path[i];
        }
        while (!stack.isEmpty()) {
            result.add(NavigationUtil.getIndex(g, g.nodes[stack.pop()].getNodeId()));
        }
        return result;
    }


}

package com.whw.util;

import com.whw.model.MyGraph;
import com.whw.model.MyPath;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2019/01/08
 *     desc   : Yen的KSP算法
 *     version: 1.0
 * </pre>
 */
public class YenKspUtil {

    public static final int K = 5;

    /**
     * 得到两点间的K条最短路径
     *
     * @param g
     * @param startNodeIndex
     * @param endNodeIndex
     * @return
     */
    public static List<MyPath> getKsp(MyGraph g, int startNodeIndex, int endNodeIndex) {
        // 结果列表
        List<MyPath> result = new ArrayList<>();
        // 候选路径列表
        Set<MyPath> candidatePaths = new HashSet<>();
        // 候选路径列表中权值最小的路径，及其对应的节点个数
        // 第一条最短路径
        MyPath p1 = DijkstraUtil.getSingleShortestPath_dijkstra(g, startNodeIndex, endNodeIndex,
                null, null);
        result.add(p1);
        int k = 1;
        List<Integer> pk = p1.path;
        while (k < K) {
            /*
            求第k+1条最短路径
             */
            // 遍历每一个偏离点
            for (int i = 0; i <= pk.size() - 2; i++) {
                // 1，pk路径中起点到偏离点Vi的路径权值
                double w1 = 0;
                for (int j = 0; j <= i - 1; j++) {
                    w1 += NavigationUtil.getEdgeWight(g, pk.get(j), pk.get(j + 1));
                }
                // 2,偏离点到终点的最短路径
                MyPath viToDestinationSP = DijkstraUtil.getSingleShortestPath_dijkstra(g,
                        pk.get(i), endNodeIndex, pk.subList(0, i), result);
                if (viToDestinationSP != null) {
                    // 说明从这个偏离点可以到达终点
                    MyPath temp = new MyPath();
                    List<Integer> tempPath = new ArrayList<>(pk.subList(0, i));
                    tempPath.addAll(viToDestinationSP.path);
                    temp.path = tempPath;
                    temp.weight = w1 + viToDestinationSP.weight;
                    // 加入候选列表
                    if (!candidatePaths.contains(temp)) {
                        candidatePaths.add(temp);
                    }
                }
            }
            if (candidatePaths == null || candidatePaths.size() == 0) {
                // 没有候选路径，则无需再继续向下求解
                break;
            } else {
                // 从候选路径中选出最合适的一条，移除并加入到结果列表
                MyPath fitPath = getFitPathFromCandidate(candidatePaths);
                candidatePaths.remove(fitPath);
                result.add(fitPath);
                k++;
                pk = fitPath.path;
            }
        }
        return result;
    }

    /**
     * 从候选列表中得到一条路径作为pk+1
     * 要求：1）该路径的权值和最小；2）路径经过节点数最少
     *
     * @param candidatePaths：候选列表
     * @return
     */
    private static MyPath getFitPathFromCandidate(Set<MyPath> candidatePaths) {
        MyPath result = new MyPath(null, Double.MAX_VALUE);
        for (MyPath p : candidatePaths) {
            // 对于每一条路径
            if (p.weight < result.weight) {
                result = p;
            }
            if (p.weight == result.weight && p.path.size() < result.path.size()) {
                result = p;
            }
        }
        return result;
    }
}

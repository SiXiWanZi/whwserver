package com.whw.util;

import com.whw.model.MyGraph;
import com.whw.model.NavPath;
import com.whw.model.Node;

import java.util.*;

/**
 * <pre>
 *     author : 杨丽金
 *     time   : 2019/01/07
 *     desc   : A*算法工具类
 *     version: 1.0
 * </pre>
 */
public class AStarUtil{
    /**
     * 得到 startNodeNum 到 endNodeNum 之间的最短路径（A*算法）
     *
     * @param startNodeIndex：起点在数组中编号
     * @param endNodeIndex：终点在数组中编号
     * @return
     */
    public static NavPath getShortestRoad_AStar(MyGraph g, int startNodeIndex, int endNodeIndex) {
        PriorityQueue<AStarNode> open = new PriorityQueue<>();
        List<AStarNode> close = new ArrayList<>();
        List<Double> gj = new ArrayList<>();
        // 将起始节点加入到 open 表中
        double man = calManhattan(g, startNodeIndex, endNodeIndex);
        open.add(new AStarNode(startNodeIndex,0,man));
        while (!open.isEmpty()) {
            // 1.将 open 表中 fj 最小的节点从open中移除
            AStarNode temp = open.remove();
            // 2.加入到 Close 中
            close.add(temp);
            // 加入到 Close 的同时，更新 gj 中相应节点对应的值
            if (gj.isEmpty()) {
                gj.add(0.0);
            } else {
                gj.add(gj.get(gj.size() - 1) + NavigationUtil.getEdgeWight(g, close.get(close.size() - 1).nodeIndex, close.get(close.size() - 2).nodeIndex));
            }
            // 判断是否找到终点
            if (temp.nodeIndex == endNodeIndex) {
                // 找到终点
                NavPath res = getNavPath(g,close, gj);
                return res;
            }
            // 3.将该节点 temp 的所有邻接节点按照邻接点规则加入到 Open 表中
            int beginIndex = g.nodes[temp.nodeIndex].getLinkEdgesBeginIndex();
            int count = g.nodes[temp.nodeIndex].getCount();
            if (beginIndex == -1) {
                continue;
            }
            for (int i = beginIndex; i < beginIndex + count; i++) {
                // 邻接节点
                Node adjNode = g.edges[i].getExitNode();
                int adjIndex = NavigationUtil.getIndex(g, adjNode.getNodeId());
                // 邻接点规则
                if (nodeIsInClose(adjIndex, close)){
                    continue;
                }
                else{
                    // adjNode 不在 close 表中
                    AStarNode p = getNodeInOpen(adjIndex, open);
                    if (p != null) {
                        // adjNode 在 Open 表
                        // 更新估价函数
                        double tempGDis=temp.gDis + NavigationUtil.getEdgeWight(g,temp.nodeIndex,adjIndex);
                        p.fj = tempGDis < p.gDis ? tempGDis+p.hDis : p.fj;
                        // 更新父节点
                        p.parentNode = temp;
                    } else {
                        // adjNode 不在 Open 表：将 adjNode 加入到 Open 表
                        double gDis=temp.gDis + NavigationUtil.getEdgeWight(g,temp.nodeIndex,adjIndex);
                        double mDis = calManhattan(g, adjIndex, endNodeIndex);
                        open.add(new AStarNode(adjIndex,gDis,mDis,temp));
                    }

                }
            }
        }
        return null;
    }

    /**
     * 判断节点 adjIndex(节点在数组中编号) 是否在 Close 表中
     * @param adjIndex
     * @param close
     * @return
     */
    private static boolean nodeIsInClose(int adjIndex, List<AStarNode> close) {
        for(int i=0;i<close.size();i++) {
            if (close.get(i).nodeIndex == adjIndex) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从 Open 表中得到 数组编号为 adjIndex 的节点，若 Open表中没有该节点则返回 null
     * @param adjIndex
     * @param open
     * @return
     */
    private static AStarNode getNodeInOpen(int adjIndex, PriorityQueue<AStarNode> open) {
        if (open != null && open.size() != 0) {
            Iterator<AStarNode> iterator=open.iterator();
            while (iterator.hasNext()) {
                AStarNode node= iterator.next();
                if (adjIndex == node.nodeIndex) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * 得到导航路径：从起始点到目标节点
     *
     * @param close
     * @param gj
     * @return
     */
    private static NavPath getNavPath(MyGraph g, List<AStarNode> close, List<Double> gj) {
        NavPath path = new NavPath();
        Stack<Node> stack = new Stack<>();
        if (close != null && close.size() != 0) {
            // 输出路径
//            Node parent = gDis.nodes[.nodeIndex];
            AStarNode parent = close.get(close.size() - 1);
            while (parent != null) {
                stack.add(g.nodes[parent.nodeIndex]);
                parent = parent.parentNode;
            }
            List<Node> nodes = new ArrayList<>();
            while (!stack.isEmpty()) {
                nodes.add(stack.pop());
            }
            path.nodeList = nodes;
            path.weight = gj.get(gj.size() - 1);
            return path;
        }
        return null;
    }


    /**
     * 计算两点间的曼哈顿距离
     *
     * @param g
     * @param startNodeIndex
     * @param endNodeIndex
     * @return
     */
    private static double calManhattan(MyGraph g, int startNodeIndex, int endNodeIndex) {
        Node p1 = g.nodes[startNodeIndex];
        Node p2 = g.nodes[endNodeIndex];
        return Math.abs(p1.getLatitude() - p2.getLatitude()) + Math.abs(p1.getLongtitude() - p2.getLongtitude());
    }

    /**
     * 存储 Open 表中节点
     */
    public static class AStarNode implements Comparable<AStarNode>{
        // 节点在数组中编号
        int nodeIndex;
        // 实际距离
        double gDis;
        // 估计距离
        double hDis;
        // f(j)估价函数
        double fj;
        // 父节点
        AStarNode parentNode;


        public AStarNode() {
        }

        public AStarNode(int nodeIndex, double gDis, double hDis, AStarNode parentNode) {
            this.nodeIndex = nodeIndex;
            this.gDis = gDis;
            this.hDis = hDis;
            this.fj = gDis+hDis;
            this.parentNode = parentNode;
        }

        public AStarNode(int nodeIndex, double g, double h) {
            this.nodeIndex = nodeIndex;
            this.gDis = g;
            this.hDis = h;
            this.fj = g+h;
            parentNode=null;
        }

        @Override
        public int compareTo(AStarNode o) {
            if (o == null) return -1;
            if (fj> o.fj)
                return 1;
            else if (fj< o.fj){
                return -1;
            }else{
                return 0;
            }
        }
    }
}

package com.whw.dao;


import com.whw.model.Edge;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface EdgeDao extends BaseDao<Edge> {
    /**
     * 通过名字查找用户
     *
     * @param name
     * @return
     */
    Edge findByName(String name);

    /**
     * 通过id
     *
     * @param edgeId
     * @return
     */
    Edge findById(String edgeId);

    /**
     * 增加一个节点
     *
     * @param edge
     */
    void addEdge(Edge edge);

    /**
     * 返回数据库中所有路段（手动采集+非手动采集）
     * @return
     */
    List<Edge> findAllRoads();

    /**
     * 根据edgeId删除边
     * @param edgeId
     * @return
     */
    boolean deleteEdgeById(String edgeId);

    /**
     * 根据edgeName删除边
     * @param edgeName
     * @return
     */
    boolean deleteEdgeByEdgeName(String edgeName);

    /**
     * 得到连通的路段
     * @return
     */
    List<Edge> findConnectedEdges();

    /**
     * 更新路段的自由流行驶时间
     *
     * @param edge
     */
    void updateInitialTime(Edge edge, double initialTime);

    /**
     * 更新路段的初始通行能力
     * @param edge
     */
    void updateInitialCarNumbers(Edge edge, int initialCarNumbers);
}

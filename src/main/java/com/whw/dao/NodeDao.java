package com.whw.dao;


import com.whw.model.Node;
import com.whw.model.User;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface NodeDao extends BaseDao<Node> {
    /**
     * 通过名字查找用户
     * @param name
     * @return
     */
    Node findByName(String name);

    /**
     * 通过id
     * @param nodeId
     * @return
     */
    Node findById(String nodeId);

    /**
     *
     * 增加一个节点
     * @param node
     */
    void addNode(Node node);

    /**
     * 查询数据库中所有节点
     * @return
     */
    List<Node> findAllPoints();
}

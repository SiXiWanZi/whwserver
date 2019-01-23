package com.whw.dao.impl;

import com.whw.dao.NodeDao;
import com.whw.dao.UserDao;
import com.whw.model.Node;
import com.whw.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class NodeDaoImpl extends BaseDaoImpl<Node> implements NodeDao {
    @Override
    public Node findByName(String name) {
        String hql = "from Node where nodeName = ?";
        return get(hql, name);
    }

    @Override
    public Node findById(String nodeId) {
        String hql = "from Node where nodeId = ?";
        return get(hql, nodeId);
    }

    @Override
    public void addNode(Node node) {
        save(node);
    }

    @Override
    public List<Node> findAllPoints() {
        String hql = "from Node";
        return find(hql);
    }
}

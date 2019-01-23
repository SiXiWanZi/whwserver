package com.whw.dao.impl;

import com.whw.dao.EdgeDao;
import com.whw.model.Edge;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class EdgeDaoImpl extends BaseDaoImpl<Edge> implements EdgeDao {

    @Override
    public Edge findByName(String name) {
        String hql = "from Edge where edgeName = ?";
        return get(hql, name);
    }

    @Override
    public Edge findById(String edgeId) {
        String hql = "from Edge where edgeId = ?";
        return get(hql, edgeId);
    }

    @Override
    public void addEdge(Edge edge) {
        save(edge);
    }

    @Override
    public List<Edge> findAllRoads() {
        String hql = "from Edge";
//        String hql = "from Edge where isConnected=false";
//        String hql = "from Edge GROUP BY edgeName";
        return find(hql);
    }

    @Override
    public boolean deleteEdgeById(String edgeId) {
        String hql = "delete from Edge where edgeId=?";
        int rowNum = executeHql(hql, edgeId);
        if (rowNum > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteEdgeByEdgeName(String edgeName) {
        String hql = "delete from Edge where edgeName=?";
        int rowNum = executeHql(hql, edgeName);
        if (rowNum > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Edge> findConnectedEdges() {
        String hql = "from Edge where isConnected=true";
        return find(hql);
    }

    @Override
    public void updateInitialTime(Edge edge, double initialTime) {
        edge.setInitialTime(initialTime);
        update(edge);
    }

    @Override
    public void updateInitialCarNumbers(Edge edge, int initialCarNumbers) {
        edge.setInitalCarNumbers(initialCarNumbers);
        update(edge);
    }


}

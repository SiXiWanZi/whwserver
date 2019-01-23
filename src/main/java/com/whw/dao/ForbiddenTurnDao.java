package com.whw.dao;


import com.whw.model.ForbiddenTurn;
import com.whw.model.Node;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface ForbiddenTurnDao extends BaseDao<ForbiddenTurn> {
    /**
     * 通过名字查找用户
     * @param nodeId
     * @return
     */
    ForbiddenTurn findByNodeId(String nodeId);

    /**
     * 通过id
     * @param forbiddenId
     * @return
     */
    ForbiddenTurn findById(String forbiddenId);

    /**
     *
     * 增加一个节点
     * @param forbiddenTurn
     */
    void addForbiddenTurn(ForbiddenTurn forbiddenTurn);
}

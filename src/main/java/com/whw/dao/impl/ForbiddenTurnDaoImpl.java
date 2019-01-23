package com.whw.dao.impl;

import com.whw.dao.ForbiddenTurnDao;
import com.whw.dao.NodeDao;
import com.whw.model.ForbiddenTurn;
import com.whw.model.Node;
import org.springframework.stereotype.Repository;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class ForbiddenTurnDaoImpl extends BaseDaoImpl<ForbiddenTurn> implements ForbiddenTurnDao {

    @Override
    public ForbiddenTurn findByNodeId(String nodeId) {
        String hql = "from ForbiddenTurn where nodeId = ?";
        return get(hql, nodeId);
    }

    @Override
    public ForbiddenTurn findById(String yhbh) {
        return null;
    }

    @Override
    public void addForbiddenTurn(ForbiddenTurn forbiddenTurn) {
        save(forbiddenTurn);
    }

}

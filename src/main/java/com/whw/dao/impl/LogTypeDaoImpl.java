package com.whw.dao.impl;

import com.whw.dao.LogTypeDao;
import com.whw.model.LogType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
@Repository
public class LogTypeDaoImpl extends BaseDaoImpl<LogType> implements LogTypeDao {

    @Override
    public List<LogType> findFirstLevel() {
        String hql = "from LogType gt where gt.flbm is null";
        return find(hql);
    }

    @Override
    public List<LogType> findGoodsTypeByParentId(String flbm) {
        String hql = "from LogType gt where gt.flbm is not null and gt.flbm.lbbm= ?";
        return find(hql, flbm);
    }
}

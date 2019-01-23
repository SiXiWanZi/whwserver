package com.whw.dao;


import com.whw.model.LogType;

import java.util.List;

/**
 * Created by YangZhi on 2016/1/15.
 */
public interface LogTypeDao extends BaseDao<LogType> {

    List<LogType> findFirstLevel();

    List<LogType> findGoodsTypeByParentId(String flbm);


}
